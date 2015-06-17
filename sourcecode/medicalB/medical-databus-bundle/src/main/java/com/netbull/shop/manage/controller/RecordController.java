package com.netbull.shop.manage.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.manage.weixin.service.RecordAttaService;
import com.netbull.shop.manage.weixin.service.RecordService;
import com.netbull.shop.manage.weixin.vo.EMRecord;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class RecordController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private RecordService recordService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private RecordAttaService recordAttaService;
	
	/**
	 * 获取病人病历详情
	 * @param request 参数recordID:病人病历ID
	 * 					patientID:病人ID
	 * 					doctorID:医生ID
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/queryRecordDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecordDetail(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryRecordDetail method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("获取病人病历详细信息，参数：" + requestMap.toString());
			}
			
			Map recordDetail=recordService.queryRecordDetail(requestMap);
			
			List<Map> recordAttaList=recordAttaService.queryRecordAttaList(requestMap);
			for (Iterator iterator = recordAttaList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				map.put("attaURL", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + map.get("attaURL"));
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("recordDetail", recordDetail);
			resultMap.put("recordAttaList", recordAttaList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 获取病人病历列表
	 * @param request 参数patientID:病人ID 
	 * 					  doctorID:医生ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryRecordList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecordList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryRecordList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("获取病人病历列表，参数：" + requestMap.toString());
			}
			String type = requestMap.get("type");
			if(NullUtil.isNull(type)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", "参数type必须传递");
				return JsonUtils.obj2Json(resultMap);
			}
			
			if("0".equals(type)){
				if(NullUtil.isNull(requestMap.get("patientID"))){
					resultMap.put("code", Constants.FAIL);
					resultMap.put("msg", "patientID必传");
					return JsonUtils.obj2Json(resultMap);
				}
			}else{
				if(NullUtil.isNull(requestMap.get("patientID"))){
					resultMap.put("code", Constants.FAIL);
					resultMap.put("msg", "patientID必传");
					return JsonUtils.obj2Json(resultMap);
				}
			}
			
			List<Map> recordList=recordService.queryRecordList(requestMap);
			if("1".equals(type) && !NullUtil.isNull(recordList)){
				recordService.updateRecord(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("recordList", recordList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 添加病人病历信息
	 * @param request 
	 * 		参数：patientCard-患者身份证
	 * 				hospitalName-医院名称
	 * 				sectionName-科室名称
	 * 				diseaseName-病目
	 * 				note-就诊明细
	 * 				drug-用药
	 * 				source-
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/saveRecord", produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveRecord(EMRecord eMRecord,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> requestMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class saveRecord method.");
			}
			
			recordService.saveRecord(eMRecord);
			
			if(!NullUtil.isNull(eMRecord.getAttaList())){
				String [] attaArray = eMRecord.getAttaList().split(",");
				if(!NullUtil.isNull(eMRecord.getId()) && !NullUtil.isNull(attaArray)){
					for (int i = 0; i < attaArray.length; i++) {
						String attaObj = attaArray[i];
						String[] temp = attaObj.split("\\|");
						if(temp != null && temp.length == 2){
							requestMap.put("recordID", eMRecord.getId());
							requestMap.put("attaURL", temp[0]);
							requestMap.put("attaName", temp[1]);
							this.recordAttaService.saveAttaRecord(requestMap);
						}
					}
				}
			}
			
			if(!NullUtil.isNull(eMRecord.getDoctorId())){
				requestMap.put("recordID", eMRecord.getId());
				requestMap.put("doctorID", eMRecord.getDoctorId());
				Long count = this.recordService.queryRecordRelationCount(requestMap);
				if(count <= 0){
					recordService.saveRecordRelation(requestMap);
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/modifyRecord", produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyRecord(EMRecord eMRecord,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> requestMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class modifyRecord method.");
			}
			
			recordService.updateAllRecord(eMRecord);
			
			String [] attaArray = eMRecord.getAttaList().split(",");
			if(!NullUtil.isNull(eMRecord.getId()) && !NullUtil.isNull(attaArray)){
				for (int i = 0; i < attaArray.length; i++) {
					String attaObj = attaArray[i];
					String[] temp = attaObj.split("\\|");
					if(temp != null && temp.length == 2){
						requestMap.put("recordID", eMRecord.getId());
						requestMap.put("attaURL", temp[0]);
						requestMap.put("attaName", temp[1]);
						this.recordAttaService.saveAttaRecord(requestMap);
					}
				}
			}
			if(!NullUtil.isNull(eMRecord.getDoctorId())){
				requestMap.put("recordID", eMRecord.getId());
				requestMap.put("doctorID", eMRecord.getDoctorId());
				Long count = this.recordService.queryRecordRelationCount(requestMap);
				if(count <= 0){
					recordService.saveRecordRelation(requestMap);
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/deleteRecord", produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteRecord(EMRecord eMRecord,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class deleteRecord method.");
			}
			recordService.deleteRecord(eMRecord);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
}
