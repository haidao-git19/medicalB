package com.netbull.web.shop.common;

import com.netbull.web.shop.goods.vo.GoodsVo;

public class ShoppingCartItem {
	private GoodsVo goods;
	private int amount;
	
	public ShoppingCartItem() {
		
	}

	public ShoppingCartItem(GoodsVo goods, int amount) {
		this.goods = goods;
		this.amount = amount;
	}

	public GoodsVo getGoods() {
		return goods;
	}

	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
