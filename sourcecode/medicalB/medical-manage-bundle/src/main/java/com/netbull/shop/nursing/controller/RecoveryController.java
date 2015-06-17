package com.netbull.shop.nursing.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.nursing.dao.RecoveryDao;
import com.netbull.shop.nursing.entity.Recovery;
import com.netbull.shop.nursing.service.RecoveryService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class RecoveryController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private RecoveryService recoveryService;
	
	@RequestMapping(value="/recovery")
	public String recoveryList(HttpServletRequest request){
		return "recovery/recoveryList";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/recovery/query")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = recoveryService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("title"));
				list.add(map.get("publisher"));
				list.add(map.get("type"));
				list.add(map.get("userID"));
				list.add(map.get("createTime"));
				list.add(map.get("updateTime"));
				list.add(map.get("url"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/recovery/initAddOrUpdate")
	public String initAddOrUpdate(ModelMap model,Integer id,HttpServletRequest request){
		Recovery recovery=null;
		if(id!=null&&id>0){
			recovery=recoveryService.get(RecoveryDao.class.getName(), id);
		}
		model.addAttribute("recovery", recovery);
		return "recovery/recoveryEdit";
	}
	
	@RequestMapping(value="/recovery/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(Integer id,Recovery recovery,HttpServletRequest request){
		JSONObject json=new JSONObject();
		int count=0;
		if(id!=null&&id>0){
			count=recoveryService.updateRecovery(recovery);
		}else{
			count=recoveryService.saveRecovery(recovery);
		}
		json.put("flag", count==1);
		if(count==1){
			json.put("id", recovery.getId());
		}
		return json.toString();
	}
	
	@RequestMapping(value="/recovery/del")
	@ResponseBody
	public void del(Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			Recovery recovery=recoveryService.get(RecoveryDao.class.getName(), id);
			if(!NullUtil.isNull(recovery)){
				recovery.setIsShow(1);
				recoveryService.updateRecovery(recovery);
			}
		}
	}
	
	@RequestMapping("/recovery/createTemplate")
	public String createTemplate(ModelMap model,Integer id) throws ParseException {
		Recovery recovery=null;
		if(id!=null&&id!=0){
			recovery=recoveryService.get(RecoveryDao.class.getName(), id);
			
			String publishTime=recovery.getUpdateTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date=sdf.parse(publishTime);
			String now=sdf.format(date);
			recovery.setCreateTime(now);
		}
		model.addAttribute("recovery", recovery);
		return "template/recoveryDetail";
	} 
	
	@RequestMapping("/recovery/createDetail")
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
        
        Recovery recovery=null;
		if(id!=null&&id!=0){
			recovery=recoveryService.get(RecoveryDao.class.getName(), id);
		}
		if(NullUtil.isNull(recovery)){
			resultMap.put("error", "操作失败!");
			return resultMap;
		}
		recovery.setUrl(url);
        recoveryService.updateRecovery(recovery);
        resultMap.put("success", "操作成功!");
		return resultMap;
	}
}
