package com.netbull.shop.patient.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.vo.Constant;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;
import com.netbull.shop.patient.service.PatientService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("patientController")
public class PatientController {

	@Resource
	private PatientService patientService;
	
	@RequestMapping(value = "/patientList.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = patientService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("patientName"));
				list.add(map.get("patientSex"));
				list.add(map.get("patientAge"));
				list.add(map.get("patientCard"));
				list.add(map.get("contactPhone"));
				list.add(map.get("createTime").toString());
				list.add(map.get("patientID"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/patient.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		
		return "patient/patientList";
	}
	
	@RequestMapping(value="/patient/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Patient patient,HttpServletRequest request,HttpServletResponse response) {
		JSONObject json=new JSONObject();
		json.put("flag", false);
		Picture picture=new Picture();
		picture.setObjectID(patient.getPatientID());
		picture.setObjectType(Constant.IMAGE_PATIENT);
		picture.setPicUrl(patient.getImg());
		picture.setPicDesc(Constant.IMAGE_PATIENT_DESC);
		if(patient.getPatientID()>0){
			patientService.update(patient);
			//修改图片信息
			int count=patientService.updatePicture(picture);
			if(count<1){
				patientService.savePicture(picture);
			}
			json.put("flag", true);
		}else{
			Patient pat=patientService.findById(patient);
			if(!StringTools.isEmpty(pat.getPatientCard())){
				json.put("msg", "该病人已存在不能重复添加!");
			}else{
				patient.setCreateTime(new Date());
				patientService.save(patient);
				Patient newPatient=patientService.findById(patient);
				picture.setObjectID(newPatient.getPatientID());
				//保存图片信息
				patientService.savePicture(picture);
				json.put("flag", true);
			}
		}
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/patient/initAddOrUpdate")
	public String initAddOrUpdate(Patient patient,HttpServletRequest request,HttpServletResponse response) {
		Patient pat=new Patient();
		if(patient.getPatientID()>0){
			pat=patientService.findById(patient);
			Picture picture=new Picture();
			picture.setObjectID(pat.getPatientID());
			picture.setObjectType(Constant.IMAGE_PATIENT);
			picture.setPicDesc(Constant.IMAGE_PATIENT_DESC);
			//查询图片
			String img=patientService.findImg(picture);
			pat.setImg(img);
		}
		request.setAttribute("pat",pat);
		return "patient/initAddOrUpdate";
	}
	
	

	@RequestMapping(value="/patient/showPatient")
	public String showHospital(Patient patient,HttpServletRequest request,HttpServletResponse response) {
		if(patient.getPatientID()>0){
			Patient pat=patientService.findById(patient);
			request.setAttribute("pat",pat);
		}
		return "patient/showPatient";
	}
	
	@RequestMapping(value="/patient/del")
	@ResponseBody
	public String handleSeletedVillage(Patient patient,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", true);
		try {
			int count=	patientService.del(patient);
			if(count>0){
				json.put("flag", true);
			}
		} catch (Exception e) {
			json.put("flag", false);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/fileUpload")
	@ResponseBody
	public String fileUpload(@RequestParam MultipartFile[] imgFile,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", false);
		String path="";
		String newFileName="";
		String uuid="";
		try {
			 for(MultipartFile myfile : imgFile){ 
			 	uuid=UUID.randomUUID().toString().replaceAll("-", "");
				newFileName=uuid+".jpg";
				path= ConfigLoadUtil.loadConfig().getPropertie("upload_dir"); 
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(path,newFileName));
			 }
			json.put("name", newFileName);
			json.put("flag", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	
	
	/**
	 * 读取文件
	 * @throws IOException
	 */
	@RequestMapping(value="/showImg")
	@ResponseBody
	public void showIdCardImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String fileName=request.getParameter("fileName");
		String dir= ConfigLoadUtil.loadConfig().getPropertie("upload_dir"); 
	 	String path=dir+ File.separator + fileName;
	 	//读取文件流
	 	File file = new File(path) ; // 实例化File类的对象
	 	BufferedImage output=null;
	 	try {
		     output = ImageIO.read(file);
		 }catch (IOException e) {
		     e.printStackTrace();
		 }
		// 禁止图像缓存。
		 response.setHeader("Pragma", "no-cache");
		 response.setHeader("Cache-Control", "no-cache");
		 response.setDateHeader("Expires", 0);
		 response.setContentType("image/jpeg");
		 ImageIO.write(output, "JPEG", response.getOutputStream());  
	}
	
	@RequestMapping(value="/patient/get/idcard")
	@ResponseBody
	public String loadByIdCard(String patientCard, HttpServletRequest request,HttpServletResponse response) {
		Patient obj = patientService.findByPatientCard(patientCard);
		return JSON.toJSONString(obj == null ? "" : obj);
	}
}
