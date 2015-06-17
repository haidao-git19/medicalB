package com.netbull.web.index.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.web.index.dao.AdviseDao;
import com.netbull.web.index.entity.Advise;


@Service("adviseService")
public class AdviseService {

	@Resource
	private AdviseDao  adviseDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = adviseDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public void save(Advise nursing) {
		// TODO Auto-generated method stub
		adviseDao.save(nursing);
	}


	public void update(Advise nursing) {
		// TODO Auto-generated method stub
		adviseDao.update(nursing);
	}


	public Advise findById(Advise nursing) {
		// TODO Auto-generated method stub
		return adviseDao.findById(nursing);
	}


	public int del(Advise nursing) {
		// TODO Auto-generated method stub
		return adviseDao.del(nursing.getAdviceID());
	}


}
