package com.netbull.shop.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.netbull.shop.common.dao.SqlSession;
/**
 * 封装myBatis公用方法
 * @author luye
 *
 */
public class MyBatisDao<T,ID extends Serializable> {
	@Resource
	private SqlSession session;
	/**
	 * 获取分页的pageBean对象
	 * @param page
	 * @param myBatisPrefix
	 * @param map
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public PageBean<T> findPage(final PageBean<T> page, final String myBatisPrefix,Map map){
		long totalCount = session.selectOne(myBatisPrefix+".queryCount",map);
		page.setCount(totalCount);
		List<T> result = session.selectList(myBatisPrefix+".querylist",map);
		page.setData(result);
		return page;
	}
	
	/**
	 * 根据id获取实体
	 * @param myBatisPrefix
	 * @param id
	 * @return
	 */
	public T get(final String myBatisPrefix,final ID id){
		return session.selectOne(myBatisPrefix + ".queryEntityById", id);
	}
	
	/**
	 * 保存实体
	 * @param myBatisPrefix
	 * @param entity
	 */
	public void save(final String myBatisPrefix,final T entity){
		session.insert(myBatisPrefix + ".save", entity);
	}
	
	/**
	 * 根据id删除实体
	 * @param myBatisPrefix
	 * @param id
	 * @return
	 */
	public void delete(final String myBatisPrefix,final ID id){
		session.delete(myBatisPrefix + ".delete", id);
	}
	
	/**
	 * 获取实体的所有数据
	 * @param myBatisPrefix
	 * @return
	 */
	public List<T> findAll(String myBatisPrefix){
		return session.selectList(myBatisPrefix+".queryAll");
	}
	/**
	 * 验证数据是否重复
	 * @param myBatisPrefix
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkRule(String myBatisPrefix,Map map){
		long count = session.selectOne(myBatisPrefix+".checkRule",map);
		return count==0;
	}
	
	/**
	 * 更新实体
	 * @param myBatisPrefix
	 * @param entity
	 */
	public Integer update(final String myBatisPrefix,final T entity){
		return session.update(myBatisPrefix + ".update", entity);
	}
}
