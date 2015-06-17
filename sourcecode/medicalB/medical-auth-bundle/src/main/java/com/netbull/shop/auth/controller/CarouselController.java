package com.netbull.shop.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.auth.entity.CarouselInfo;
import com.netbull.shop.auth.service.CarouselService;

@Controller
public class CarouselController {

	@Autowired
	private CarouselService carouselService;
	
	/**
	 * 查询首页轮播信息
	 * @return
	 */
	@RequestMapping("/carousel/queryCarousels")
	@ResponseBody
	public List<CarouselInfo> queryCarousels(){
		String wechatId=StringUtil.getString(StringTools.queryWechatId());
		List<CarouselInfo> carousels=carouselService.queryCarousels(wechatId);
		return carousels;
	}
}
