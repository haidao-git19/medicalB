package com.netbull.shop.manage.common.quartz;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.questionnaire.service.QuestionnaireService;

@Component("PushQuestionnaireQuartz")
public class PushQuestionnaireQuartz {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	public void run(){
		List<Map> groupedQnPMList=questionnaireService.queryGroupedQnPMList();
		List<Map> qnPatientMappingList=questionnaireService.queryQuestionnairePatientMappingList();
		
		if(!NullUtil.isNull(qnPatientMappingList)){
			
			for(Iterator<Map> groupIter=groupedQnPMList.iterator();groupIter.hasNext();){
				
				Map gmap=groupIter.next();
				int count=0;
				for(Iterator<Map> singleIter=qnPatientMappingList.iterator();singleIter.hasNext();){
					
					Map qnPM=singleIter.next();
					if(ObjectUtils.equals(gmap.get("qnId"),qnPM.get("qnId"))
							&&ObjectUtils.equals(gmap.get("patientId"),qnPM.get("patientId"))
							&&ObjectUtils.equals(gmap.get("doctorId"),qnPM.get("doctorId"))){
						
						if(!NullUtil.isNull(qnPM.get("qnPushState"))&&Integer.valueOf(StringUtil.getString(qnPM.get("qnPushState")))==1){
							count++;
						}
					}
				}
				if(count==0){
					questionnaireService.saveQuestionnairePatientMapping(gmap);
				}
			}
		}
	}
}
