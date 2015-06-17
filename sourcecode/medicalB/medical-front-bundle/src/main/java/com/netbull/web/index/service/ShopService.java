package com.netbull.web.index.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.index.dao.ShopDao;


@Service("shopService")
public class ShopService {

	@Resource
	private ShopDao shopDao;

	public long getcount() {
		return shopDao.getcount();
	}
}
