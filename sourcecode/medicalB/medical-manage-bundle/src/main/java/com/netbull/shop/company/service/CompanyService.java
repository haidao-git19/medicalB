package com.netbull.shop.company.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.company.dao.CompanyDao;
import com.netbull.shop.company.entity.CompanyVo;
import com.netbull.shop.doctor.entity.UserInfo;
import com.netbull.shop.pharmacy.dao.PharmacyDao;

@Service("companyService")
public class CompanyService {

	@Resource
	private CompanyDao companyDao;

	@Resource
	private  PharmacyDao  pharmacyDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = companyDao.page(iDisplayStart, iDisplayLength, requestMap);
		return page;
	}

	public int save(CompanyVo company) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginName(company.getLoginAccount());
		userInfo.setPassword(DigestUtils.md5Hex(company.getLoginPwd()));
		userInfo.setParentid(company.getCreator());
		userInfo.setPhone(company.getLinkPhone());
		userInfo.setTrueName(company.getCompanyName());
		userInfo.setStatus(1);
		userInfo = pharmacyDao.saveUserInfo(userInfo);
		int companyRoleId = StringUtil.parseInt(ConfigLoadUtil.loadConfig()
				.getPropertie("PHARMACY_ROLE_ID"), 0);
		pharmacyDao.saveRoleUserInfo(companyRoleId, userInfo.getId());
		company.setLoginId(userInfo.getId());

		return companyDao.save(company);
	}

	public int update(CompanyVo company) {
		if(company.getState()==-1){
			UserInfo userInfo = new UserInfo();
			userInfo.setTrueName(company.getCompanyName());
			userInfo.setId(company.getLoginId());
			userInfo.setStatus(0);
			pharmacyDao.updateUserInfo(userInfo);
		}

		return companyDao.update(company);
	}

	public CompanyVo findByParam(Map parameter) {
		return companyDao.findByParam(parameter);
	}

	public List<CompanyVo> queryCompanyVo(CompanyVo company) {
		return companyDao.queryCompany(company);
	}

	public int del(CompanyVo company) {
		return companyDao.del(company.getCompanyId());
	}

}
