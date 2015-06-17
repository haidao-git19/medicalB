package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.DiseaseService;
import com.netbull.shop.manage.weixin.service.SectionService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class DiseaseController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private SectionService sectionService;
	
	/**
	 * 根据科室获取病种列表
	 * @param request 参数sectionID-科室ID 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryDiseaseList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryDiseaseList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> sectionDetail=new HashMap<String, Object>();
		List<Map> diseaseList=new ArrayList<Map>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryDiseaseList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("根据科室获取病种列表信息，参数：" + requestMap.toString());
			}
			if(StringUtil.isEmpty(requestMap.get("sectionID"))){
				sectionDetail=sectionService.querySectionDetail(requestMap);
				diseaseList=diseaseService.queryDiseaseList(requestMap);
			}
			
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("sectionDetail", sectionDetail);
			resultMap.put("diseaseList", diseaseList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}
