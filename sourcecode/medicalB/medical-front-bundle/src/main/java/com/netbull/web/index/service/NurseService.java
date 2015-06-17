package com.netbull.web.index.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.index.dao.NurseDao;


@Service("nurseService")
public class NurseService {

	@Resource
	private NurseDao nurseDao;

	public long getcount() {
		return nurseDao.getcount();
	}
}
