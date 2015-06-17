package com.netbull.web.shop.goods.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.web.shop.common.BaseController;
import com.netbull.web.shop.common.ShoppingCart;
import com.netbull.web.shop.common.ShoppingCartItem;
import com.netbull.web.shop.goods.service.GoodsService;
import com.netbull.web.shop.goods.vo.GoodsVo;

@Controller
public class CartController extends BaseController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private GoodsService goodsService;

	@RequestMapping(value="/anon/shoppingcart/addItem")
	@ResponseBody
	public String addItem(HttpServletRequest request, HttpServletResponse response) {
		String gc = request.getParameter("goodsCode");
		String amt = request.getParameter("goodsAmount");
		int amount = StringUtil.parseInt(amt, 1);
		
		GoodsVo goodsVo = goodsService.getGoodsVo(gc);
		if(goodsVo.getGoods() == null) {
			logger.error("goodsCode not found. _gc=" + gc);
			return "error";
		}
		
		ShoppingCart cart = readCartFromCookie(request);
		cart.addItem(gc, new ShoppingCartItem(goodsVo, amount));
		writeCartToCookie(cart, response);
		
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return JSON.toJSONString(json);
	}
	
	@RequestMapping(value="/anon/shoppingcart/delItem")
	@ResponseBody
	public String delItem(HttpServletRequest request, HttpServletResponse response) {
		String gc = request.getParameter("goodsCode");
		
		ShoppingCart cart = readCartFromCookie(request);
		cart.removeItem(gc);
		writeCartToCookie(cart, response);
		
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return JSON.toJSONString(json);
	}
	
	@RequestMapping(value="/anon/shoppingcart/delItems")
	@ResponseBody
	public String delItems(HttpServletRequest request, HttpServletResponse response) {
		String gc = request.getParameter("goodsCodes");
		String[] gcs = gc.split(",");
		
		ShoppingCart cart = readCartFromCookie(request);
		for(String _gc : gcs) {
			cart.removeItem(_gc);
		}
		writeCartToCookie(cart, response);
		
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return JSON.toJSONString(json);
	}
	
	@RequestMapping(value="/anon/shoppingcart/clearCart")
	@ResponseBody
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		clearCartCookie(request, response);
		
		JSONObject json = new JSONObject();
		json.put("flag", 1);
		return JSON.toJSONString(json);
	}
	
}
