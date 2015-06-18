package com.netbull.shop.manage.weixin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.vo.GoodsClob;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.manage.weixin.dao.GoodsDao;
import com.netbull.shop.pharmacy.dao.PharmacyDao;
import com.netbull.shop.pharmacy.entity.Pharmacy;
import com.netbull.shop.util.MyBatisDao;
import com.netbull.shop.util.UploadFileUtil;

@Service
public class GoodsService extends MyBatisDao<GoodsVo, Integer>{

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	@Autowired
	private PharmacyDao pharmacyDao;
	
	/**
	 * 分页查询商品信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryGoodsPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		Map parameter=new HashMap();
		parameter.put("users", BaseDao.handleQueryOrgan());
		Pharmacy pharmacy=pharmacyDao.findByParam(parameter);
		if(!NullUtil.isNull(pharmacy)){
			requestMap.put("shopId", pharmacy.getShopID());
		}
		return goodsDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 根据参数查询商品信息
	 * @param map
	 * @return
	 */
	public GoodsVo queryGoodsVoByParams(Map<Object,Object> map){
		return goodsDao.queryEntityByParams(map);
	}
	
	/**
	 * 保存商品信息
	 * @param MYBATIS_PREFIX
	 * @param goodsVo
	 * @param file
	 */
	public void goodsSave(String MYBATIS_PREFIX,GoodsVo goodsVo,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, null);
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		goodsVo.setImgPath(realPath+fileName);
		goodsVo.setCreatePerson(BaseDao.queryCurrentShiroUser().getLoginName());
		
		Map parameter=new HashMap();
		parameter.put("users", BaseDao.handleQueryOrgan());
		Pharmacy pharmacy=pharmacyDao.findByParam(parameter);
		
		if(!NullUtil.isNull(pharmacy)){
			goodsVo.setShopId(pharmacy.getShopID());
		}
		save(MYBATIS_PREFIX, goodsVo);
	}
	
	/**
	 * 更新商品信息
	 * @param MYBATIS_PREFIX
	 * @param goodsVo
	 * @param file
	 */
	public void goodsUpdate(String MYBATIS_PREFIX,GoodsVo goodsVo,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, null);
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		goodsVo.setImgPath(realPath+fileName);
		goodsVo.setUpdatePerson(BaseDao.queryCurrentShiroUser().getLoginName());
		update(MYBATIS_PREFIX, goodsVo);
	}
	
	/**
	 * 查询所有商品
	 * @param map
	 * @return
	 */
	public List<GoodsVo> queryAll(Map<String,String> map){
		return goodsDao.queryAll(map);
	}
	
	public GoodsClob queryGoodsClob(Integer id){
		return goodsDao.queryClob(id);
	}
	
	public int saveGoodsClob(GoodsClob goodsClob){
		return goodsDao.saveClob(goodsClob);
	}
	
	public int updateGoodsClob(GoodsClob goodsClob){
		return goodsDao.updateClob(goodsClob);
	}
}
