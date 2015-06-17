package com.netbull.shop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.netbull.shop.dao.IDao;
import com.netbull.shop.dao.JpaDao;

/**
 * 帮助前端在使用jquery datatables 的情况下，进行后端排序
 * 
 * @author pengwu2
 * 
 */
public abstract class DataTablesQuery {

	/**
	 * 对于全表排序，获取带有排序的查询语句
	 * 
	 * @param ql
	 *            原查询语句
	 * @param params
	 *            前端由datatable产生的参数
	 * @return
	 */
	public String toSortQL(String ql, Map<String, String> params) {
		String sortCol = params.get("iSortCol_0");
		String sortDir = params.get("sSortDir_0");
		String sColumns = params.get("sColumns");
		if (StringUtils.isEmpty(sortCol)) {
			return ql;
		}
		int sortIndex = 0;
		try {
			sortIndex = Integer.parseInt(sortCol);
		} catch (NumberFormatException e) {
			return ql;
		}

		if (StringUtils.isEmpty(sortDir)) {
			return ql;
		}
		sortDir = sortDir.equalsIgnoreCase("asc") ? "ASC" : "DESC";

		if (StringUtils.isEmpty(sColumns)) {
			return ql;
		}
		String[] cols = sColumns.split(",");
		if (sortIndex >= cols.length) {
			return ql;
		}
		String colName = cols[sortIndex];
		if (StringUtils.isEmpty(colName)) {
			return ql;
		}
		String pOrderStr = "\\s*(o|O)(r|R)(d|D)(e|E)(r|R)\\s+(b|B)(y|Y).*$";
		ql = ql.replaceFirst(pOrderStr, "");
		ql += " ORDER BY " + colName + " " + sortDir;
		return ql;
	}

	/**
	 * 分页排序，对部分数据进行排序
	 * 
	 * @param aaData
	 *            要返回前端的数据
	 * @param params
	 *            前端由datatable产生的参数
	 */
	public void sort(List<List<Object>> aaData, Map<String, String> params) {
		String sortCol = params.get("iSortCol_0");
		String sortDir = params.get("sSortDir_0");
		if (StringUtils.isEmpty(sortCol)) {
			return;
		}
		int sortIndex = 0;
		try {
			sortIndex = Integer.parseInt(sortCol);
		} catch (NumberFormatException e) {
			return;
		}

		if (StringUtils.isEmpty(sortDir)) {
			return;
		}
		final int _sortIndex = sortIndex;
		final String _sortDir = sortDir;
		Collections.sort(aaData, new Comparator<List<Object>>() {
			@SuppressWarnings("unchecked")
			public int compare(List<Object> o1, List<Object> o2) {
				try {
					Comparable<Object> v1 = (Comparable<Object>) o1
							.get(_sortIndex);
					Comparable<Object> v2 = (Comparable<Object>) o2
							.get(_sortIndex);
					if (_sortDir.equalsIgnoreCase("asc")) {
						return v1.compareTo(v2);
					} else if (_sortDir.equalsIgnoreCase("desc")) {
						return v2.compareTo(v1);
					} else {
						return 0;
					}
				} catch (Exception e) {
					return 0;
				}
			}
		});
	}

