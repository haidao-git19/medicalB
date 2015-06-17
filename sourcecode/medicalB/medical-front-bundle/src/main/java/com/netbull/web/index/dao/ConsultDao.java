package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.web.index.entity.Consult;

@Repository("consultDao")
public class ConsultDao {
	private static final String MYBATIS_PREFIX = ConsultDao.class.getName();
	@Resource
	private SqlSession session;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		return session.page(MYBATIS_PREFIX+".pageConsult", MYBATIS_PREFIX+".countConsult",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public Consult findById(Consult consult) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("consultationID", consult.getConsultationID());
		List<Consult> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new Consult();
	}

	public void update(Consult consult) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".update", consult);
	}

	public void save(Consult consult) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".save", consult);
	}

	public int del(Consult consult) {
		// TODO Auto-generated method stub
	 return session.delete(MYBATIS_PREFIX+".del",consult);
	}
	
	/**
	 * 查询咨询信息
	 * @param paramter
	 * @return
	 */
	public List<Consult> queryDetailList(Map paramter) {		
		List<Consult> list = session.selectList(MYBATIS_PREFIX + ".queryDetailList",paramter);
		return list;
	} 
	/**
	 * 查询咨询信息数量
	 * @param paramter
	 * @return
	 */
	public long countWsConsult(Map paramter) {		
		long l = session.count(MYBATIS_PREFIX + ".countWsConsult",paramter);
		return l;
	} 
	
	public List<Map> queryLatestConsultList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryLatestConsultList", parameter);
	}
}
