package com.netbull.web.shop.goods.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.web.shop.goods.domain.Goods;
import com.netbull.web.shop.goods.domain.GoodsImg;
import com.netbull.web.shop.goods.vo.CataNavigation;
import com.netbull.web.shop.goods.vo.CatagoryOption;

@Repository
public class GoodsDao {

	private static final String NAMESPACE = Goods.class.getName();

	@Resource
	private SqlSession session;
	
	public Goods findGoods(String goodsCode) {
		return session.selectOne(NAMESPACE + ".find_Goods_by_goodsCode", goodsCode);
	}
	
	public List<GoodsImg> findGoodsImages(String goodsCode) {
		return session.selectList(NAMESPACE + ".find_Goods_images_by_goodsCode", goodsCode);
	}
	
	public List queryRecordSizeByCondition(Map param) {
		return session.selectList(NAMESPACE + ".query_Records_By_Condition", param);
	}
	
	public Integer queryRecordsByCondition(Map param) {
		return session.selectOne(NAMESPACE + ".query_RecordSize_By_Condition", param);
	}
	
	public List<CataNavigation> queryCatalogTree() {
		return session.selectList(NAMESPACE + ".queryCatalogTree", null);
	}
	
	public List<CatagoryOption> queryCatalogOption(long catId) {
		return session.selectList(NAMESPACE + ".queryCatalogOption", catId);
	}
}