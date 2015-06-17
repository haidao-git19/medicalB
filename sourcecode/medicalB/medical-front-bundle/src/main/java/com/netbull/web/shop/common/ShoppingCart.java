package com.netbull.web.shop.common;

import java.util.HashMap;
import java.util.Map;

import com.netbull.shop.manage.common.util.CommonUtil;

public class ShoppingCart {

	public static ShoppingCart getInstance() {
		return new ShoppingCart();
	}
	
	private Map<String, ShoppingCartItem> items = new HashMap<String, ShoppingCartItem>();
	
	public void addItem(String goodsCode, ShoppingCartItem item) {
		items.put(goodsCode, item);
	}
	
	public void removeItem(String goodsCode) {
		items.remove(goodsCode);
	}
	
	public void clear() {
		items.clear();
	}
	
	public void increase(String goodsCode) {
		ShoppingCartItem item = items.get(goodsCode);
		if(item == null) {
			return;
		}
		
		int amount = item.getAmount();
		item.setAmount(amount + 1);
	}
	
	public void decrease(String goodsCode) {
		ShoppingCartItem item = items.get(goodsCode);
		if(item == null) {
			return;
		}
		
		int amount = item.getAmount();
		item.setAmount(amount - 1);
	}
	
	public String getTotalPrice() {
		return CommonUtil.round(calcTotalPrice(), 2);
	}
	
	public double calcTotalPrice() {
		double totalPrice = 0;
		
		for(String key : items.keySet()) {
			ShoppingCartItem item = items.get(key);
			if(item != null) {
				totalPrice += (item.getGoods().getGoods().getShopPrice() * item.getAmount());
			}
		}
		
		return totalPrice;
	}
	
	public int calcTotalPriceFen() {
		return (int)(calcTotalPrice() * 100);
	}

	public Map<String, ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Map<String, ShoppingCartItem> items) {
		this.items = items;
	}

}
