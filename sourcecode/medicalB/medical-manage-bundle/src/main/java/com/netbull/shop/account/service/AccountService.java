package com.netbull.shop.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.netbull.shop.account.dao.AccountDao;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.company.entity.CompanyVo;
import com.netbull.shop.doctor.entity.UserInfo;
import com.netbull.shop.pharmacy.dao.PharmacyDao;

@Service("accountService")
public class AccountService {

	@Resource
	private AccountDao accountDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = accountDao.page(iDisplayStart, iDisplayLength, requestMap);
		return page;
	}

}
