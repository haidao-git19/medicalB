package com.netbull.shop.common.datacache;
/**
 * 存放表的列名称信息
 * @author yangfeng
 *
 */
public class Column {
	/**
     * 内置的列数据类型定义
     */
    static String[] arrTypes = { "VARCHAR", "INTEGER", "DOUBLE", "TIMESTAMP",
            "BINARY" };
    /**
     * 列名称
     */
    String name = null;
    /**
     * 列类型
     */
    int type = 0;
    /**
     * 列序号
     */
    int sn = 0;
}
