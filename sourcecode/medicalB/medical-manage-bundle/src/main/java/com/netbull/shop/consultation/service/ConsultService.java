package com.netbull.shop.consultation.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.consultation.dao.ConsultDao;
import com.netbull.shop.consultation.entity.Consult;


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
}
