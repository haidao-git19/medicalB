package com.netbull.shop.manage.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.HealthKnowlege;
import com.netbull.shop.common.vo.JSONMessage;
import com.netbull.shop.manage.common.constant.Const;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.NewsDao;
import com.netbull.shop.manage.weixin.service.NewsService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.JsonUtils;

@Controller
public class NewsController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=NewsDao.class.getName();
	@Autowired
	private NewsService newsService; 
	
	/***************************************后台*************************************************/
	
	/**
	 * 健康常识列表
	 * @return
	 */
	@RequestMapping("/health/healthKnowlegeList")
	public String healthKnowlegeList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class healthKnowlegeDynamic method.");
		}
		return "healthKnowlege/healthKnowlegeList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/health/queryHealthKnowlegeList")
	@ResponseBody
	public Map<String, Object> queryHealthKnowlegeList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryHealthKnowlegeList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = newsService.queryHealthKnowlegePage(iDisplayStart, iDisplayLength, requestMap);//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","title","createTime","updateTime","status","detailUrl");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 健康常识编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/health/healthKnowlegeEdit")
	public String HealthKnowlegeEdit(ModelMap model,Integer id,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class HealthKnowlegeEdit method.");
		}
		String realPath = ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
		HealthKnowlege healthKnowlege=null;
		if(id!=null&&id!=0){
			healthKnowlege=newsService.queryHealthKnowlege(id);
			healthKnowlege.setCoverImage(realPath+healthKnowlege.getCoverImage());
			model.addAttribute("healthKnowlege", healthKnowlege);
		}
		return "healthKnowlege/healthKnowlegeEdit";
	}
	
	/**
	 * 保存健康常识
	 * @param healthKnowlege
	 * @param request
	 */
	@RequestMapping("/health/healthKnowlegeSave")
	@ResponseBody
	public String healthKnowlegeSave(HealthKnowlege healthKnowlege,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class HealthKnowlegeEdit method.");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		
		Integer id=healthKnowlege.getId();
		if(id!=null&&id!=0){
			newsService.updateHealthKnowlege(healthKnowlege, file);
		}else{
			newsService.saveHealthKnowlege(healthKnowlege, file);
		}
		
		return JsonUtils.obj2Json(healthKnowlege.getId());
	}
	
	/**
	 * 删除健康常识
	 * @param id
	 */
	@RequestMapping("/health/healthKnowlegeDelete")
	@ResponseBody
	public String healthKnowlegeDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class healthKnowlegeDelete method.");
		}
		if(id!=null&&id!=0){
			newsService.delete(MYBATIS_PREFIX, id);
		}
		JSONMessage jsonM = new JSONMessage();
        jsonM.setFlag(JSONMessage.Flag.SUCCESS);
		jsonM.setMsg("发送消息成功");
		return jsonM.toString();
	}
	
	/**
	 * 获取详情模板页面
	 * @param req
	 * @param menuId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/health/createTemplate")
	public String createTemplate(ModelMap model,Integer id) throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class createTemplate method.");
		}
		HealthKnowlege healthKnowlege=null;
		if(id!=null&&id!=0){
			healthKnowlege=newsService.queryHealthKnowlege(id);
			
			String createTime=healthKnowlege.getCreateTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date=sdf.parse(createTime);
			String now=sdf.format(date);
			healthKnowlege.setCreateTime(now);
			
			String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
			healthKnowlege.setCoverImage(realPath+healthKnowlege.getCoverImage());
		}
		model.addAttribute("healthKnowlege", healthKnowlege);
		return "healthKnowlege/healthKnowlegeTemplate";
	} 
	
	/**
	 * 创建详情模板页面
	 * @param houseId
	 * @param detail
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/health/createDetail")
	@ResponseBody
	public Map<String,String> createDetail(Integer id,HttpServletRequest request) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class createDetail method.");
		}
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		
		HealthKnowlege healthKnowlege=null;
		if(id!=null&&id!=0){
        	healthKnowlege=newsService.queryHealthKnowlege(id);
		}
		
		Map<String,String> resultMap=new HashMap<String, String>();
		if(NullUtil.isNull(healthKnowlege)){
			resultMap.put("error", "操作失败!");
			return resultMap;
		}
		
		String detail=requestMap.get("detail");
		String realPath = ConfigLoadUtil.loadConfig().getPropertie("templateUploadPath");
		String fileName = StringTools.getBillno()+".html";
        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(detail.getBytes("UTF-8")) , new File(realPath, fileName)); 
        String url=ConfigLoadUtil.loadConfig().getPropertie("templateUrl")+fileName;
        
        
        healthKnowlege.setDetailUrl(url);
        newsService.update(MYBATIS_PREFIX, healthKnowlege);
        resultMap.put("success", "操作成功!");
		return resultMap;
	}
	
	/***************************************微信*************************************************/
	
	/**
	 * 健康常识首页
	 * @return
	 */
	@RequestMapping(value="/anon/healthKnowlegeDynamic", produces="text/plain;charset=utf-8")
	public String healthKnowlegeDynamic(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class healthKnowlegeDynamic method.");
		}
		return "healthKnowlege/healthKnowlegeDynamic";
	}
	
	/**
	 * 查询健康常识动态列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/queryHealthKnowlegeDynamics", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryHealthKnowlegeDynamics(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryHealthKnowlegeDynamics method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Map paramter = new HashMap();
		paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Const.QUERY_HEALTH_KNOWLEGE_PAGE_SIZE);
		paramter.put("limit", Const.QUERY_HEALTH_KNOWLEGE_PAGE_SIZE);
		paramter.put("sortKey", StringUtil.getString(requestMap.get("sortKey")));
		List<Map> detailList = this.newsService.queryDetailList(paramter);
		
		for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			if(!NullUtil.isNull(map.get("coverImage"))){
				map.put("coverImage", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") +map.get("coverImage"));
			}
		}
		
		resultMap.put("detailList", detailList);
		return JsonUtils.obj2Json(resultMap);
	}
	
//	/**
//	 * 健康常识详情页
//	 * @return
//	 */
//	@RequestMapping(value="/anon/healthKnowlegeDetail", produces="text/plain;charset=utf-8")
//	public String healthKnowlegeDetail(){
//		if (logger.isDebugEnabled()) {
//			logger.debug("enter the " + this.getClass().getName() + " class healthKnowlegeDetail method.");
//		}
//		return "healthKnowlege/healthKnowlegeDetail";
//	}
//	
//	/**
//	 * 查询健康常识
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value="/anon/queryHealthKnowlege", produces="text/plain;charset=utf-8")
//	@ResponseBody
//	public String queryHealthKnowlege(Integer id){
//		if (logger.isDebugEnabled()) {
//			logger.debug("enter the " + this.getClass().getName() + " class queryHealthKnowlege method.");
//		}
//		HealthKnowlege healthKnowlege=null;
//		if(id!=null&&id!=0){
//			healthKnowlege=healthKnowlegeService.queryHealthKnowlege(id);
//		}
//		return JsonUtils.obj2Json(healthKnowlege);
//	}
}
