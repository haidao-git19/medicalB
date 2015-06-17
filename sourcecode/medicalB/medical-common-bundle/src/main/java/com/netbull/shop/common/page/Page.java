package com.netbull.shop.common.page;

import java.util.Map;

/**
 * 
 * 分页器接口，根据page,pageSize,total用于页面上分页显示多项内容，计算页码和当前页的偏移量，方便页面分页使用.
 * 
 */
public interface Page {
	/**
	 * 取总记录数.
	 */
	public long getTotal();

	/**
	 * 取总页数.
	 */
	public int getPageCount();

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize();

	/**
	 * 得到页码
	 * 
	 * @return
	 */
	public int getCurrent();

	/**
	 * 是否为起始页
	 * 
	 * @return
	 */
	public boolean hasPrevious();

	/**
	 * 是否为末页
	 * 
	 * @return
	 */
	public boolean hasNext();

	/**
	 * 得到上一页的页码
	 * 
	 * @return
	 */
	public int getPrevious();

	/**
	 * 得到下一页的页码
	 * 
	 * @return
	 */
	public int getNext();

	/**
	 * 得到该页的数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public java.util.Collection getResult();

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * 
	 */
	public int getStart();

	/**
	 * 获取任一页第后一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * 
	 */
	public int getEnd();

	public Map getConditionsMap();

}
