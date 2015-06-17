package com.netbull.shop.manage.common.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbull.shop.manage.weixin.service.PlusService;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.JPushService;

@Component("plusTipsQuartz")
public class PlusTipsQuartz {

	@Autowired
	private PlusService plusService;
	@Resource
	private JPushService jPushService;
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	public void run(){
		Map<String,String> requestMap = new HashMap<String,String>();
		String preTime = ConfigLoadUtil.loadConfig().getPropertie("Quartz_Cron_time");
		requestMap.put("minitue", preTime);
		List<Map> plusList = this.plusService.queryRcentiPlus(requestMap);
		
		if(NullUtil.isNull(plusList)){
			return;
		}
		
		for (Iterator iterator = plusList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			invokenNotice(map);
		}
	}
	
	private void invokenNotice(Map paramter){
		Map<String,String> requestMap = new HashMap<String,String>();
		String plusID = StringUtil.getString(paramter.get("plusID"));
		String doctorID = StringUtil.getString(paramter.get("doctorID"));
		String patientID = StringUtil.getString(paramter.get("patientID"));
		
		String doctorName = StringUtil.getString(paramter.get("doctorName"));
		String patientName = StringUtil.getString(paramter.get("patientName"));
		
		String aliases = "YS_"+doctorID;
		String alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Doctor_Notice_Plus_Msg");
		alert = alert.replace("#name#", doctorName);
		
		if(HttpXmlUtil.sendYSNoticeMsg(aliases, alert,1)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "0");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "3");
		requestMap.put("bizId", plusID);
		jPushService.savePushLogInfo(requestMap);
		
		aliases = "HZ_"+patientID;
		alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Patient_Notice_Plus_Msg");
		alert = alert.replace("#name#", patientName);
		
		if(HttpXmlUtil.sendNoticeMsg(aliases, alert,1)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "1");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "3");
		requestMap.put("bizId", plusID);
		jPushService.savePushLogInfo(requestMap);
		
		requestMap.put("plusID",plusID);
		requestMap.put("isnotice", "1");
		this.plusService.updatePlus(requestMap);
		
	}
}
