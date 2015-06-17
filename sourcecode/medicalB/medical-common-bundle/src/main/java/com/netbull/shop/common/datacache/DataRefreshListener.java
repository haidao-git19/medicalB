package com.netbull.shop.common.datacache;
import java.util.HashSet;

/**
 * 需要使用刷新机制的类需要实现的接口
 *
 */
public interface DataRefreshListener {
	 /**
     * 数据更新时会调用此方法
     * 
     * @param hsTableNames 有数据更新的表名称
     */
    @SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames);
}
