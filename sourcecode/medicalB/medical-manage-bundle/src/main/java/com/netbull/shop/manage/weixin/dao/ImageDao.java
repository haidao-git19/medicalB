package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.vo.ImageVo;

@Repository
public class ImageDao {

	private static final String MYBATIS_PREFIX=ImageDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 查询商品展示图片
	 * @param map
	 * @return
	 */
	public List<ImageVo> queryList(Map<Object,Object> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", map);
	}
}
