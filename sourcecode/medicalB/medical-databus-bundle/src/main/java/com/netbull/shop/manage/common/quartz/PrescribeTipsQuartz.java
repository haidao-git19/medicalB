package com.netbull.shop.manage.common.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbull.shop.manage.weixin.service.PrescribeService;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.JPushService;

@Component("prescribeTipsQuartz")
public class PrescribeTipsQuartz {

	@Autowired
	private PrescribeService prescribeService;
	@Resource
	private JPushService jPushService;
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	public void run(){
		Map<String,String> requestMap = new HashMap<String,String>();
		String preTime = ConfigLoadUtil.loadConfig().getPropertie("Quartz_Cron_time");
		requestMap.put("minitue", preTime);
		List<Map> prescribeList = this.prescribeService.queryRcentiPrescribe(requestMap);
		
		if(NullUtil.isNull(prescribeList)){
			return;
		}
		
		for (Iterator iterator = prescribeList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			invokenNotice(map);
		}
	}
	
	private void invokenNotice(Map paramter){
		Map<String,String> requestMap = new HashMap<String,String>();
		String id = StringUtil.getString(paramter.get("id"));
		String doctorID = StringUtil.getString(paramter.get("doctorId"));
		String patientID = StringUtil.getString(paramter.get("patientID"));
		
		String doctorName = StringUtil.getString(paramter.get("doctorName"));
		String patientName = StringUtil.getString(paramter.get("patientName"));
		
		String aliases = "YS_"+doctorID;
		String alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Doctor_Notice_Prescribe_Msg");
		alert = alert.replace("#name#", doctorName);
		
		if(HttpXmlUtil.sendYSNoticeMsg(aliases, alert,1)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "0");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "4");
		requestMap.put("bizId", id);
		jPushService.savePushLogInfo(requestMap);
		
		aliases = "HZ_"+patientID;
		alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Patient_Notice_Prescribe_Msg");
		alert = alert.replace("#name#", patientName);
		
		if(HttpXmlUtil.sendNoticeMsg(aliases, alert,1)){
			requestMap.put("status", "0");
		}else{
			requestMap.put("status", "1");
		}
		
		requestMap.put("aliases",aliases);
		requestMap.put("aliasesType", "1");
		requestMap.put("alert", alert);
		requestMap.put("bizType", "4");
		requestMap.put("bizId", id);
		jPushService.savePushLogInfo(requestMap);
		
		requestMap.put("id",id);
		requestMap.put("isnotice", "1");
		this.prescribeService.update(requestMap);
		
	}
}
