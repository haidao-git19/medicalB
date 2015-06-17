package com.netbull.shop.manage.common.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.manage.weixin.service.SubscribeService;

@Component("subscribeTipsQuartz")
public class SubscribeTipsQuartz {

	@Autowired
	private SubscribeService subscribeService;
	@Resource
	private JPushService jPushService;
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	public void run(){
		Map<String,String> requestMap = new HashMap<String,String>();
		String preTime = ConfigLoadUtil.loadConfig().getPropertie("Quartz_Cron_time");
		requestMap.put("minitue", preTime);
		List<Map> subscribeList = this.subscribeService.queryRcentiSubscribe(requestMap);
		
		if(NullUtil.isNull(subscribeList)){
			return;
		}
		
		for (Iterator iterator = subscribeList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			invokenNotice(map);
		}
	}
	
	private void invokenNotice(Map paramter){
		Map<String,String> requestMap = new HashMap<String,String>();
		String orderID = StringUtil.getString(paramter.get("orderID"));
		String doctorID = StringUtil.getString(paramter.get("doctorID"));
		String patientID = StringUtil.getString(paramter.get("patientID"));
		String state = StringUtil.getString(paramter.get("state"));
		
		String doctorName = StringUtil.getString(paramter.get("doctorName"));
		String patientName = StringUtil.getString(paramter.get("patientName"));
		
		String aliases = "YS_"+doctorID;
		String alert = null;
		if("0".equals(state)){
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Doctor_Notice_Subscribe_Msg");
		}else{
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Doctor_Notice_Subscribe_Cancel_Msg");
		}
		
		alert = alert.replace("#name#", doctorName);
		
		if(HttpXmlUtil.sendYSNoticeMsg(aliases, alert,2)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "0");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "2");
		requestMap.put("bizId", orderID);
		jPushService.savePushLogInfo(requestMap);
		
		aliases = "HZ_"+patientID;
		if("0".equals(state)){
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Patient_Notice_Subscribe_Msg");
		}else{
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Patient_Notice_Subscribe_Cancel_Msg");
		}
		alert = alert.replace("#name#", patientName);
		
		if(HttpXmlUtil.sendNoticeMsg(aliases, alert,2)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "1");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "2");
		requestMap.put("bizId", orderID);
		jPushService.savePushLogInfo(requestMap);
		
		requestMap.put("orderID",orderID);
		requestMap.put("isnotice", "1");
		this.subscribeService.modifyOrder(requestMap);
		
	}
}
