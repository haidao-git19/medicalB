package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.GoodsReport;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.GoodsDC;
import com.netbull.shop.manage.common.util.SectionFilterDC;
import com.netbull.shop.manage.common.util.HospitalDC;
import com.netbull.shop.manage.weixin.service.DiseaseService;
import com.netbull.shop.manage.weixin.service.GoodsReportService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.manage.weixin.service.HospitalService;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class HospitalCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private PatientService patientService;

	/**
	 * 查询附近医院列表
	 * @param request 参数locationX-经度
	 * 						locationY-纬度
	 * 						scope-距离范围(如2公里就传2)
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryNearHospitalList", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryNearHospitalList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryNearHospitalList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询附近医院列表信息，参数：" + requestMap.toString());
			}
			
			Double scope=requestMap.get("scope")!=null?Double.parseDouble(StringUtil.getString(requestMap.get("scope"))):2.0;
			String locationX=StringUtil.getString(requestMap.get("locationX"));
			String locationY=StringUtil.getString(requestMap.get("locationY"));
			
			if(!StringUtil.isEmpty(locationX)||!StringUtil.isEmpty(locationY)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_LOCATION);
				return resultMap;
			}
			
			Map parameter=new HashMap();
			parameter.put("scope", scope);
			parameter.put("locationX", locationX);
			parameter.put("locationY", locationY);
			List<Map> nearHospitalList=hospitalService.queryNearHospitalList(parameter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("nearHospitalList", nearHospitalList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryHospitalList", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryHospitalList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryHospitalList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医院列表信息，参数：" + requestMap.toString());
			}
			String type = requestMap.get("type");
			if(Constants.HOSTPITAL_TYPE_0.equals(type)){
				resultMap.put("hospitalList", HospitalDC.getInstance().queryHospitalLevelList(null));
			}else{
				resultMap.put("hospitalList", HospitalDC.getInstance().queryHospitalAreaList(null));
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/anon/queryHospitalDetail" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryHospitalDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询详细信息，参数：" + requestMap.toString());
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("hospitalDetail", HospitalDC.getInstance().queryHospitalDetail(requestMap.get("hospitalID")));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/anon/querySectionFilter" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String querySectionFilter(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Map _map = SectionFilterDC.getInstance().queryFilter();
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("filter", _map.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 获取推荐医院列表信息
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/queryRecommendHospitalList" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecommendHospitalList(HttpServletRequest request) throws IOException{
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询推荐医院列表信息，参数：" + requestMap.toString());
			}
			requestMap.put("recomFlag", "1");
			List<Map> recomHospitalList=hospitalService.queryRecomHospitalList(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("recomHospitalList", recomHospitalList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/queryVirtualHospital" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryVirtualHospital(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMapCheck(request,"patientID");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> _virtualHospital = new ArrayList<Map>();
		Set<Map> setMap = new HashSet<Map>();
		try {
			if(NullUtil.isNull(requestMap)){
				resultMap.put("code", Constants.FAIL_NULL_1);
				resultMap.put("msg", Constants.FAIL_NULL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("查询详细信息，参数：" + requestMap.toString());
			}
			
			List<Map> serviceList=patientService.myServiceList(requestMap);
			List<Map> virtualHospitalList = hospitalService.queryVirtualHospital(requestMap);
			
			if(NullUtil.isNull(serviceList)||NullUtil.isNull(virtualHospitalList)){
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			for (Iterator iterator = virtualHospitalList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				for (Iterator iterator2 = serviceList.iterator(); iterator2.hasNext();) {
					Map map2 = (Map) iterator2.next();
					if(StringUtil.getString(map.get("id")).equals(StringUtil.getString(map2.get("sectionID")))){
						 if(setMap.add(map)){  
							 _virtualHospital.add(map);
				         } 
					}
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("virtualHospital", _virtualHospital);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}
