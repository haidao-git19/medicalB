package com.netbull.web.shop.common;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.manage.common.util.ShopConst;
import com.netbull.shop.manage.common.util.ZipUtil;

public class BaseController {

	public void writeCartToCookie(ShoppingCart cart, HttpServletResponse response) {
		String value = ZipUtil.gzip(JSON.toJSONString(cart));
		CookiesUtils.setCookie(response, ShopConst.CART_NAME, value, Calendar.getInstance().get(Calendar.SECOND) + ShopConst.ONE_MONTH_LATER);
	}
	
	public ShoppingCart readCartFromCookie(HttpServletRequest request) {
		ShoppingCart cart = null;
		Cookie cookie = CookiesUtils.getCookie(request, ShopConst.CART_NAME);
		if(cookie == null) {
			cart = ShoppingCart.getInstance();
		}else{
			String value = ZipUtil.ungzip(cookie.getValue());
			cart = JSON.parseObject(value, ShoppingCart.class);
		}
		return cart;
	}
	
	public void clearCartCookie(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart cart = readCartFromCookie(request);
		cart.clear();
		writeCartToCookie(cart, response);
	}
}
