package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.ConsultationDao;
import com.netbull.shop.manage.weixin.vo.ConsultationVo;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConsultationService {
	private static final Log log = LogFactory.getLog(ConsultationService.class);
    
	@Resource
	private ConsultationDao consultationDao;

	public List<Map> queryConsultationList(Map paramter) {		
		return consultationDao.queryConsultationList(paramter);
	}
	
	public Integer modifyConsultation(Map paramter) {		
		return consultationDao.modifyConsultation(paramter);
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
