package com.netbull.web.section.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.web.section.dao.PlusDao;

@Service
public class PlusService {

	@Autowired
	private PlusDao plusDao;
	
	public long queryPlusSuccessCount(Map parameter){
		return plusDao.queryPlusSuccessCount(parameter);
	}
}
