package com.netbull.shop.pharmacy.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.doctor.entity.UserInfo;
import com.netbull.shop.patient.dao.PatientDao;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;
import com.netbull.shop.pharmacy.dao.PharmacyDao;
import com.netbull.shop.pharmacy.entity.Pharmacy;
import com.netbull.shop.util.UploadFileUtil;


@Service("pharmacyService")
public class PharmacyService {

	@Resource
	private  PharmacyDao  pharmacyDao;
	
	@Resource
	private  PatientDao  patientDao;
	
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = pharmacyDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public int save(Pharmacy pharmacy,CommonsMultipartFile file) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginName(pharmacy.getLoginAccount());
		userInfo.setPassword(DigestUtils.md5Hex(pharmacy.getLoginPwd()));
		userInfo.setParentid(pharmacy.getCreator());
		userInfo.setPhone(pharmacy.getPhone());
		userInfo.setTrueName(pharmacy.getShopName());
		
		userInfo = pharmacyDao.saveUserInfo(userInfo);
		int pharmacyRoleId = StringUtil.parseInt(ConfigLoadUtil.loadConfig().getPropertie("PHARMACY_ROLE_ID"), 0);
		pharmacyDao.saveRoleUserInfo(pharmacyRoleId, userInfo.getId());
		pharmacy.setLoginID(userInfo.getId());
		
		String fileName=uploadFileUtil.createFile(file, null);
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		pharmacy.setLogo(realPath+fileName);
		return pharmacyDao.save(pharmacy);
	}


	public int update(Pharmacy pharmacy,CommonsMultipartFile file) {
		UserInfo userInfo = new UserInfo();
		userInfo.setTrueName(pharmacy.getShopName());
		userInfo.setPhone(pharmacy.getPhone());
		userInfo.setId(pharmacy.getLoginID());
		pharmacyDao.updateUserInfo(userInfo);
		
		if(!NullUtil.isNull(file)&&file.getSize()>0){
			String fileName=uploadFileUtil.createFile(file, null);
			String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
			pharmacy.setLogo(realPath+fileName);
		}
		return pharmacyDao.update(pharmacy);
	}


	public Pharmacy findByParam(Map parameter) {
		// TODO Auto-generated method stub
		return pharmacyDao.findByParam(parameter);
	}

	public List<Pharmacy> queryPharmacy(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return pharmacyDao.queryPharmacy(pharmacy);
	}

	public int del(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return pharmacyDao.del(pharmacy.getShopID());
	}
	
	public int updatePicture(Picture picture) {
		// TODO Auto-generated method stub
	return	patientDao.updatePicture(picture);
		
	}


	public void savePicture(Picture picture) {
		// TODO Auto-generated method stub
		patientDao.savePicture(picture);
	}


	public String findImg(Picture picture) {
		// TODO Auto-generated method stub
	return patientDao.findImg(picture);
	}

}
