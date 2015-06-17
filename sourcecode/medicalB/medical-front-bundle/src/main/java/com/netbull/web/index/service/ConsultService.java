package com.netbull.web.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.web.index.dao.ConsultDao;
import com.netbull.web.index.entity.Consult;


@Service("consultService")
public class ConsultService {
	@Resource
	private ConsultDao consultDao;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = consultDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public Consult findById(Consult consult) {
		// TODO Auto-generated method stub
		return consultDao.findById(consult);
	}


	public void update(Consult consult) {
		// TODO Auto-generated method stub
		consultDao.update(consult);
	}


	public void save(Consult consult) {
		// TODO Auto-generated method stub
		consultDao.save(consult);
	}


	public int del(Consult consult) {
		// TODO Auto-generated method stub
		return consultDao.del(consult);
	}
	/**
	 * 查询咨询信息
	 * @param paramter
	 * @return
	 */
	public List<Consult> queryDetailList(Map paramter) {		
		return consultDao.queryDetailList(paramter);
	} 
	/**
	 * 查询网上咨询数量
	 * @param paramter
	 * @return
	 */
	public long countWsConsult(Map paramter) {		
		long l = consultDao.countWsConsult(paramter);
		return l;
	} 
	
	public List<Map> queryLatestConsultList(Map parameter){
		return consultDao.queryLatestConsultList(parameter);
	}
}
