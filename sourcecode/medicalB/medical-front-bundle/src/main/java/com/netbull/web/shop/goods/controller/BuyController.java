package com.netbull.web.shop.goods.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.util.OrderUtil;
import com.netbull.shop.manage.common.util.ShopConst;
import com.netbull.shop.manage.common.util.ZipUtil;
import com.netbull.web.shop.common.BaseController;
import com.netbull.web.shop.common.ShoppingCart;
import com.netbull.web.shop.common.ShoppingCartItem;
import com.netbull.web.shop.goods.domain.Goods;
import com.netbull.web.shop.goods.service.GoodsService;
import com.netbull.web.shop.goods.vo.GoodsVo;
import com.netbull.web.shop.order.domain.OrderBase;
import com.netbull.web.shop.order.domain.OrderDelivery;
import com.netbull.web.shop.order.domain.OrderItem;
import com.netbull.web.shop.order.domain.OrderPayment;
import com.netbull.web.shop.order.service.OrderService;

@Controller
public class BuyController extends BaseController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private GoodsService goodsService;
	@Resource
	private OrderService orderService;
	
	@RequestMapping(value="/anon/buy/cart")
	public String cart(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart cart = readCartFromCookie(request);
		request.setAttribute("cart", cart);
		return "goods/cart";
	}
	
	@RequestMapping(value="/anon/buy/fillOrder")
	public String fillOrder(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart cart = readCartFromCookie(request);
		request.setAttribute("cart", cart);
		return "goods/fillOrder";
	}
	
	@RequestMapping(value="/anon/buy/saveOrder")
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart cart = retrieveShoppingCart(request);
		String orderNumber = OrderUtil.buildOrderNumber();
		
		OrderBase orderBase = buildOrderBase(cart, orderNumber);
		OrderDelivery orderDelivery = buildOrderDelivery(request, orderNumber);
		OrderPayment orderPayment = buildOrderPayment(request, cart, orderNumber);
		List<OrderItem> items = buildOrderItems(cart, orderNumber);
		int rc = 0;
		try{
			orderService.saveOrderBase(orderBase);
			orderService.saveOrderDelivery(orderDelivery);
			orderService.saveOrderPayment(orderPayment);
			for(OrderItem orderItem : items) {
				orderService.saveOrderItem(orderItem);
			}
			rc = 1;
			clearCartCookie(request, response);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		request.setAttribute("rc", rc);
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("totalPrice", cart.getTotalPrice());
		return "goods/saveOrder"; 
	}
	
	@RequestMapping(value="/anon/buy/orderFinish")
	public String orderFinish(HttpServletRequest request, HttpServletResponse response) {
		String rc = request.getParameter("_r");
		String orderNumber = request.getParameter("_n");
		String totalPrice = request.getParameter("_p");
		String sign = request.getParameter("_sig");
		
		String curSig = DigestUtils.md5Hex(rc+orderNumber+totalPrice+ShopConst.SIGN_KEY);
		if(!curSig.equals(sign)) {
			logger.warn("invalid sign.");
			return "error";
		}
		
		request.setAttribute("rc", rc);
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("totalPrice", totalPrice);
		return "goods/orderFinish"; 
	}
	
	private ShoppingCart retrieveShoppingCart(HttpServletRequest request) {
		Cookie cookie = CookiesUtils.getCookie(request, ShopConst.CART_NAME);
		ShoppingCart cart = ShoppingCart.getInstance();
		if(cookie != null) {
			String value = ZipUtil.ungzip(cookie.getValue());
			cart = JSON.parseObject(value, ShoppingCart.class);
			
			for(String key : cart.getItems().keySet()) {
				ShoppingCartItem item = cart.getItems().get(key);
				GoodsVo goods = goodsService.getGoodsVo(item.getGoods().getGoods().getGoodsCode());
				item.setGoods(goods);
			}
		}
		
		return cart;
	}
	
	private OrderBase buildOrderBase(ShoppingCart cart, String orderNumber) {
		OrderBase orderBase = new OrderBase();
		orderBase.setOrderNumber(orderNumber);
		orderBase.setTotalPrice(cart.calcTotalPriceFen());
//		orderBase.setUserId(userId); //TODO 依赖登录
		return orderBase;
	}
	
	private OrderDelivery buildOrderDelivery(HttpServletRequest request, String orderNumber) {
		String selProvince = request.getParameter("province");
		String selCity = request.getParameter("city");
		String selArea = request.getParameter("area");
		String particularAddress = request.getParameter("particularAddress");
		String sendPeople = request.getParameter("sendPeople");
		String contactMobile = request.getParameter("contactMobile");
		
		OrderDelivery orderDelivery = new OrderDelivery();
		orderDelivery.setOrderNumber(orderNumber);
		orderDelivery.setAddress(selProvince + "-" + selCity + "-" + selArea + "-" + particularAddress);
		orderDelivery.setName(sendPeople);
		orderDelivery.setTel(contactMobile);
		return orderDelivery;
	}
	
	private OrderPayment buildOrderPayment(HttpServletRequest request, ShoppingCart cart, String orderNumber) {
		int payMode = StringUtil.parseInt(request.getParameter("payMode"), 0);
		String payPlatformCode = request.getParameter("payPlatformCode");
		
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrderNumber(orderNumber);
		orderPayment.setPayMode(payMode);
		orderPayment.setPayPrice(cart.calcTotalPriceFen());
		orderPayment.setPayPlatformCode(payPlatformCode);
		return orderPayment;
	}
	
	private List<OrderItem> buildOrderItems(ShoppingCart cart, String orderNumber) {
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		if(cart != null) {
			for(String key : cart.getItems().keySet()) {
				ShoppingCartItem item = cart.getItems().get(key);
				Goods goods = item.getGoods().getGoods();

				OrderItem e = new OrderItem();
				e.setOrderNumber(orderNumber);
				e.setGoodsCode(goods.getGoodsCode());
				e.setGoodsType(OrderItem.GOODS_TYPE_DRUG);
				e.setName(goods.getBrandName() + " " + goods.getGoodsName() + " " + goods.getSpec());
				e.setPrice((int)(goods.getShopPrice() * 100));
				e.setAmount(item.getAmount());
				e.setShopId(goods.getShopId());
				
				items.add(e);
			}
		}
		
		return items;
	}
	
}
