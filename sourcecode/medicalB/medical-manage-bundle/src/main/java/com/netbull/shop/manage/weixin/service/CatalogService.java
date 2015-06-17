package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.vo.CatalogGoodsVo;
import com.netbull.shop.common.vo.CatalogVo;
import com.netbull.shop.common.vo.GoodsFilter;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.manage.weixin.dao.CatalogDao;
import com.netbull.shop.util.UploadFileUtil;

@Service
public class CatalogService {

	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	/**
	 * 分页查询类目商品列表
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryCatalogGoodsPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return catalogDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	/**
	 * 条件查询类目商品
	 * @param map
	 * @return
	 */
	public List<CatalogGoodsVo> queryCatalogsByParams(Map<String,String> map){
		return catalogDao.queryList(map);
	}
	/**
	 * 查询类目
	 * @param map
	 * @return
	 */
	public CatalogVo queryCatalog(Map<String,String> map){
		return catalogDao.queryCatalog(map);
	}
	/**
	 * 保存类目
	 * @param map
	 * @return
	 */
	public Integer saveCatalog(Map<String,String> map){
		return catalogDao.saveCatalog(map);
	}
	/**
	 * 更新类目
	 * @param catalogVo
	 * @return
	 */
	public Integer updateCatalog(Map<String,String> map){
		return catalogDao.updateCatalog(map);
	}
	/**
	 * 保存类目商品
	 * @param map
	 * @return
	 */
	public Integer saveCatalogGoods(Map<String,String> map,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		map.put("coverImage", fileName);
		return catalogDao.saveCatalogGoods(map);
	}
	/**
	 * 更新类目商品
	 * @param catalogGoodsVo
	 * @return
	 */
	public Integer updateCatalogGoods(Map<String,String> map,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		map.put("coverImage", fileName);
		return catalogDao.updateCatalogGoods(map);
	}
	/**
	 * 查询类目商品
	 * @param id
	 * @return
	 */
	public CatalogGoodsVo queryCatalogGoods(Map<String,String> map){
		return catalogDao.queryCatalogGoods(map);
	} 
	
	/**
	 * 删除类目商品
	 * @param id
	 */
	public void deleteCatalogGoods(Integer id){
		catalogDao.deleteCatalogGoods(id);
	}
	
	/**
	 * 删除类目
	 * @param map
	 */
	public void deleteCatalog(Map<String,String> map){
		catalogDao.deleteCatalog(map);
	}
	
	/**
	 * 查找类目商品风格属性
	 * @param map
	 * @return
	 */
	public List<GoodsFilter> queryCatalogGoodsFilter(Map<String,String> map){
		return catalogDao.queryFilter(map);
	}
}
