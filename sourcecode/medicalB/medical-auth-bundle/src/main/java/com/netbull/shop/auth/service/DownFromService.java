package com.netbull.shop.auth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.dao.HDao;

@Service("downFromService")
public class DownFromService {
	
	@Resource
	private HDao authDao;

}
