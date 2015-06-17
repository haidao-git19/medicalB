package com.netbull.shop.common.vo;

import java.io.Serializable;

/**
 * 
 * 分页信息VO. 需要分页的需继承此类.
 *
 */
public class Page implements Serializable{
	/** 
	 *  
	 */
	private static final long serialVersionUID = -4665419819543L;
	/**
	 * 默认页面大小
	 */
	public static final Integer DEFAUL_PAGE_SIZE = Integer.valueOf(5);
	/**
	 * 页号基数,从0开始.
	 */
	
	public static final Integer PAGE_NO_BASE = Integer.valueOf(0);
	
	// 页面显示记录条数
	private Integer pageSize = DEFAUL_PAGE_SIZE;

	//当前页号
	private Integer index = PAGE_NO_BASE;

	//开始记录
	private Integer start = Integer.valueOf(0);//从1开始(包含当前值)

	//结束记录
	private Integer end = pageSize;//从1开始(包含当前值)
	
	private Integer total;// 总记录数

	private Integer pageCount;// 总页数

	/**
	 * 计算页数、起始记录、结束记录行号
	 */
	public void resetPage()
	{
		pageSize = (null == pageSize || pageSize < 1) ? DEFAUL_PAGE_SIZE : pageSize;
		
		if (null == total || total == 0)
		{
			pageCount = 0;
			start = 1;
			end = pageSize;
			index = 0;
			return;
		}
		
		pageCount = total / pageSize;
		pageCount = (total % pageSize == 0) ? pageCount : pageCount + 1;
				
		index = (null == index) ? PAGE_NO_BASE : index;
		index = (index  < 0) ? 0 : index;
		index = (index >= pageCount) ? pageCount - 1 : index;
		
		//计算开始、结束记录行号
		start = index * pageSize + 1;
		end = (index + 1) * pageSize;
	}
	
	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getIndex()
	{
		return index;
	}

	public void setIndex(Integer index)
	{
		this.index = index;
	}

	public Integer getStart()
	{
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}

	public Integer getEnd()
	{
		return end;
	}

	public void setEnd(Integer end)
	{
		this.end = end;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public Integer getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(Integer pageCount)
	{
		this.pageCount = pageCount;
	}
	/**
	 * 从给定的PagesVo对象复制属性值.
	 * @param vo 值来源对象, 如果为null, 则直接返回.
	 */
	public void copyValue(Page vo)
	{
		if(null == vo)
		{
			return;
		}
		this.pageSize = vo.pageSize;
		this.index = vo.index;
		this.start = vo.start;
		this.end = vo.end;
		this.total = vo.total;
		this.pageCount = vo.pageCount;
	}
}
