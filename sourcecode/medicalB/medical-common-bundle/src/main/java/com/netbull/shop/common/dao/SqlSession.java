package com.netbull.shop.common.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.page.PageContainer;
import com.netbull.shop.util.BeanUtils;

@Component
public class SqlSession implements org.apache.ibatis.session.SqlSession{
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	public SqlSession() {
		super();
	}

	public SqlSession(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void clearCache() {
		sqlSessionTemplate.clearCache();
	}

	@Override
	public void close() {
		sqlSessionTemplate.close();

	}

	@Override
	public void commit() {
		sqlSessionTemplate.commit();
	}

	@Override
	public void commit(boolean arg0) {
		sqlSessionTemplate.commit(arg0);

	}

	@Override
	public int delete(String arg0) {
		return sqlSessionTemplate.delete(arg0);
	}

	@Override
	public int delete(String arg0, Object arg1) {
		return sqlSessionTemplate.delete(arg0, arg1);
	}

	@Override
	public List<BatchResult> flushStatements() {
		return sqlSessionTemplate.flushStatements();
	}

	@Override
	public Configuration getConfiguration() {
		return sqlSessionTemplate.getConfiguration();
	}

	@Override
	public Connection getConnection() {
		return sqlSessionTemplate.getConnection();
	}

	@Override
	public <T> T getMapper(Class<T> arg0) {
		return sqlSessionTemplate.getMapper(arg0);
	}

	@Override
	public int insert(String arg0) {
		return sqlSessionTemplate.insert(arg0);
	}

	@Override
	public int insert(String arg0, Object arg1) {
		return sqlSessionTemplate.insert(arg0, arg1);
	}

	@Override
	public void rollback() {
		sqlSessionTemplate.rollback();
	}

	@Override
	public void rollback(boolean arg0) {
		sqlSessionTemplate.rollback(arg0);
	}

	@Override
	public void select(String arg0, ResultHandler arg1) {
		sqlSessionTemplate.select(arg0, arg1);
	}

	@Override
	public void select(String arg0, Object arg1, ResultHandler arg2) {
		sqlSessionTemplate.select(arg0, arg1, arg2);
	}

	@Override
	public void select(String arg0, Object arg1, RowBounds arg2, ResultHandler arg3) {
		sqlSessionTemplate.select(arg0, arg1, arg2, arg3);

	}

	@Override
	public <E> List<E> selectList(String arg0) {
		return sqlSessionTemplate.selectList(arg0);
	}

	@Override
	public <E> List<E> selectList(String arg0, Object arg1) {
		return sqlSessionTemplate.selectList(arg0, arg1);
	}

	@Override
	public <E> List<E> selectList(String arg0, Object arg1, RowBounds arg2) {
		return sqlSessionTemplate.selectList(arg0, arg1, arg2);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String arg0, String arg1) {
		return sqlSessionTemplate.selectMap(arg0, arg1);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String arg0, Object arg1, String arg2) {
		return sqlSessionTemplate.selectMap(arg0, arg1, arg2);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String arg0, Object arg1, String arg2, RowBounds arg3) {
		return sqlSessionTemplate.selectMap(arg0, arg1, arg2);
	}

	@Override
	public <T> T selectOne(String arg0) {
		return sqlSessionTemplate.selectOne(arg0);
	}

	@Override
	public <T> T selectOne(String arg0, Object arg1) {
		return sqlSessionTemplate.selectOne(arg0, arg1);
	}

	@Override
	public int update(String arg0) {
		return sqlSessionTemplate.update(arg0);
	}

	@Override
	public int update(String arg0, Object arg1) {
		return sqlSessionTemplate.update(arg0, arg1);
	}

	public Object get(String statement, Map params) {
		return sqlSessionTemplate.selectOne(statement, params);
	}

	public <E> List<E> find(String statement, Map params) {
		return sqlSessionTemplate.selectList(statement, params);
	}

	public int delete(String arg0, Map params) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(arg0, params);
	}

	public Page page(String arg0, Map params, int offset, int limit) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page page(String pageStatement, String countStatement, Map params, Integer current, Integer pagesize) {
		Map p = BeanUtils.cloneMap(params);
		long total = count(countStatement, p);
		long offset = PageContainer.startRow(current, pagesize, total);
		p.put("LIMIT", pagesize);
		p.put("OFFSET", offset);
		List l = sqlSessionTemplate.selectList(pageStatement, p);
		Page page = new PageContainer(total, pagesize, current, l, p);
		return page;
	}

	@SuppressWarnings("rawtypes")
	public long count(String countStatement, Map params) {
		return sqlSessionTemplate.selectOne(countStatement, params);
	}

	public Map findOne(String statement, Map params) {
		return sqlSessionTemplate.selectOne(statement, params);
	}

	public <E> List<E> find(String statement) {
		return sqlSessionTemplate.selectList(statement);
	}

}
