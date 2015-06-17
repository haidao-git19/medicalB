package com.netbull.shop.common.page;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PageContainer implements Page {
	/** 记录的总数 */
	private long totalRecords = 0;

	/** 记录的总页数 */
	private static int pageCount = 0;

	/** 每页显示容量 */
	public static final int DEFAULT_PAGE_ROWS = 10;

	public int pagesize = DEFAULT_PAGE_ROWS;

	/** 得到的页码 */
	public int current = 1;

	/** 查询开始的行数 */
	private int startRow = 1;

	/** 查询结束的行数 */
	private int endRow = 1;

	/** 记录集合 */
	private List<Object> records;
    /** 查询条件*/
	private Map<String,Object> conditions;

	public PageContainer(long totalRecords, int pagesize, int current, List<Object> records, Map<String,Object> conditions) {
		this.totalRecords = totalRecords;
		this.pagesize = pagesize;
		this.current = current;
		this.records = records;
		this.conditions = conditions;
	}

	@Override
	/**
	 * 取总记录数.
	 */
	public long getTotal() {
		return totalRecords;
	}

	@Override
	/**
	 * 取总页数.
	 */
	public int getPageCount() {
		pageCount = (int) (totalRecords / pagesize);
		if (totalRecords % pagesize != 0) {
			pageCount++;
		}
		return pageCount;
	}

	@Override
	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pagesize;
	}

	@Override
	/**
	 * 得到页码
	 * @return
	 */
	public int getCurrent() {
		if (current > getPageCount()) {
			return getPageCount();
		} else if (current < 1) {
			return 1;
		} else {
			return current;
		}
	}

	@Override
	/**
	 * 是否为起始页
	 * @return
	 */
	public boolean hasPrevious() {
		return current == 1;
	}

	@Override
	/**
	 * 是否为末页
	 * @return
	 */
	public boolean hasNext() {
		return current == pageCount;
	}

	@Override
	/**
	 * 得到上一页的页码
	 * @return
	 */
	public int getPrevious() {
		if (current > 1) {
			return current - 1;
		} else {
			return 1;
		}
	}

	@Override
	/**
	 * 得到下一页的页码
	 * @return
	 */
	public int getNext() {
		if (current < getPageCount()) {
			return current + 1;
		} else {
			return pageCount;
		}
	}

	@Override
	/**
	 * 得到该页的数据
	 * @return
	 */
	public Collection<Object> getResult() {
		return records;
	}

	@Override
	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 */
	public int getStart() {
		return (current * pagesize) - pagesize;
	}

	@Override
	/**
	 * 获取任一页第后一条数据在数据集的位置，每页条数使用默认值.
	 *
	 * 
	 */
	public int getEnd() {
		return current * pagesize;
	}

	public List<Object> getRecords() {
		return records;
	}

	public static int startRow(int c, int s, long t) {
		//c = current(c, s, t);
		if(c>0){
			//return (c * s) - s;
			return c;
		}else{
			return 0;
		}
		
	}

	public static int endRow(int c, int s, long t) {
		c = current(c, s, t);
		return (c * s) - s;
	}

	private static int current(int c, int s, long t) {
		if (c > pageCount(s, t)) {
			return pageCount(s, t);
		} else if (c < 1) {
			return 1;
		} else {
			return c;
		}
	}

	public static int pageCount(int sz, long total) {
		int count = (int) (total / sz);
		if (total % sz != 0) {
			count++;
		}
		return count;
	}

	@Override
	public Map<String,Object> getConditionsMap() {
		// TODO Auto-generated method stub
		return null;
	}


}
