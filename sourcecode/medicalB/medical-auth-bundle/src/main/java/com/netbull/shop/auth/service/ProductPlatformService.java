package com.netbull.shop.auth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import com.netbull.shop.auth.entity.PlatformInfo;
import com.netbull.shop.auth.entity.ProductInfo;
import com.netbull.shop.auth.entity.ProductPlatformInfo;
import com.netbull.shop.auth.entity.UserDataInfo;
import com.netbull.shop.dao.HDao;

@Service("productPlatformService")
public class ProductPlatformService {

	@Resource
	private HDao authDao;

	public List<PlatformInfo> getPlatformsByUser(String loginName, String... productIds) {
		List<PlatformInfo> list = new ArrayList<PlatformInfo>();
		if("admin".equals(loginName)) {
			list = getAllPlatforms();
		}else {
			List<String> values = authDao.find("select dataValue from UserDataInfo where user.loginName=? and dataType=?",
					loginName, UserDataInfo.TYPE_PLATFORM);
			for (String platformId : values) {
				list.add(getPlatformById(platformId));
			}
		}
		if (ArrayUtils.isNotEmpty(productIds)) {
			Set<String> ids = getPlatformIdsByProduct(productIds);
			Iterator<PlatformInfo> it = list.iterator();
			while (it.hasNext()) {
				PlatformInfo el = it.next();
				if (!ids.contains(el.getId())) {
					it.remove();
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public List<ProductInfo> getProductsByUser(String loginName) {
		if ("admin".equals(loginName)) {
			return getAllProducts();
		}
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		List<String> values = authDao.find("select dataValue from UserDataInfo where user.loginName=? and dataType=?",
				loginName, UserDataInfo.TYPE_PRODUCT);
		for (String productId : values) {
			list.add(getProductById(productId));
		}
		Collections.sort(list);
		return list;
	}

	public PlatformInfo getPlatformById(String platformId) {
		return authDao.get(PlatformInfo.class, platformId);
	}

	public List<PlatformInfo> getAllPlatforms() {
		return authDao.find("from PlatformInfo order by pfNumber, id");
	}

	public ProductInfo getProductById(String productId) {
		return authDao.get(ProductInfo.class, productId);
	}

	public List<ProductInfo> getAllProducts() {
		return authDao.find("from ProductInfo order by bizNumber, id");
	}

	public List<ProductPlatformInfo> getAllRelateInfo() {
		return authDao.find("from ProductPlatformInfo");
	}

	public Set<String> getPlatformIdsByProduct(String... productIds) {
		HashSet<String> idSet = new HashSet<String>();
		if (ArrayUtils.isNotEmpty(productIds)) {
			for (String productId : productIds) {
				List<String> ids = authDao.find(
						"select platformId from ProductPlatformInfo where productId=? order by platformId", productId);
				idSet.addAll(ids);
			}
		}
		return idSet;
	}

}
