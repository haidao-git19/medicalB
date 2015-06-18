package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.manage.weixin.dao.ConsultDispatcherDao;
import com.netbull.shop.manage.weixin.dao.ConsultationDao;
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

	public List<Map> queryConsultationList(Map paramter) {		
		return consultationDao.queryConsultationList(paramter);
	}
	@Transactional
	public Integer modifyConsultation(Map paramter) {	
		//TODO 修改咨询信息，判断如果有推荐的医生或科室进行保存
		Integer rlt= consultationDao.modifyConsultation(paramter);
		String state=(String) paramter.get("state");
		if("2".equals(state)){
			String disType=(String) paramter.get("dispatcherType");
			String dispdatas=(String) paramter.get("dataItems");
			List<ConsultationDispatcher> list=JSON.parseArray(dispdatas, ConsultationDispatcher.class);
			if(list!=null){
				for(ConsultationDispatcher disp:list){
					disp.setDispatcherType(Integer.parseInt(disType));
					disp.setConsultationID(Long.valueOf((String)paramter.get("consultationID")));
					consultDispDao.saveConsultDispatcher(disp);
				}
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
	
	public Long queryTodayConsultation(Map parameter){
		return consultationDao.queryTodayConsultation(parameter);
	}
	
	public List<Map> queryConsultationRepeatList(Map paramter) {		
		return consultationDao.queryConsultationRepeatList(paramter);
	}
	
	public List<Map> queryRcentiConsultation(Map paramter) {		
		return consultationDao.queryRcentiConsultation(paramter);
	}
}
