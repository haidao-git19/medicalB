package com.netbull.shop.manage.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.vo.ContentVo;
import com.netbull.shop.common.vo.JSONMessage;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.ContentDao;
import com.netbull.shop.manage.weixin.service.ContentService;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class ContentController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=ContentDao.class.getName();
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 内容记录列表页
	 * @return
	 */
	@RequestMapping("/content/contentList")
	public String contentList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class contentList method.");
		}
		return "content/contentList";
	}
	
	/**
	 * 分页查询内容记录
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/content/queryContentList")
	@ResponseBody
	public  Map<String, Object> queryContentList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryContentList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = contentService.queryContentPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","title","type","url","createTime","updateTime","state","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 查询所有可用内容记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/content/queryContents")
	@ResponseBody
	public List<ContentVo> queryContents(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryContents method.");
		}
		List<ContentVo> list=contentService.queryAllContents();
		return list;
	}
	
	/**
	 * 内容编辑页
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/contentEdit")
	public String contentEdit(ModelMap model,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class contentEdit method.");
		}
		ContentVo contentVo=new ContentVo();
		if(id!=null&&id!=0){
			contentVo=contentService.queryContent(id);
		}
		model.addAttribute("content", contentVo);
		return "content/contentEdit";
	}
	
	/**
	 * 保存内容记录
	 * @param contentVo
	 * @param id
	 */
	@RequestMapping("/content/contentSave")
	@ResponseBody
	public Map<String,Object> contentSave(ContentVo contentVo,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class contentSave method.");
		}
		if(id!=null&&id!=0){
			contentService.update(MYBATIS_PREFIX, contentVo);
		}else{
			contentService.save(MYBATIS_PREFIX, contentVo);
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", contentVo.getId());
		return map;
	}
	
	/**
	 * 删除内容记录
	 * @param id
	 */
	@RequestMapping("/content/contentDelete")
	@ResponseBody
	public String contentDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class contentDelete method.");
		}
		if(id!=null&&id!=0){
			ContentVo contentVo=contentService.queryContent(id);
			contentVo.setState("-1");
			contentService.update(MYBATIS_PREFIX, contentVo);
			JSONMessage jsonM = new JSONMessage();
			jsonM.setFlag(JSONMessage.Flag.SUCCESS);
			jsonM.setMsg("发送消息成功");
			return jsonM.toString();
		}
		return null;
	}
	
	@RequestMapping("/content/template")
	public String createTemplate(HttpServletRequest req) {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class createTemplate method.");
		}
		return "template/content";
	}
	
	@RequestMapping("/content/detail")
	@ResponseBody
	public String createDetailHtmlFile(Integer id,String detail,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class createDetailHtmlFile method.");
		}
		String realPath = ConfigLoadUtil.loadConfig().getPropertie("templateUploadPath");
		String fileName = StringTools.getBillno()+".html";
        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(detail.getBytes("UTF-8")) , new File(realPath, fileName)); 
        
        String url=ConfigLoadUtil.loadConfig().getPropertie("templateUrl")+fileName;
        if(id != null && id!=0){
        	ContentVo contentVo=contentService.queryContent(id);
        	contentVo.setUrl(url);
			contentService.update(MYBATIS_PREFIX, contentVo);
		}
		
        JSONMessage jsonM = new JSONMessage();
        jsonM.setFlag(JSONMessage.Flag.SUCCESS);
		jsonM.setMsg("发送消息成功");
		return jsonM.toString();
	}
}
