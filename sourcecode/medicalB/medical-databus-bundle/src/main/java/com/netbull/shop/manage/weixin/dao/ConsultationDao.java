package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.ConsultationVo;

@Repository
public class ConsultationDao {
	private static final String MYBATIS_PREFIX = ConsultationDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	public List<Map> queryConsultationList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryConsultationList",paramter);
		return list;
	}
	
	public Integer modifyConsultation(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyConsultation",paramter);
	}
	
	public Integer saveConsultationInfo(ConsultationVo consultationVo) {		
		return session.update(MYBATIS_PREFIX + ".saveConsultationInfo",consultationVo);
	}
	
	public Map queryConsultationDetail(Map paramter) {		
		return session.selectOne(MYBATIS_PREFIX + ".queryConsultationDetail",paramter);
	}
	
	public Long queryTodayConsultation(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryTodayConsultation", parameter);
	}
	
	public List<Map> queryConsultationRepeatList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryConsultationRepeatList",paramter);
		return list;
	}
	
	public List<Map> queryRcentiConsultation(Map paramter) {		
		return session.selectList(MYBATIS_PREFIX + ".queryRcentiConsultation",paramter);
	}
}
