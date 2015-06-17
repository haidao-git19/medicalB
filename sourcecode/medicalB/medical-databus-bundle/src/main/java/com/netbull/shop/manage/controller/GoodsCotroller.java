package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.GoodsReport;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.GoodsAttrDC;
import com.netbull.shop.manage.common.util.GoodsDC;
import com.netbull.shop.manage.common.util.GoodsFilterDC;
import com.netbull.shop.manage.common.util.SectionFilterDC;
import com.netbull.shop.manage.weixin.service.GoodsReportService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class GoodsCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private GoodsService goodsService;
	@Autowired
	private GoodsReportService goodsReportService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryGoodsList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryGoodsList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> resultList = new ArrayList<Map>();
		
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryGoodsList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询商品列表信息，参数：" + requestMap.toString());
			}
			
			String keys = requestMap.get("keys");
			if(NullUtil.isNull(keys)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap); 
			}
			
			String[] keyAttray = keys.split("\\|");
			for (int i = 0; i < keyAttray.length; i++) {
				requestMap.put("key", keyAttray[i]);
				List<Map> goodsList = goodsService.queryGoods(requestMap);
				Map goodsMap = !NullUtil.isNull(goodsList)?goodsList.get(0):null;
				if(!NullUtil.isNull(goodsMap)){
					goodsMap.put("diseaseName", keyAttray[i]);
					resultList.add(goodsMap);
				}
			}
			
			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				Map goodsMap = (Map) iterator.next();
				goodsMap.put("attrList", GoodsAttrDC.getInstance().queryGoodsAttrList(StringUtil.getString(goodsMap.get("goodsCode"))));
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("goodsList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/queryGoodsDetail" , produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryGoodsDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询商品详细信息，参数：" + requestMap.toString());
			}
			Map<String,Object> goodsMap=GoodsDC.getInstance().queryGoodsDetail(requestMap);
			GoodsReport goodsReport=goodsReportService.queryGoodsReportByParams(requestMap);
			if(!NullUtil.isNull(goodsReport)){
				goodsMap.put("praiseNum", goodsReport.getPraiseNum());
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("goodsDetail", goodsMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryRecomGoodsList" , produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryRecomGoodsList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询推荐或促销商品列表信息，参数：" + requestMap.toString());
			}
			
			List goodsList = GoodsDC.getInstance().queryDiscountOrRecomGoods(requestMap);
			
			if(NullUtil.isNull(goodsList)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap); 
			}
			
			for (Iterator iterator = goodsList.iterator(); iterator.hasNext();) {
				Map goodsMap = (Map) iterator.next();
				goodsMap.put("attrList", GoodsAttrDC.getInstance().queryGoodsAttrList(StringUtil.getString(goodsMap.get("goodsCode"))));
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("goodsList", goodsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/anon/queryFilter" , produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryFilter(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Map _map = GoodsFilterDC.getInstance().queryFilter();
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("filter", _map.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}
