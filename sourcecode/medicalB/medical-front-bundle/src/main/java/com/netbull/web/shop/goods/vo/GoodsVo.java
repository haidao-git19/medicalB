package com.netbull.web.shop.goods.vo;

import java.util.List;

import com.netbull.web.shop.goods.domain.Goods;
import com.netbull.web.shop.goods.domain.GoodsImg;

public class GoodsVo {

	private Goods goods;
	private List<GoodsImg> images;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<GoodsImg> getImages() {
		return images;
	}

	public void setImages(List<GoodsImg> images) {
		this.images = images;
	}

}
