package com.netbull.shop.manage.common.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbull.shop.manage.weixin.service.SellRecordService;

@Component("RefrashLogisticsInfoQuartz")
public class RefrashLogisticsInfoQuartz {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private SellRecordService sellRecordService;
	
	public void run(){
		
	}
}
