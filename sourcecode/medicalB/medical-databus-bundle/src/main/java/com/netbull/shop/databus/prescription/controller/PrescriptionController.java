package com.netbull.shop.databus.prescription.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.databus.prescription.dto.DoctorCreateResp;
import com.netbull.shop.databus.prescription.dto.DoctorDetailResp;
import com.netbull.shop.databus.prescription.dto.DoctorQueryReq;
import com.netbull.shop.databus.prescription.dto.DoctorQueryResp;
import com.netbull.shop.databus.prescription.dto.DrugQueryReq;
import com.netbull.shop.databus.prescription.dto.DrugQueryResp;
import com.netbull.shop.databus.prescription.dto.NearShopBuyReq;
import com.netbull.shop.databus.prescription.dto.NearShopBuyResp;
import com.netbull.shop.databus.prescription.dto.PatientDetailResp;
import com.netbull.shop.databus.prescription.dto.PatientPrescription;
import com.netbull.shop.databus.prescription.dto.PatientQueryReq;
import com.netbull.shop.databus.prescription.dto.PatientQueryResp;
import com.netbull.shop.databus.prescription.dto.PrescriptionDto;
import com.netbull.shop.databus.prescription.model.PrescriptionBuyRecord;
import com.netbull.shop.databus.prescription.model.PrescriptionRelation;
import com.netbull.shop.databus.prescription.service.PrescriptionService;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;

/**
 * 处方Controller
 * 
 * @author Ade
 */
@Controller("prescriptionController")
public class PrescriptionController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private PrescriptionService prescriptionService;

	/**
	 * 新增处方-医生端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/doctor/prescription/create" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String createForDoctor(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionDto dto = JSON.parseObject(req, PrescriptionDto.class);
		DoctorCreateResp resp = prescriptionService.savePrescriptionDto(dto);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 处方列表-医生端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/doctor/prescription/list" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String listForDoctor(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		DoctorQueryReq query = JSON.parseObject(req, DoctorQueryReq.class);
		DoctorQueryResp resp = prescriptionService.doctorPrescriptionPagination(query);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 处方详情-医生端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/doctor/prescription/detail" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String detailForDoctor(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionDto dto = JSON.parseObject(req, PrescriptionDto.class);
		DoctorDetailResp resp = prescriptionService.findPrescriptionDtoById(dto.getId());
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 删除处方-医生端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/doctor/prescription/del" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String delForDoctor(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionDto dto = JSON.parseObject(req, PrescriptionDto.class);
		int cnt = prescriptionService.delPrescriptionById(dto.getId());

		JSONObject rslt = new JSONObject();
		rslt.put("code", cnt > 0 ? Constants.SUCCESS : Constants.FAIL);
		rslt.put("msg", cnt > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
		return JSON.toJSONString(rslt);
	}
	
	/**
	 * 更新处方-医生端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/doctor/prescription/update" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateForDoctor(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionDto dto = JSON.parseObject(req, PrescriptionDto.class);
		int cnt = prescriptionService.updatePrescriptionDto(dto);
		logger.info("cnt=" + cnt);
		
		JSONObject rslt = new JSONObject();
		rslt.put("code", cnt > 0 ? Constants.SUCCESS : Constants.FAIL);
		rslt.put("msg", cnt > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
		return JSON.toJSONString(rslt);
	}
	
	/**
	 * 处方列表 - 患者端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/patient/prescription/list" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String listForpatient(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PatientQueryReq query = JSON.parseObject(req, PatientQueryReq.class);
		PatientQueryResp resp = prescriptionService.patientPrescriptionPagination(query);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 处方详情 - 患者端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/patient/prescription/detail" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String detailForpatient(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PatientPrescription query = JSON.parseObject(req, PatientPrescription.class);
		PatientDetailResp resp = prescriptionService.findPatientPrescriptionDto(query.getId());
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 删除处方 - 患者端
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/patient/prescription/del" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String delForpatient() throws Exception {
		//TODO
		return JSON.toJSONString(null);
	}
	
	/**
	 * 处方-患者-关联绑定
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/prescription/bind" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String bind(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionRelation relation = JSON.parseObject(req, PrescriptionRelation.class);
		int cnt = prescriptionService.savePrescriptionRelation(relation);
		logger.info("cnt=" + cnt);
		
		JSONObject rslt = new JSONObject();
		rslt.put("code", cnt > 0 ? Constants.SUCCESS : Constants.FAIL);
		rslt.put("msg", cnt > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
		return JSON.toJSONString(rslt);
	}
	
	/**
	 * 药品检索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/prescription/drug/query" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String drugQuery(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		DrugQueryReq query = JSON.parseObject(req, DrugQueryReq.class);
		DrugQueryResp resp = prescriptionService.drugQueryPagination(query);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 处方-附近可购买药店查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/prescription/buy/nearshops" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String nearshops(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		NearShopBuyReq query = JSON.parseObject(req, NearShopBuyReq.class);
		NearShopBuyResp resp = prescriptionService.queryNearShopBuys(query);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 二维码生成
	 */
	@RequestMapping(value = "/anon/qrcode/encode" , produces="image/png")
	public void qrcode(HttpServletRequest reqest, HttpServletResponse response) throws Exception {
		String content = reqest.getParameter("content");
		logger.info("content=" + content);
		
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
	}
	
	/**
	 * 患者处方-药店, 购买记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anon/patient/prescription/buy/record" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String buyRecord(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PrescriptionBuyRecord recd = JSON.parseObject(req, PrescriptionBuyRecord.class);
		Resp resp = prescriptionService.saveBuyRecord(recd);
		return JSON.toJSONString(resp);
	}
}
