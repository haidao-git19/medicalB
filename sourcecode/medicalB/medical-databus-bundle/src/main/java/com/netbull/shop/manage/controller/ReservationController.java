package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.manage.weixin.service.ReservationService;
import com.netbull.shop.manage.weixin.vo.ReservationVo;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class ReservationController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private ReservationService reservationService;
	
	@Resource
	private PatientService patientService;
	@Resource
	private DoctorService doctorService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryReservationList", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryReservationList(HttpServletRequest request)
			throws IOException {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName()
						+ " class queryReservationList method.");
			}
			String showCount = !NullUtil.isNull(requestMap.get("pageSize")) ? requestMap
					.get("pageSize") : "10";
			Map paramter = new HashMap();
			paramter.put("start", (Integer.parseInt(requestMap.get("pageNum"))-1)
					* Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("patientId", requestMap.get("patientId"));
			paramter.put("doctorId", requestMap.get("doctorId"));
			List<Map> reservationList = reservationService
					.queryReservationList(paramter);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("list", reservationList);
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	@RequestMapping(value = "/anon/queryReservationDetail", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryReservationDetail(HttpServletRequest request)
			throws IOException {
		Map<String, String> requestMap = RequestUtils.parameterToMapCheck(
				request, "id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName()
						+ " class queryReservationDetail method.");
			}
			if (NullUtil.isNull(requestMap)) {
				resultMap.put("code", Constants.FAIL_NULL_1);
				resultMap.put("msg", Constants.FAIL_NULL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			Map reservationMap = reservationService.queryReservationDetail(requestMap);
			
			if (NullUtil.isNull(reservationMap)) {
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			resultMap.put("reservationDetail", reservationMap);
			Map param=new HashMap();
			param.put("patientID", reservationMap.get("patientId"));
			param.put("doctorID", reservationMap.get("doctorId"));
			resultMap.put("patientDetail", patientService.queryPatientDetail(param));
			resultMap.put("doctorDetail", doctorService.queryDoctorDetail(param));
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	@RequestMapping(value = "/anon/saveReservation", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveReservation(ReservationVo reservationVo,
			HttpServletRequest request) throws IOException {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("保存信息，参数：" + requestMap.toString());
			}
			reservationService.saveReservation(reservationVo);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("sourceID", reservationVo.getId());
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	@RequestMapping(value = "/anon/modifyReservation", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String modifyReservation(HttpServletRequest request,
			ReservationVo reservationVo) throws IOException {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改信息，参数：" + requestMap.toString());
			}
			reservationService.modifyReservation(reservationVo);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}
