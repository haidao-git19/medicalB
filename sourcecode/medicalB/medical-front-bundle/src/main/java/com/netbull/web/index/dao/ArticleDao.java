package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.web.index.entity.Article;

@Repository("articleDao")
public class ArticleDao  extends BaseDao{
	private static final String MYBATIS_PREFIX = ArticleDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		return session.page(MYBATIS_PREFIX+".queryList", MYBATIS_PREFIX+".queryCount",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public Article findById(long id) {
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("id", id);
		return session.selectOne(MYBATIS_PREFIX+".queryOne", param);
	}
	
	/**
	 * 查询首页专家文章
	 * @param paramter
	 * @return
	 */
	public List<Article> queryDetailList(Map paramter) {		
		List<Article> list = session.selectList(MYBATIS_PREFIX + ".queryList",paramter);
		return list;
	} 

}