	public Map<String, Object> queryPage(Map<String, String> params,
			JpaDao jpaDao) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean<Object> pageBean = new PageBean<Object>(0, 20);
		String iDisplayStart = params.get("iDisplayStart");
		String iDisplayLength = params.get("iDisplayLength");
		if (StringUtils.hasText(iDisplayStart)) {
			pageBean.setStart(Integer.parseInt(iDisplayStart));
		}
		if (StringUtils.hasText(iDisplayLength)) {
			pageBean.setLimit(Integer.parseInt(iDisplayLength));
		}
		List<Object> paramValues = new ArrayList<Object>();
		String hql = buildQuery(params, paramValues);
		hql = toSortQL(hql, params);
		jpaDao.find(pageBean, hql, paramValues.toArray());
		int sEcho = Integer.parseInt(params.get("sEcho"));
		result.put("sEcho", sEcho + 1);
		result.put("aaData", toRowDatas(pageBean.getData()));
		result.put("iTotalRecords", pageBean.getCount());
		result.put("iTotalDisplayRecords", pageBean.getCount());
		return result;
	}
	
	public Map<String, Object> queryPage(Map<String, String> params,
			IDao dao) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean<Object> pageBean = new PageBean<Object>(0, 20);
		String iDisplayStart = params.get("iDisplayStart");
		String iDisplayLength = params.get("iDisplayLength");
		if (StringUtils.hasText(iDisplayStart)) {
			pageBean.setStart(Integer.parseInt(iDisplayStart));
		}
		if (StringUtils.hasText(iDisplayLength)) {
			pageBean.setLimit(Integer.parseInt(iDisplayLength));
		}
		List<Object> paramValues = new ArrayList<Object>();
		String hql = buildQuery(params, paramValues);
		hql = toSortQL(hql, params);
		dao.find(pageBean, hql, paramValues.toArray());
		int sEcho = Integer.parseInt(params.get("sEcho"));
		result.put("sEcho", sEcho + 1);
		result.put("aaData", toRowDatas(pageBean.getData()));
		result.put("iTotalRecords", pageBean.getCount());
		result.put("iTotalDisplayRecords", pageBean.getCount());
		return result;
	}

	public Map<String, Object> queryPageInMemory(Map<String, String> params,
			JpaDao jpaDao) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean<Object> pageBean = new PageBean<Object>(0, 20);
		String iDisplayStart = params.get("iDisplayStart");
		String iDisplayLength = params.get("iDisplayLength");
		if (StringUtils.hasText(iDisplayStart)) {
			pageBean.setStart(Integer.parseInt(iDisplayStart));
		}
		if (StringUtils.hasText(iDisplayLength)) {
			pageBean.setLimit(Integer.parseInt(iDisplayLength));
		}
		List<Object> paramValues = new ArrayList<Object>();
		String hql = buildQuery(params, paramValues);
		hql = toSortQL(hql, params);
		List<Object> datas = jpaDao.find(hql, paramValues.toArray());
		processInMemory(datas,params);
		int size = datas.size();
		pageBean.setCount(size);
		int start = pageBean.getStart();
		int end = pageBean.getStart() + pageBean.getLimit();
		if (start < size && end <= size) {
			datas = datas.subList(start, end);
		}
		pageBean.setData(datas);
		int sEcho = Integer.parseInt(params.get("sEcho"));
		result.put("sEcho", sEcho + 1);
		result.put("aaData", toRowDatas(datas));
		result.put("iTotalRecords", pageBean.getCount());
		result.put("iTotalDisplayRecords", pageBean.getCount());
		return result;
	}
	
	public Map<String, Object> queryPageInMemory(Map<String, String> params,
			IDao dao) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean<Object> pageBean = new PageBean<Object>(0, 20);
		String iDisplayStart = params.get("iDisplayStart");
		String iDisplayLength = params.get("iDisplayLength");
		if (StringUtils.hasText(iDisplayStart)) {
			pageBean.setStart(Integer.parseInt(iDisplayStart));
		}
		if (StringUtils.hasText(iDisplayLength)) {
			pageBean.setLimit(Integer.parseInt(iDisplayLength));
		}
		List<Object> paramValues = new ArrayList<Object>();
		String hql = buildQuery(params, paramValues);
		hql = toSortQL(hql, params);
		List<Object> datas = dao.find(hql, paramValues.toArray());
		processInMemory(datas,params);
		int size = datas.size();
		pageBean.setCount(size);
		int start = pageBean.getStart();
		int end = pageBean.getStart() + pageBean.getLimit();
		if (start < size && end <= size) {
			datas = datas.subList(start, end);
		}
		pageBean.setData(datas);
		int sEcho = Integer.parseInt(params.get("sEcho"));
		result.put("sEcho", sEcho + 1);
		result.put("aaData", toRowDatas(datas));
		result.put("iTotalRecords", pageBean.getCount());
		result.put("iTotalDisplayRecords", pageBean.getCount());
		return result;
	}
	
	public Map<String,Object> queryPage(Map<String,String> params,InvokeQuery invoke){
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean<Object> pageBean = new PageBean<Object>(0, 20);
		String iDisplayStart = params.get("iDisplayStart");
		String iDisplayLength = params.get("iDisplayLength");
		if (StringUtils.hasText(iDisplayStart)) {
			pageBean.setStart(Integer.parseInt(iDisplayStart));
		}
		if (StringUtils.hasText(iDisplayLength)) {
			pageBean.setLimit(Integer.parseInt(iDisplayLength));
		}
		List<Object> paramValues = new ArrayList<Object>();
		String hql = buildQuery(params, paramValues);
		hql = toSortQL(hql, params);
		invoke.doQuery(pageBean, hql, paramValues.toArray());
		int sEcho = Integer.parseInt(params.get("sEcho"));
		result.put("sEcho", sEcho + 1);
		result.put("aaData", toRowDatas(pageBean.getData()));
		result.put("iTotalRecords", pageBean.getCount());
		result.put("iTotalDisplayRecords", pageBean.getCount());
		return result;
	}

	protected void processInMemory(List<Object> datas,
			Map<String, String> params) {

	}

	protected Object toRowDatas(List<Object> datas) {
		return datas;
	}

	protected abstract String buildQuery(Map<String, String> params,
			List<Object> paramValues);
	
	public static interface InvokeQuery {

		void doQuery(PageBean<Object> pageBean, String hql, Object[] array);
		
	}

}
