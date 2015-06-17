package com.netbull.web.shop.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.ShopConst;
import com.netbull.web.shop.goods.service.GoodsService;
import com.netbull.web.shop.goods.vo.CataNavigation;
import com.netbull.web.shop.goods.vo.CatagoryOption;
import com.netbull.web.shop.goods.vo.GoodsVo;
import com.netbull.web.shop.goods.vo.OptionKey;

@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping(value="/anon/goods/query")
	public String query_init(HttpServletRequest request, HttpServletResponse response) {
		int pageSize = getPageSize();
		int catId = StringUtil.parseInt(request.getParameter("catId"), 0);
		int scId = StringUtil.parseInt(request.getParameter("scId"), 0);
		int catScOptId = StringUtil.parseInt(request.getParameter("catScOptId"), 0);
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("catId", catId);
		request.setAttribute("scId", scId);
		request.setAttribute("catScOptId", catScOptId);
		return "goods/query_init";
	}
	
	@RequestMapping(value="/anon/goods/query/totalRecords")
	@ResponseBody
	public String totalRecords(HttpServletRequest request, HttpServletResponse response) {
		int pageSize = getPageSize();
		int pageNum = StringUtil.parseInt(request.getParameter("pageNum"), 1);
		int catId = StringUtil.parseInt(request.getParameter("catId"), 0);
		int scId = StringUtil.parseInt(request.getParameter("scId"), 0);
		int catScOptId = StringUtil.parseInt(request.getParameter("catScOptId"), 0);
		int offset = (pageNum - 1) * pageSize;
		
		//--------------------------------------
		// 构造查询参数
		//--------------------------------------
		Map<String, Object> param = new HashMap();
		param.put("catId", catId);
		param.put("scId", scId);
		param.put("catScOptId", catScOptId);
		param.put("offset", offset);
		param.put("pageSize", pageSize);
		
		Map rslt = goodsService.queryByCondition(param);
		rslt.remove("records");
		return JSON.toJSONString(rslt);
	}
	
	@RequestMapping(value="/anon/goods/query/records")
	public String query_records(HttpServletRequest request, HttpServletResponse response) {
		int pageSize = getPageSize();
		int pageNum = StringUtil.parseInt(request.getParameter("pageNum"), 1);
		int catId = StringUtil.parseInt(request.getParameter("catId"), 0);
		int scId = StringUtil.parseInt(request.getParameter("scId"), 0);
		int catScOptId = StringUtil.parseInt(request.getParameter("catScOptId"), 0);
		int offset = (pageNum - 1) * pageSize;
		
		//--------------------------------------
		// 构造查询参数
		//--------------------------------------
		Map<String, Object> param = new HashMap();
		param.put("catId", catId);
		param.put("scId", scId);
		param.put("catScOptId", catScOptId);
		param.put("offset", offset);
		param.put("pageSize", pageSize);
		
		Map rslt = goodsService.queryByCondition(param);
		
		request.setAttribute("recordSize", rslt.get("recordSize"));
		request.setAttribute("records", rslt.get("records"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", getPageCount((Integer)rslt.get("recordSize"), pageSize));
		
		return "goods/query_records";
	}
	
	@RequestMapping(value="/anon/goods/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String gc = request.getParameter("_gc");
		GoodsVo goodsVo = goodsService.getGoodsVo(gc);

		if(goodsVo.getGoods() == null) {
			logger.error("goodsCode not found. _gc=" + gc);
			return "error";
		}
		
		request.setAttribute("goodsVo", goodsVo);
		return "goods/detail";
	}
	
	@RequestMapping(value="/anon/goods/categoty/list")
	public String categoty_list(HttpServletRequest request, HttpServletResponse response) {
		List<CataNavigation> navis = goodsService.queryNavigation();
		
		request.setAttribute("navis", navis);
		return "goods/categoty_list";
	}
	
	@RequestMapping(value="/anon/goods/categoty/options")
	public String categoty_options(HttpServletRequest request, HttpServletResponse response) {
		long catId = Long.parseLong(request.getParameter("catId"));
		Map<OptionKey, List<CatagoryOption>> map = goodsService.queryCatalogOption(catId);
		
		request.setAttribute("optionMap", map);
		return "goods/categoty_options";
	}
	
	private int getPageSize() {
		return ShopConst.DEF_GOODS_PAGESIZE;
	}
	
	private int getPageCount(int recordSize, int pageSize) {
		int pageCount = recordSize / pageSize;
		if((recordSize % pageSize) != 0) {
			pageCount += 1;
		}
		
		return pageCount;
	}
	
}
