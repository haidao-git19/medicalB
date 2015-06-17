package com.netbull.shop.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.netbull.shop.common.vo.CatalogGoodsVo;
import com.netbull.shop.common.vo.CatalogVo;
import com.netbull.shop.common.vo.GoodsFilter;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.service.CatalogService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class CatalogController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	private static final String ATTRNAME="风格";
	
	private static final int count=3;
	
	@Autowired
	private CatalogService catalogService; 
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 分类类目商品列表
	 * @return
	 */
	@RequestMapping("/catalog/catalogList")
	public String catalogList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class catalogList method.");
		}
		return "catalog/catalogList";
	}
	
	/**
	 * 分页查询类目商品列表
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/catalog/queryCatalogGoodsList")
	@ResponseBody
	public  Map<String, Object> queryCatalogGoodsList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryCatalogGoodsList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = catalogService.queryCatalogGoodsPage(iDisplayStart, iDisplayLength, requestMap);//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","catalogCode","catalogName","attrValue","goodsCode","goodsVersion","goodsName","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 类目商品编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/catalog/catalogGoodsEdit")
	public String catalogGoodsEdit(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class catalogGoodsEdit method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
		CatalogVo catalogVo=null;
		if(StringUtil.isEmpty(requestMap.get("catalogCode"))){
			catalogVo=catalogService.queryCatalog(requestMap);
		}
		if(!NullUtil.isNull(catalogVo)){
			
			List<CatalogGoodsVo> catalogList=catalogService.queryCatalogsByParams(requestMap);
			if(!NullUtil.isNull(catalogList)){
				resultMap.put("catalogCode", catalogVo.getCatalogCode());
				resultMap.put("catalogName", catalogVo.getCatalogName());
				resultMap.put("attrValue", catalogVo.getAttrValue());
				resultMap.put("seq", catalogVo.getSeq());
				for(CatalogGoodsVo catalog:catalogList){
					resultMap.put("coverImage_"+catalog.getOrder(), realPath+catalog.getCoverImage());
					resultMap.put("goods_"+catalog.getOrder(), catalog.getGoodsCode()+"_"+catalog.getGoodsVersion());
				}
			}
		}
		model.addAttribute("resultMap", resultMap);
		return "catalog/catalogEdit";
	}
	
	/**
	 * 类目商品保存
	 * @param request
	 */
	@RequestMapping("/catalog/catalogGoodsSave")
	@ResponseBody
	public void catalogSave(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class catalogSave method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file=null;
		CommonsMultipartFile[] cmfArr=new CommonsMultipartFile[count];
		for(int i=0;i<count;i++){
			file = (CommonsMultipartFile) multipartRequest.getFile("upload_"+i);
			cmfArr[i]=file;
		}
		
		CatalogVo catalogVo=catalogService.queryCatalog(requestMap);
		if(NullUtil.isNull(catalogVo)){
			if(StringTools.equals(requestMap.get("catalogName"), "新品推荐")){
				requestMap.put("req", StringUtil.getString(0));
			}
			catalogService.saveCatalog(requestMap);
			for(int i=0;i<count;i++){
				if(StringUtil.isEmpty(requestMap.get("goods_"+i))){
					String[] goodsInfoArr=requestMap.get("goods_"+i).split("_");
					requestMap.put("goodsCode", goodsInfoArr[0]);
					requestMap.put("goodsVersion", goodsInfoArr[1]);
					requestMap.put("order", StringUtil.getString(i));
					catalogService.saveCatalogGoods(requestMap, cmfArr[i]);
				}
			}
		}else{
			if(StringTools.equals(requestMap.get("catalogName"), "新品推荐")){
				requestMap.put("req", StringUtil.getString(0));
			}
			catalogService.updateCatalog(requestMap);
			for(int i=0;i<count;i++){
				String order=StringUtil.getString(i);
				requestMap.put("order", order);
				CatalogGoodsVo catalogGoodsVo=catalogService.queryCatalogGoods(requestMap);
				
				if(NullUtil.isNull(catalogGoodsVo)&&StringUtil.isEmpty(requestMap.get("goods_"+i))){
					String[] goodsInfoArr=requestMap.get("goods_"+i).split("_");
					requestMap.put("goodsCode", goodsInfoArr[0]);
					requestMap.put("goodsVersion", goodsInfoArr[1]);
					catalogService.saveCatalogGoods(requestMap, cmfArr[i]);
				}else if(!NullUtil.isNull(catalogGoodsVo)&&StringUtil.isEmpty(requestMap.get("goods_"+i))){
					String[] goodsInfoArr=requestMap.get("goods_"+i).split("_");
					requestMap.put("goodsCode", goodsInfoArr[0]);
					requestMap.put("goodsVersion", goodsInfoArr[1]);
					catalogService.updateCatalogGoods(requestMap, cmfArr[i]);
				}
				
			}
			
		}
	}
	
	/**
	 * 删除类目商品
	 * @param id
	 */
	@RequestMapping("/catalog/catalogGoodsDelete")
	@ResponseBody
	public void catalogGoodsDelete(Integer id,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class catalogSave method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String catalogCode=requestMap.get("catalogCode");
		if(id!=null&&id!=0&&StringUtil.isEmpty(catalogCode)){
			catalogService.deleteCatalogGoods(id);
			List<CatalogGoodsVo> catalogGoodsList=catalogService.queryCatalogsByParams(requestMap);
			if(NullUtil.isNull(catalogGoodsList)){
				catalogService.deleteCatalog(requestMap);
			}
		}
	}
	
	/**
	 * 查询类目商品风格属性
	 * @param request
	 * @return
	 */
	@RequestMapping("/catalog/queryAttrValues")
	@ResponseBody
	public List<GoodsFilter> queryAttrValues(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryAttrValues method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		requestMap.put("attrname", ATTRNAME);
		List<GoodsFilter> attrList=catalogService.queryCatalogGoodsFilter(requestMap);
		return attrList;
	}
	
	/**
	 * 查询所有商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/catalog/queryGoodsList")
	@ResponseBody
	public List<GoodsVo> queryGoodsList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<GoodsVo> goodsList=goodsService.queryAll(requestMap);
		return goodsList;
	}
}
