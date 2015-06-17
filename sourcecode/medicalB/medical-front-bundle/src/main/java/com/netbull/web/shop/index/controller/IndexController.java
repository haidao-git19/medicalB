package com.netbull.web.shop.index.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.util.JsonUtils;
import com.netbull.web.shop.index.entity.Floor;
import com.netbull.web.shop.index.service.IndexService;

@Controller
public class IndexController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private IndexService indexService;
	
	@RequestMapping(value="/anon/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "shop/index";
	}
	
	@RequestMapping(value="/anon/index/floor")
	public String floor(HttpServletRequest request, HttpServletResponse response) {
		List<Floor> floors = indexService.queryFloors();
		request.setAttribute("floors", floors);
		return "shop/floor";
	}
	
	/**
	 * 查询首页海报图
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/posterList")
	@ResponseBody
	public List<Map> posterList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String posterUrl = ConfigLoadUtil.loadConfig().getPropertie("queryPoster");
		String posterResp = HttpXmlUtil.doPost(posterUrl, requestMap);
		Map<String,Object> posterResultMap=JsonUtils.json2Map(posterResp);
		String posterCode=(String) posterResultMap.get("code");
		List<Map> posterList=null;
		if(StringTools.equals(posterCode, "0")){
			posterList=(List<Map>) posterResultMap.get("posterList");
		}
		return posterList;
	}
	
	/**
	 * 查询合作医院
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryShopList")
	@ResponseBody
	public List<Map> queryShopList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryShopList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String shopUrl = ConfigLoadUtil.loadConfig().getPropertie("queryShopList");
		String shopResp = HttpXmlUtil.doPost(shopUrl, requestMap);
		Map<String,Object> shopResultMap=JsonUtils.json2Map(shopResp);
		String posterCode=(String) shopResultMap.get("code");
		List<Map> shopList=null;
		if(StringTools.equals(posterCode, "0")){
			shopList=(List<Map>) shopResultMap.get("shopList");
		}
		return shopList;
	}
	
	/**
	 * 查询推荐或促销的商品列表；
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryRecomGoodsList")
	@ResponseBody
	public List<Map> queryRecomGoodsList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryRecomGoodsList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String goodsUrl = ConfigLoadUtil.loadConfig().getPropertie("queryRecomGoodsList");
		String goodsResp = HttpXmlUtil.doPost(goodsUrl, requestMap);
		Map<String,Object> goodsResultMap=JsonUtils.json2Map(goodsResp);
		String posterCode=(String) goodsResultMap.get("code");
		List<Map> goodsList=null;
		if(StringTools.equals(posterCode, "0")){
			goodsList=(List<Map>) goodsResultMap.get("goodsList");
		}
		return goodsList;
	}
	
	
	/**
	 * 查询药品对应的功效列表；
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryFilter")
	@ResponseBody
	public List<Map> queryFilter(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryFilter method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String filterUrl = ConfigLoadUtil.loadConfig().getPropertie("queryFilter");
		String filterResp = HttpXmlUtil.doPost(filterUrl, requestMap);
		Map<String,Object> filterResultMap=JsonUtils.json2Map(filterResp);
		String posterCode=(String) filterResultMap.get("code");
		List<Map> filterList=null;
		if(StringTools.equals(posterCode, "0")){
			filterList=(List<Map>) filterResultMap.get("filter");
		}
		return filterList;
	}
	
	/**
	 * 根据疾病分类查询对应的药品信息；
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryGoodsListByKey")
	@ResponseBody
	public List<Map> queryGoodsListByKey(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsListByKey method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String goodsUrl = ConfigLoadUtil.loadConfig().getPropertie("queryGoodsListByKey");
		String goodsResp = HttpXmlUtil.doPost(goodsUrl, requestMap);
		Map<String,Object> goodsResultMap=JsonUtils.json2Map(goodsResp);
		String posterCode=(String) goodsResultMap.get("code");
		List<Map> goodsList=null;
		if(StringTools.equals(posterCode, "0")){
			goodsList=(List<Map>) goodsResultMap.get("goodsList");
		}
		return goodsList;
	}
}
