package com.netbull.shop.manage.weixin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.dao.ConsultDispatcherDao;
import com.netbull.shop.manage.weixin.dao.ConsultationDao;
import com.netbull.shop.manage.weixin.dao.DoctorDao;
import com.netbull.shop.manage.weixin.vo.ConsultationDispatcher;
import com.netbull.shop.manage.weixin.vo.ConsultationVo;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConsultationService {
	private static final Log log = LogFactory.getLog(ConsultationService.class);

	@Resource
	private ConsultationDao consultationDao;
	@Resource
	private ConsultDispatcherDao consultDispDao;
	@Resource
	private DoctorDao doctorDao;
	@Resource
	private AccountmanageService accService;

	public List<Map> queryConsultationList(Map paramter) {
		return consultationDao.queryConsultationList(paramter);
	}

	@Transactional
	public Integer modifyConsultation(Map paramter) {
		Integer rlt = consultationDao.modifyConsultation(paramter);
		String state = (String) paramter.get("state");
		if ("2".equals(state)) {
			// 分诊
			dispatcherConsult(paramter);
			// 查询是否需要退费
			Map consultation = consultationDao
					.queryConsultationDetail(paramter);
			// 如果已经付款进行退款
			Integer paystate = (Integer) consultation.get("paystate");
			if (Constants.CONSULATOIN_PAY_STATE_FINISH.equals(String
					.valueOf(paystate))) {
				Long serviceNumber = (Long) consultation
						.get("consultationID");
				Long patientID = (Long) consultation.get("patientID");
				accService.returnsFee(Constants.CONSULATOIN_PAY_TYPE,
						String.valueOf(serviceNumber), String.valueOf(patientID));
			}
		}
		return rlt;
	}

	public Integer saveConsultationInfo(ConsultationVo consultationVo) {
		return consultationDao.saveConsultationInfo(consultationVo);
	}

	public Map queryConsultationDetail(Map paramter) {
		return consultationDao.queryConsultationDetail(paramter);
	}

	public Long queryTodayConsultation(Map parameter) {
		return consultationDao.queryTodayConsultation(parameter);
	}

	public List<Map> queryConsultationRepeatList(Map paramter) {
		return consultationDao.queryConsultationRepeatList(paramter);
	}

	public List<Map> queryRcentiConsultation(Map paramter) {
		return consultationDao.queryRcentiConsultation(paramter);
	}

	/**
	 * 查询分诊记录的顶级专家或者推荐的科室
	 * 
	 * @createTime: 2015年6月27日 下午3:43:56
	 * @history:
	 * @param consultationMap
	 * @return Map
	 */
	public Map queryConsultDispatcherDetail(Map consultationMap) {
		Map<String, Object> detail = new HashMap<String, Object>();
		// detail.put("isdispatcher", consultationMap.get("isdispatcher"));
		Integer dispatcherType = (Integer) consultationMap
				.get("dispatcherType");
		detail.put("dispatcherType", dispatcherType);
		if (dispatcherType != null && dispatcherType == 1) {
			detail.put("doctors",
					consultationDao.queryConsultDispDocs(consultationMap));

		} else if (dispatcherType != null && dispatcherType == 2) {
			detail.put("sections",
					consultationDao.queryConsultDispSections(consultationMap));
		}
		return detail;
	}

	/**
	 * 推荐分诊
	 * 
	 * @createTime: 2015年6月27日 下午3:42:48
	 * @history:
	 * @param paramter
	 *            void
	 */
	private void dispatcherConsult(Map paramter) {
		String disType = (String) paramter.get("dispatcherType");
		String dispdatas = (String) paramter.get("dataItems");
		List<ConsultationDispatcher> list = JSON.parseArray(dispdatas,
				ConsultationDispatcher.class);

		if ("1".equals(disType)) {
			if (list != null) {
				for (ConsultationDispatcher disp : list) {
					disp.setDispatcherType(Integer.parseInt(disType));
					disp.setConsultationID(Long.valueOf((String) paramter
							.get("consultationID")));

					consultDispDao.saveConsultDispatcher(disp);
				}
			}
		} else if ("2".equals(disType)) {
			ConsultationDispatcher disp = new ConsultationDispatcher();
			disp.setDispatcherType(Integer.parseInt(disType));
			disp.setConsultationID(Long.valueOf((String) paramter
					.get("consultationID")));
			Map doc = doctorDao.queryDoctorDetail(paramter);
			Long sectionID = (Long) doc.get("sectionID");
			Long hospitalID = (Long) doc.get("hospitalID");
			try {
				disp.setTargetId(sectionID);
				disp.setHospitalID(hospitalID);
			} catch (NumberFormatException e) {
				log.error("医生科室和医院ID异常" + sectionID + "," + hospitalID);
			}
			consultDispDao.saveConsultDispatcher(disp);
		}

	}
}
