package com.netbull.shop.common.datacache;
/**
 * 数据库更新通知记录的封装
 *
 */
public class DataNotifyLog {
	  /**
     * 记录编号
     */
    int SN = 0;
    /**
     * 记录类型，INS为插入/更新，DEL为删除，
     */
    String CMD_TYPE = "";
    /**
     * 更新记录对应的表名
     */
    String TABLE_NAME = null;
    /**
     * 记录主键
     */
    String PK_VALUES = null;
    /**
     * where条件
     */
    String WHERE_CONDITION = null;
}
