package com.netbull.shop.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.netbull.shop.entity.BaseEntity;
import com.netbull.shop.util.PageBean;

@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class JpaDao {

	private static final Log logger = LogFactory.getLog(JpaDao.class);

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void save(Object entity) {
		if (entity instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) entity;
			if (baseEntity.getId() != null) {
				update(entity);
				return;
			}
		}
		entityManager.persist(entity);
	}

	@Transactional
	public void update(Object entity) {
		entityManager.merge(entity);
	}

	@Transactional
	public <T> void saveAll(Collection<T> entities) {
		for (T entity : entities) {
			save(entity);
		}
	}

	@Transactional
	public void delete(Object entity) {
		entityManager.remove(entity);
	}

	@Transactional
	public <T> void deleteAll(Collection<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Transactional
	public int bulkUpdate(String hql, Object... values) {
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		return query.executeUpdate();
	}

	@Transactional
	public int bulkUpdateBySQL(final String sql, final Object... values) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		return query.executeUpdate();
	}

	public <T> T refresh(T entity) {
		if (entity instanceof Object[]) {
			Object[] objs = (Object[]) entity;
			for (Object obj : objs) {
				refresh(obj);
			}
		} else if (entity instanceof Collection) {
			Collection<Object> collect = (Collection<Object>) entity;
			for (Object obj : collect) {
				refresh(obj);
			}
		}
		if (contains(entity)) {
			entityManager.refresh(entity);
		}
		return entity;
	}

	public boolean contains(Object entity) {
		try {
			return entityManager.contains(entity);
		} catch (Exception e) {
			return false;
		}
	}

	public <T> T find(Class<T> clazz, Serializable id) {
		return refresh(entityManager.find(clazz, id));
	}

	public <T> List<T> find(String hql, Object... values) {
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		return refresh(query.getResultList());
	}

	public <T> T findSingle(String hql, Object... values) {
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		try {
			return (T) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public <T> PageBean<T> find(PageBean<T> pageBean, String hql,
			Object... values) {
		entityManager.getEntityManagerFactory().getCache().evictAll();
		Query query = entityManager.createQuery(hql);
		Query countQuery = entityManager.createQuery(toCountHQL(hql));
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
			countQuery.setParameter(i + 1, values[i]);
		}
		Object obj = countQuery.getSingleResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(refresh(query.getResultList()));
		return pageBean;
	}

	public <T> List<T> find(String hql, String[] paramNames, Object[] params) {
		Query query = entityManager.createQuery(hql);
		setQueryParam(query, paramNames, params);
		return refresh(query.getResultList());

	}

	public <T> PageBean<T> find(PageBean<T> pageBean, String hql,
			String[] paramNames, Object[] params) {
		Query query = entityManager.createQuery(hql);
		Query countQuery = entityManager.createQuery(toCountHQL(hql));
		setQueryParam(query, paramNames, params);
		setQueryParam(countQuery, paramNames, params);
		Object obj = countQuery.getSingleResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(refresh(query.getResultList()));
		return pageBean;
	}

	public <T> List<T> findBySQL(String sql, Object... values) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		return refresh(query.getResultList());
	}

	public <T> T findSingleBySQL(String sql, Object... values) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		try {
			return (T) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public <T> PageBean<T> findBySQL(PageBean<T> pageBean, String sql,
			Object... values) {
		Query query = entityManager.createNativeQuery(sql);
		Query countQuery = entityManager.createNativeQuery(toCountSQL(sql));
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
			countQuery.setParameter(i + 1, values[i]);
		}
		Object obj = countQuery.getSingleResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(refresh(query.getResultList()));
		return pageBean;
	}

	public <T> List<T> findBySQL(String sql, String[] paramNames,
			Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		setQueryParam(query, paramNames, params);
		return refresh(query.getResultList());
	}

	public <T> PageBean<T> findBySQL(PageBean<T> pageBean, String sql,
			String[] paramNames, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		Query countQuery = entityManager.createNativeQuery(toCountSQL(sql));
		setQueryParam(query, paramNames, params);
		setQueryParam(countQuery, paramNames, params);
		Object obj = countQuery.getSingleResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(refresh(query.getResultList()));
		return pageBean;
	}

	public <T> PageBean<T> findDistinctHql(PageBean<T> pageBean,
			String indexString, String hql, Object... values) {
		entityManager.getEntityManagerFactory().getCache().evictAll();
		Query query = entityManager.createQuery(hql);
		Query countQuery = entityManager.createQuery(toCountDistinctHQL(
				indexString, hql));
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
			countQuery.setParameter(i + 1, values[i]);
		}
		Object obj = countQuery.getSingleResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(refresh(query.getResultList()));
		return pageBean;
	}

	private void setQueryParam(Query query, String[] paramNames, Object[] params) {
		if (paramNames == null || params == null)
			return;
		for (int i = 0; i < paramNames.length && i < params.length; i++) {
			query.setParameter(paramNames[i], params[i]);
		}
	}

	public long getCount(Object obj) {
		if (obj instanceof Object[]) {
			return getCount(((Object[]) obj)[0]);
		}
		if (obj instanceof List) {
			return getCount(((List<?>) obj).get(0));
		}
		if (obj instanceof Number) {
			Number count = (Number) obj;
			return count.longValue();
		}
		return 0;
	}

	public String toCountHQL(String hql) {
		if (null == hql || hql.trim().equals(""))
			return "";
		String formatQl = hql;
		String pStr = "^\\s*((s|S)(e|E)(l|L)(e|E)(c|C)(t|T))?(.*?)(f|F)(r|R)(o|O)(m|M)\\s";
		String pOrderStr = "\\s*(o|O)(r|R)(d|D)(e|E)(r|R)\\s+(b|B)(y|Y).*$";
		Pattern p = Pattern.compile(pStr, Pattern.DOTALL);
		Matcher m = p.matcher(hql);
		if (m.find()) {
			StringBuffer countHeader = new StringBuffer("SELECT COUNT(*)");
			if (m.group(8) != null && !m.group(8).trim().equals("")) {
				countHeader.append(", " + m.group(8).trim());
			}
			countHeader.append(" FROM ");
			formatQl = formatQl.replaceFirst(pStr, countHeader.toString());
		} else {
			logger.warn("can't convert to countHQL, hql[" + hql + "]");
		}
		formatQl = formatQl.replaceFirst(pOrderStr, "");
		return formatQl;
	}

	public String toCountDistinctHQL(String indexString, String hql) {
		if (null == hql || hql.trim().equals(""))
			return "";
		String formatQl = hql;
		String pStr = "^\\s*((s|S)(e|E)(l|L)(e|E)(c|C)(t|T))?(.*?)(f|F)(r|R)(o|O)(m|M)\\s";
		String pOrderStr = "\\s*(o|O)(r|R)(d|D)(e|E)(r|R)\\s+(b|B)(y|Y).*$";
		Pattern p = Pattern.compile(pStr, Pattern.DOTALL);
		Matcher m = p.matcher(hql);
		if (m.find()) {
			StringBuffer countHeader = new StringBuffer(indexString);
			countHeader.append(" FROM ");
			formatQl = formatQl.replaceFirst(pStr, countHeader.toString());
		} else {
			logger.warn("can't convert to countHQL, hql[" + hql + "]");
		}
		formatQl = formatQl.replaceFirst(pOrderStr, "");
		return formatQl;
	}

	public String toCountSQL(String sql) {
		if (null == sql || sql.trim().equals(""))
			return "";
		return "SELECT COUNT(*) FROM (" + sql + ") as COUNT";
	}

	public String getId(String nextval, String prefix, int length) {
		Query query = entityManager.createNativeQuery(nextval);
		int seq = (Integer) query.getSingleResult();
		return prefix + String.format("%1$0" + length + "d", seq);
	}

}