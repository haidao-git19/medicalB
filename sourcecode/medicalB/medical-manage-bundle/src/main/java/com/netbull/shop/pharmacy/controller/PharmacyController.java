package com.netbull.shop.pharmacy.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.vo.Constant;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.nursing.dao.RecoveryDao;
import com.netbull.shop.nursing.entity.Recovery;
import com.netbull.shop.patient.entity.Picture;
import com.netbull.shop.pharmacy.entity.Pharmacy;
import com.netbull.shop.pharmacy.service.PharmacyService;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("pharmacyController")
public class PharmacyController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private PharmacyService pharmacyService;
	@Resource
	private AreaService areaService; 
	
	
	@RequestMapping(value = "/pharmacyList.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = pharmacyService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		 List<Area> areaLst=areaService.handleAllArea();
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("shopID"));
				list.add(map.get("shopName"));
			
				//设置本地网
				String latnId="";
				for (int i = 0; i < areaLst.size(); i++) {
					if(String.valueOf(areaLst.get(i).getAreaID()).equals(map.get("latnId").toString())){
						latnId=areaLst.get(i).getAreaName();
					}
				}
				if(!StringTools.isEmpty(latnId)){
					list.add(latnId);
				}else{
					list.add(map.get("latnId"));
				}
				list.add(map.get("type"));
				list.add(map.get("address"));
				list.add(map.get("url"));
				aaData.add(list);
			}
		  
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/pharmacy.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		return "pharmacy/pharmacyList";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/pharmacy/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Integer shopID,Pharmacy pharmacy,HttpServletRequest request,HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		pharmacy.setCreator(currUser.getId());
		
		JSONObject json=new JSONObject();
		int count=0;
		if(shopID!=null&&shopID>0){
			count=pharmacyService.update(pharmacy,file);
		}else{
			Map parameter=new HashMap();
			parameter.put("shopName", pharmacy.getShopName());
			Pharmacy ph=pharmacyService.findByParam(parameter);
			if(!NullUtil.isNull(ph)){
				json.put("msg", "该药店已存在不能重复添加!");
			}else{
				pharmacy.setCrtDate(new Date());
				count=pharmacyService.save(pharmacy, file);
			}
		}
		json.put("flag", count==1);
		json.put("pharmacyID", pharmacy.getShopID());
		return json.toString();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/pharmacy/initAddOrUpdate")
	public String initAddOrUpdate(ModelMap model,Integer shopID,HttpServletRequest request) {
		Pharmacy ph=new Pharmacy();
		if(shopID!=null&&shopID>0){
			Map param=new HashMap();
			param.put("shopID", shopID);
			ph=pharmacyService.findByParam(param);
		}
		model.addAttribute("ph", ph);
		return "pharmacy/_initAddOrUpdate";
	}
	
	

//	@RequestMapping(value="/patient/showPharmacy")
//	public String showPharmacy(Pharmacy pharmacy,HttpServletRequest request,HttpServletResponse response) {
//		if(pharmacy.getShopID()>0){
//			Pharmacy ph=pharmacyService.findById(pharmacy);
//			request.setAttribute("ph",ph);
//		}
//		return "pharmacy/showPharmacy";
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/pharmacy/del")
	@ResponseBody
	public String del(Integer shopID,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		int count=0;
		Pharmacy pharmacy=new Pharmacy();
		if(shopID!=null&&shopID>0){
			Map param=new HashMap();
			param.put("shopID", shopID);
			pharmacy=pharmacyService.findByParam(param);
		}
		if(!NullUtil.isNull(pharmacy)){
			pharmacy.setState(-1);
			count=pharmacyService.update(pharmacy, null);
		}
		
		json.put("flag", count==1);
		return json.toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/pharmacy/createTemplate")
	public String createTemplate(ModelMap model,Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			Map param=new HashMap();
			param.put("shopID", id);
			Pharmacy pharmacy=pharmacyService.findByParam(param);
			model.addAttribute("pharmacy", pharmacy);
			return "template/pharmacyTemplate";
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/pharmacy/createDetail")
	@ResponseBody
	public Map<String,String> createDetail(Integer id,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		Map<String,String> resultMap=new HashMap<String, String>();
		
		String detail=requestMap.get("detail");
		String realPath = ConfigLoadUtil.loadConfig().getPropertie("templateUploadPath");
		String fileName = StringTools.getBillno()+".html";
        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(detail.getBytes("UTF-8")) , new File(realPath, fileName)); 
        String url=ConfigLoadUtil.loadConfig().getPropertie("templateUrl")+fileName;
        
        if(logger.isDebugEnabled()){
        	logger.info("模板生成页面上传路径>>>>>>>"+realPath);
        	logger.info("上传文件>>>>>>>>"+url);
        }
        
       	Pharmacy pharmacy=null;
		if(id!=null&&id!=0){
			Map parameter=new HashMap();
			parameter.put("shopID", id);
			pharmacy=pharmacyService.findByParam(parameter);
		}
		if(NullUtil.isNull(pharmacy)){
			resultMap.put("error", "操作失败!");
			return resultMap;
		}
		pharmacy.setUrl(url);
		pharmacyService.update(pharmacy, null);
        resultMap.put("success", "操作成功!");
		return resultMap;
	}
}
