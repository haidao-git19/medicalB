package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.JPushDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JPushService {
	private static final Logger logger = Logger.getLogger("serviceLog");
    
	@Resource
	private JPushDao jPushDao;

	public Integer savePushLogInfo(Map<String,String> resultMap) {		
		return jPushDao.savePushLogInfo(resultMap);
	}
	
	public List<Map> queryPushLogList(Map paramter) {		
		return jPushDao.queryPushLogList(paramter);
	}
	
}
