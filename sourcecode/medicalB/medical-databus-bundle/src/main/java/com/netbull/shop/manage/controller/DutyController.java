package com.netbull.shop.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.DutyService;
import com.netbull.shop.manage.weixin.vo.Duty;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("dutyController")
public class DutyController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private DutyService dutyService;
	@Resource
	private DoctorService doctorService;
	
	/**
	 * 修改/新增值班信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/anon/addOrModifyDutyInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String addOrModifyDutyInfo(Duty duty,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改/新增值班基本信息，参数：" + requestMap.toString());
			}
			Long doctorId = duty.getDoctorID();
			Integer weekNum = duty.getWeekNum();
			Integer weekFlag = duty.getWeekFlag();
			Integer dayFlag = duty.getDayFlag();
			
			if(NullUtil.isNull(doctorId)||NullUtil.isNull(weekNum)||NullUtil.isNull(weekFlag)||NullUtil.isNull(dayFlag)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			int count = dutyService.update(duty);
			if(count <= 0){
				dutyService.save(duty);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("id", duty.getId());
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	/**
	 * 删除值班信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/anon/deleteDutyInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteDutyInfo(Duty duty,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("删除值班基本信息，参数：" + requestMap.toString());
			}
			Long id = Long.parseLong(StringUtil.getString(requestMap.get("id")));
			dutyService.deleteById(id);
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