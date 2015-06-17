package com.netbull.shop.auth.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.dao.HDao;
import com.netbull.shop.auth.entity.OptLogInfo;

@Service("logService")
public class LogService {
	
	@Resource
	private HDao authDao;
	
	public void save(OptLogInfo optLogInfo){
		authDao.save(optLogInfo);
	}
	
	public void log(String optName) {
		OptLogInfo optLogInfo = new OptLogInfo();
		optLogInfo.setLoginName(UserService.getCurrentLoginName());
		optLogInfo.setOptName(optName);
		optLogInfo.setOptTime(new Date());
		save(optLogInfo);
	}
}
