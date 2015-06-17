package com.netbull.shop.manage.weixin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.DutyDao;
import com.netbull.shop.manage.weixin.vo.Duty;

@Service("dutyService")
public class DutyService {
	
	@Resource
	private DutyDao dutyDao;
	
	public int deleteById(long id) {
		return dutyDao.deleteById(id);
	}
	
	public int save(Duty duty) {
		return dutyDao.save(duty);
	}
	
	public int update(Duty duty) {
		return dutyDao.update(duty);
	}
}
