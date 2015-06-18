package com.netbull.shop.manage.common.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netbull.shop.manage.weixin.service.ConsultationService;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.JPushService;

@Component("consultationTipsQuartz")
public class ConsultationTipsQuartz {

	@Autowired
	private ConsultationService consultationService;
	@Resource
	private JPushService jPushService;
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	public void run(){
		Map<String,String> requestMap = new HashMap<String,String>();
		String preTime = ConfigLoadUtil.loadConfig().getPropertie("Quartz_Cron_time");
		requestMap.put("minitue", preTime);
		List<Map> consultationList = this.consultationService.queryRcentiConsultation(requestMap);
		
		if(NullUtil.isNull(consultationList)){
			return;
		}
		
		for (Iterator iterator = consultationList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			invokenNotice(map);
		}
	}
	
	private void invokenNotice(Map paramter){
		Map<String,String> requestMap = new HashMap<String,String>();
		String consultationID = StringUtil.getString(paramter.get("consultationID"));
		String doctorID = StringUtil.getString(paramter.get("doctorID"));
		String patientID = StringUtil.getString(paramter.get("patientID"));
		String isnotice = StringUtil.getString(paramter.get("isnotice"));
		
		String doctorName = StringUtil.getString(paramter.get("doctorName"));
		String patientName = StringUtil.getString(paramter.get("patientName"));
		
		String aliases;
		String alert;
		
		if("0".equals(isnotice)){//患者咨询完成，给医生发送咨询提醒；
			aliases = "YS_"+doctorID;
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Doctor_Notice_Consultation_Msg");
			alert = alert.replace("#name#", doctorName);
			
			if(HttpXmlUtil.sendYSNoticeMsg(aliases, alert,1)){
				requestMap.put("status", "0");
			}else{
				requestMap.put("status", "1");
			}
			
			requestMap.put("aliases",aliases);
			requestMap.put("aliasesType", "0");
			requestMap.put("alert", alert);
			requestMap.put("bizType", "1");
			requestMap.put("bizId", consultationID);
			jPushService.savePushLogInfo(requestMap);
			
			requestMap.put("consultationID",consultationID);
			requestMap.put("isnotice", "1");
			this.consultationService.modifyConsultation(requestMap);
		}else if("1".equals(isnotice)){//医生答复完，给患者发送答复提醒；
			aliases = "HZ_"+patientID;
			alert = ConfigLoadUtil.loadConfig().getPropertie("Send_Patient_Notice_Consultation_Msg");
			alert = alert.replace("#name#", patientName);
			
			if(HttpXmlUtil.sendNoticeMsg(aliases, alert,1)){
				requestMap.put("status", "0");
			}else{
				requestMap.put("status", "1");
			}
			
			requestMap.put("aliases",aliases);
			requestMap.put("aliasesType", "1");
			requestMap.put("alert", alert);
			requestMap.put("bizType", "1");
			requestMap.put("bizId", consultationID);
			jPushService.savePushLogInfo(requestMap);
			
			requestMap.put("consultationID",consultationID);
			requestMap.put("isnotice", "2");
			this.consultationService.modifyConsultation(requestMap);
		}
	}
}
