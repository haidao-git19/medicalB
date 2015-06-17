package com.netbull.web.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.web.index.dao.ArticleDao;
import com.netbull.web.index.entity.Article;

@Service("articleService")
public class ArticleService {

	@Resource
	private ArticleDao articleDao;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		return articleDao.page(iDisplayStart, iDisplayLength, requestMap);
	}

	public Article findById(long id) {
		return articleDao.findById(id);
	}
	/**
	 * 查询首页专家文章
	 * @param paramter
	 * @return
	 */
	public List<Article> queryDetailList(Map paramter) {		
		return articleDao.queryDetailList(paramter);
	} 
}
