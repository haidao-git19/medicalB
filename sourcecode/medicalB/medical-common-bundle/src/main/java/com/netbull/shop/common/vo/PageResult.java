package com.netbull.shop.common.vo;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable
{
	/** 
	 *  
	 */
	private static final long serialVersionUID = -45654891234872L;

	@SuppressWarnings("unchecked")
	private List values;

	private Integer pageNo;// 页号

	private Integer pageSize ;// 每页大小

	private Integer total;// 总记录数

	private Integer pageCount;// 总页数

	@SuppressWarnings("unchecked")
	public List getValues()
	{
		return values;
	}

	public Integer getPageNo()
	{
		return pageNo;
	}

	public Integer getTotal()
	{
		return total;
	}

	public Integer getPageCount()
	{
		return pageCount;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public Integer getEnd()
	{
		return pageCount;
	}

	public Integer getStart()
	{
		return 1;
	}

	@SuppressWarnings("unchecked")
	public void setValues(List values)
	{
		this.values = values;
	}

	public void setPageNo(Integer pageNo)
	{
		this.pageNo = pageNo;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public void setPageCount(Integer pageCount)
	{
		this.pageCount = pageCount;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}
	public void setPage(PagesVo vo)
	{
		this.pageCount = vo.getPageCount();
		this.pageNo = vo.getIndex();
		this.pageSize = vo.getPageSize();
		this.total = vo.getTotal();
	}
}