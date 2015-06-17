package com.netbull.shop.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.dao.HDao;
import com.netbull.shop.auth.entity.CarouselInfo;

@Service("carouselService")
public class CarouselService {

	@Resource
	private HDao authDao;
	
	/**
	 * 查找轮播图片
	 * @param wechatId
	 * @return
	 */
	public List<CarouselInfo> queryCarousels(String wechatId){
		String hql="from CarouselInfo where wechatId=?";
		return authDao.find(hql, wechatId);
	}
}
