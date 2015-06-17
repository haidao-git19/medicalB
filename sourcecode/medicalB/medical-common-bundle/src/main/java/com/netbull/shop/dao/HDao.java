package com.netbull.shop.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.netbull.shop.util.PageBean;

@SuppressWarnings("unchecked")
public class HDao implements IDao {
	
	private static final Log logger = LogFactory.getLog(HDao.class);

	private SessionFactory sessionFactory;

	private ThreadLocal<Session> localSession = new ThreadLocal<Session>();

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session currentSession() {
		Session session = localSession.get();
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
			localSession.set(session);
		}
		return session;
	}

	public boolean closeSessionIfNecessary() {
		Session session = localSession.get();
		if (session != null && session.isOpen()
				&& !session.getTransaction().isActive()) {
			session.close();
			return true;
		}
		return false;
	}

	public <T> T doInTx(Atom atom) {
		Object ret = null;
		Transaction tx = currentSession().getTransaction();
		boolean isAlreadyActive = tx.isActive();
		if (!isAlreadyActive) {
			tx.begin();
		}
		try {
			ret = atom.exec();
			if (!isAlreadyActive) {
				tx.commit();
			}
		} catch (Exception e) {
			if (!isAlreadyActive) {
				logger.info("rollback for " + e.getMessage());
				tx.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			closeSessionIfNecessary();
		}
		return (T) ret;
	}

	public void save(final Object entity) {
		doInTx(new Atom() {
			public Object exec() {
				currentSession().saveOrUpdate(entity);
				return entity;
			}
		});
	}

	public void update(final Object entity) {
		doInTx(new Atom() {
			public Object exec() {
				currentSession().update(entity);
				return entity;
			}
		});
	}

	public void delete(final Object entity) {
		doInTx(new Atom() {
			public Object exec() {
				currentSession().delete(entity);
				return entity;
			}
		});
	}

	public int bulkUpdate(final String hql, final Object... values) {
		return doInTx(new Atom() {
			public Object exec() {
				Query query = currentSession().createQuery(hql);
				setQueryParam(query, values);
				return query.executeUpdate();
			}
		});
	}

	public int bulkUpdateBySQL(final String sql, final Object... values) {
		return doInTx(new Atom() {
			public Object exec() {
				Query query = currentSession().createSQLQuery(sql);
				setQueryParam(query, values);
				return query.executeUpdate();
			}
		});
	}

	public <T> T get(Class<T> clazz, Serializable id) {
		if (id == null) {
			return null;
		}
		try {
			return (T) currentSession().get(clazz, id);
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> List<T> find(String hql, Object... values) {
		try {
			Query query = currentSession().createQuery(hql);
			setQueryParam(query, values);
			return query.list();
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> List<T> find(String hql, String[] paramNames, Object[] params) {
		try {
			Query query = currentSession().createQuery(hql);
			setQueryParam(query, paramNames, params);
			return query.list();
		} finally {
			closeSessionIfNecessary();
		}
	}
	public <T> T findSingle(String hql, Object... values) {
		try {
			Query query = currentSession().createQuery(hql);
			setQueryParam(query, values);
			return (T) query.uniqueResult();
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> List<T> findBySQL(String sql, Object... values) {
		try {
			Query query = currentSession().createSQLQuery(sql);
			setQueryParam(query, values);
			return query.list();
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> List<T> findBySQL(String sql, String[] paramNames,
			Object[] params) {
		try {
			Query query = currentSession().createSQLQuery(sql);
			setQueryParam(query, paramNames, params);
			return query.list();
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> T findSingleBySQL(String sql, Object... values) {
		try {
			Query query = currentSession().createSQLQuery(sql);
			setQueryParam(query, values);
			T ret = (T) query.uniqueResult();
			return ret;
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> PageBean<T> find(PageBean<T> pageBean, String hql,
			Object... values) {
		try {
			Query query = currentSession().createQuery(hql);
			Query countQuery = currentSession().createQuery(toCountHQL(hql));
			setQueryParam(query, values);
			setQueryParam(countQuery, values);
			Object obj = countQuery.uniqueResult();
			pageBean.setCount(getCount(obj));
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getLimit());
			pageBean.setData(query.list());
			return pageBean;
		} finally {
			closeSessionIfNecessary();
		}
	}

	public <T> PageBean<T> find(PageBean<T> pageBean, String hql,
			String[] paramNames, Object[] params) {
		try {
			Query query = currentSession().createQuery(hql);
			Query countQuery = currentSession().createQuery(toCountHQL(hql));
			setQueryParam(query, paramNames, params);
			setQueryParam(countQuery, paramNames, params);
			Object obj = countQuery.uniqueResult();
			pageBean.setCount(getCount(obj));
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getLimit());
			pageBean.setData(query.list());
			return pageBean;
		} finally {
			closeSessionIfNecessary();
		}
	}

	public synchronized <T> PageBean<T> findDistinctHql(PageBean<T> pageBean, String indexString,String hql,
			Object... values) {
		Query query = currentSession().createQuery(hql);
		Query countQuery = currentSession().createQuery(toCountDistinctHQL(indexString,hql));
		setQueryParam(query, values);
		setQueryParam(countQuery, values);
		Object obj = countQuery.uniqueResult();
		pageBean.setCount(getCount(obj));
		query.setFirstResult(pageBean.getStart());
		query.setMaxResults(pageBean.getLimit());
		pageBean.setData(query.list());
		return pageBean;
	}

	public synchronized <T> PageBean<T> findBySQL(PageBean<T> pageBean, String sql,
			Object... values) {
		try {
			Query query = currentSession().createSQLQuery(sql);
			Query countQuery = currentSession().createSQLQuery(toCountSQL(sql));
			setQueryParam(query, values);
			setQueryParam(countQuery, values);
			Object obj = countQuery.uniqueResult();
			pageBean.setCount(getCount(obj));
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getLimit());
			pageBean.setData(query.list());
			return pageBean;
		} finally {
			closeSessionIfNecessary();
		}
	}

	public synchronized <T> PageBean<T> findBySQL(PageBean<T> pageBean, String sql,
			String[] paramNames, Object[] params) {
		try {
			Query query = currentSession().createSQLQuery(sql);
			Query countQuery = currentSession().createSQLQuery(toCountSQL(sql));
			setQueryParam(query, paramNames, params);
			setQueryParam(countQuery, paramNames, params);
			Object obj = countQuery.uniqueResult();
			pageBean.setCount(getCount(obj));
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getLimit());
			pageBean.setData(query.list());
			return pageBean;
		} finally {
			closeSessionIfNecessary();
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
		}
		formatQl = formatQl.replaceFirst(pOrderStr, "");
		return formatQl;
	}

	public String toCountSQL(String sql) {
		if (null == sql || sql.trim().equals(""))
			return "";
		return "SELECT COUNT(*) FROM (" + sql + ") as COUNT";
	}

	public void setQueryParam(Query query, Object... values) {
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
	}
	
	public void setQueryParam(Query query, String[] paramNames, Object[] params) {
		if (paramNames == null || params == null)
			return;
		for (int i = 0; i < paramNames.length && i < params.length; i++) {
			query.setParameter(paramNames[i], params[i]);
		}
	}

	public void setListQueryParam(Query query, String[] listParamNames,
			Collection<?>[] listParams) {
		if (listParamNames == null || listParams == null)
			return;
		for (int i = 0; i < listParamNames.length; i++) {
			query.setParameterList(listParamNames[i], listParams[i]);
		}
	}
}