package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.MultMediaDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MultiMediaService {
	private static final Logger logger = Logger.getLogger("serviceLog");
    
	@Resource
	private MultMediaDao multMediaDao;

	public Map queryMultMedia(Map paramter) {		
		return multMediaDao.queryMultMedia(paramter);
	}
	
	public Integer modifyMultMedia(Map paramter) {		
		return multMediaDao.modifyMultMedia(paramter);
	}
	
	public Integer saveMultMediaInfo(Map<String,Object> resultMap) {		
		return multMediaDao.saveMultMediaInfo(resultMap);
	}
	
	public Integer saveCallAuthInfo(Map<String,Object> resultMap) {		
		return multMediaDao.saveCallAuthInfo(resultMap);
	}
	
}
