package com.netbull.shop.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.netbull.shop.common.vo.JSONMessage;
import com.netbull.shop.common.vo.PosterVo;
import com.netbull.shop.doctor.service.DoctorService;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.PosterDao;
import com.netbull.shop.manage.weixin.service.PosterService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.JsonUtils;

@Controller
public class PosterController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=PosterDao.class.getName();
	
	@Autowired
	private PosterService posterService;
	@Resource
	private DoctorService doctorService;
	
	/**
	 * 海报列表
	 * @return
	 */
	@RequestMapping("/poster/posterList")
	public String posterList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterList method.");
		}
		return "poster/posterList";
	}
	
	/**
	 * 分页查询海报信息
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/poster/queryPosterList")
	@ResponseBody
	public  Map<String, Object> queryPosterList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = posterService.queryPosterPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","posterName","goodsCode","goodsVersion","startTime","endTime","status","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 海报编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/poster/posterEdit")
	public String posterEdit(ModelMap model,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterEdit method.");
		}
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
		PosterVo poster=null;
		String goodsInfo=null;
		if(id!=null&&id!=0){
			poster=posterService.queryPoster(id);
		}
		if(!NullUtil.isNull(poster)){
			poster.setImage(realPath+poster.getImage());
			String goodsCode=poster.getGoodsCode();
			String goodsVersion=poster.getGoodsVersion();
			if(StringUtil.isEmpty(goodsCode)&&StringUtil.isEmpty(goodsVersion)){
				goodsInfo=goodsCode+"_"+goodsVersion;
			}
		}
		model.addAttribute("poster", poster);
		model.addAttribute("goodsInfo", goodsInfo);
		return "poster/posterEdit";
	}
	
	
	
	/**
	 * 保存海报
	 * @param posterVo
	 * @param id
	 */
	@RequestMapping("/poster/posterSave")
	@ResponseBody
	public String posterSave(PosterVo posterVo,Integer id,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterSave method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String goodsInfo=requestMap.get("goodsInfo");
		if(StringUtil.isEmpty(goodsInfo)){
			String[] goodsInfoArr=goodsInfo.split("_");
			posterVo.setGoodsCode(goodsInfoArr[0]);
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		if(id!=null&&id!=0){
			posterService.updatePoster(MYBATIS_PREFIX, posterVo,file);
		}else{
			posterService.savePoster(MYBATIS_PREFIX, posterVo,file);
		}
		 JSONMessage jsonM = new JSONMessage();
	     jsonM.setFlag(JSONMessage.Flag.SUCCESS);
	     jsonM.setMsg("发送消息成功");
	     return jsonM.toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryRecommendDoctorList")
	@ResponseBody
	public List<Map> queryRecommendDoctorList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryPosterList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<Map> recommDoctorList = doctorService.queryAllRecommDoctorList(requestMap);
		return recommDoctorList;
	}
	
	/**
	 * 删除海报
	 * @param id
	 */
	@RequestMapping("/poster/posterDelete")
	@ResponseBody
	public String posterDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterDelete method.");
		}
		if(id!=null&&id!=0){
			posterService.delete(MYBATIS_PREFIX, id);
		}
		JSONMessage jsonM = new JSONMessage();
	     jsonM.setFlag(JSONMessage.Flag.SUCCESS);
	     jsonM.setMsg("发送消息成功");
	     return jsonM.toString();
	}
	
}
