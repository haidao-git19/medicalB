package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.AdviceDao;
import com.netbull.shop.manage.weixin.vo.AdviceVo;

@Service
public class AdviceService {

	@Autowired
	private AdviceDao adviceDao;
	
	public void saveAdvice(AdviceVo adviceVo){
		adviceDao.save(adviceVo);
	}
}
