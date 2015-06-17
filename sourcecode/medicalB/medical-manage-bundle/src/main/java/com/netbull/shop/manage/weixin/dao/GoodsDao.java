package com.netbull.shop.manage.weixin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.GoodsClob;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.pharmacy.dao.PharmacyDao;
import com.netbull.shop.pharmacy.entity.Pharmacy;

@Repository
public class GoodsDao {

	private static final String MYBATIS_PREFIX=GoodsDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private PharmacyDao pharmacyDao;
	
	/**
	 * 分页查询商品信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){ 
		
		
		//获取商户信息 
		List<Pharmacy>  pharmacyList =pharmacyDao.queryPharmacy(new Pharmacy());
		List<String> list=new ArrayList<String>();
		if(pharmacyList!=null&&pharmacyList.size()>0){
			for (int i = 0; i < pharmacyList.size(); i++) {
				list.add(pharmacyList.get(i).getCode());
			}
		}else{
			//不让查询数据
			list.add("999999999999999");
		}
		requestMap.put("pharmacyCodes", list);
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 根据参数查询商品信息
	 * @param map
	 * @return
	 */
	public GoodsVo queryEntityByParams(Map<Object,Object> map){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryEntityByParams", map);
	}
	
	/**
	 * 查询所有商品
	 * @param map
	 * @return
	 */
	public List<GoodsVo> queryAll(Map<String,String> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", map);
	}
	
	public GoodsClob queryClob(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryClob",id);
	}
	
	public int saveClob(GoodsClob goodsClob){
		return sqlSession.insert(MYBATIS_PREFIX+".saveClob", goodsClob);
	}
	
	public int updateClob(GoodsClob goodsClob){
		return sqlSession.update(MYBATIS_PREFIX+".updateClob", goodsClob);
	}
}
