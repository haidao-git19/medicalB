package com.netbull.shop.newborn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.vo.Constant;
import com.netbull.shop.newborn.entity.NewBorn;
import com.netbull.shop.newborn.service.NewBornService;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;
import com.netbull.shop.patient.service.PatientService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("newBornController")
public class NewBornController {

	@Resource
	private NewBornService newBornService;
	@Resource
	private PatientService patientService;
	@RequestMapping(value = "/newBornList.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = newBornService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("babiName"));
				list.add(map.get("mamu"));
				list.add(map.get("mamuIDCard"));
				list.add(map.get("bornAddrType"));
				list.add(map.get("bornOrgName"));
				list.add(map.get("id"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/newborn.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		return "newborn/newBornList";
	}
	
	@RequestMapping(value="/newBorn/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(NewBorn newBorn,HttpServletRequest request,HttpServletResponse response) {
		JSONObject json=new JSONObject();
		json.put("flag", false);
		Picture picture=new Picture();
		picture.setObjectID(newBorn.getId());
		picture.setObjectType(Constant.IMAGE_NEW_BORN);
		picture.setPicUrl(newBorn.getImg());
		picture.setPicDesc(Constant.IMAGE_NEW_BORN_DESC);
		if(newBorn.getId()>0){
			newBornService.update(newBorn);
			//修改图片信息
			int count=patientService.updatePicture(picture);
			if(count<1){
				patientService.savePicture(picture);
			}
			json.put("flag", true);
		}else{
			NewBorn nb=newBornService.findById(newBorn);
			if(!StringTools.isEmpty(nb.getBabiName())){
				json.put("msg", "该新生儿已存在不能重复添加!");
			}else{
				newBornService.save(newBorn);
				NewBorn nbn=newBornService.findById(newBorn);
				picture.setObjectID(nbn.getId());
				patientService.savePicture(picture);
				json.put("flag", true);
			}
		}
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/newBorn/initAddOrUpdate")
	public String initAddOrUpdate(NewBorn newBorn,HttpServletRequest request,HttpServletResponse response) {
		NewBorn nb=new NewBorn();
		if(newBorn.getId()>0){
			nb=newBornService.findById(newBorn);
			//查询图片
			Picture picture=new Picture();
			picture.setObjectID(newBorn.getId());
			picture.setObjectType(Constant.IMAGE_NEW_BORN);
			picture.setPicUrl(newBorn.getImg());
			picture.setPicDesc(Constant.IMAGE_NEW_BORN_DESC);
			String img=patientService.findImg(picture);
			nb.setImg(img);
		}
		request.setAttribute("nb",nb);
		return "newborn/initAddOrUpdate";
	}
	
	

	@RequestMapping(value="/newBorn/showNewBorn")
	public String showNewBorn(NewBorn newBorn,HttpServletRequest request,HttpServletResponse response) {
		if(newBorn.getId()>0){
			NewBorn nb=newBornService.findById(newBorn);
			request.setAttribute("nb",nb);
		}
		return "newborn/showNewBorn";
	}
	
	@RequestMapping(value="/newBorn/del")
	@ResponseBody
	public String del(NewBorn newBorn,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", true);
		try {
			int count=	newBornService.del(newBorn);
			if(count>0){
				json.put("flag", true);
			}
		} catch (Exception e) {
			json.put("flag", false);
		}
		return json.toString();
	}
	
	
	
}
