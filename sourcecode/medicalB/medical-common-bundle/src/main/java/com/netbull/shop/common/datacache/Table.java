package com.netbull.shop.common.datacache;

import java.util.HashMap;
import java.util.Iterator;

public class Table {
	 /**
     * 数据库用户的默认schema
     */
    static String defaultSchema = "TBS_BILL_DATA01";

    /**
     * 当前实例的schema
     */
    String schema = defaultSchema;

    /**
     * 表名称
     */
    String name = "";

    /**
     * 加载数据使用的where条件
     */
    String sqlWhereClause = "";

    /**
     * 表中包含的所有列信息
     */
    Column[] arrCols = null;

    /**
     * 表中包含的所有列信息，key为列名称，value为DCColumn对象
     */
    @SuppressWarnings("unchecked")
	HashMap hashCols = null;

    /**
     * 表的主键列名，可能有多个。
     */
    // String[] arrPKeyNames = null;
    /**
     * 本表索引的集合，key为索引名称，value为HashDBIndex对象
     */
    @SuppressWarnings("unchecked")
	HashMap indexMap = null;

    /**
     * HSql中的建表脚本
     */
    String createTableScript = null;

    /**
     * HSql中的建索引脚本，可能有多条
     */
    String[] arrIndexScript = null;

    /**
     * 本表插入数据的语句，形如insert into name (column1, column2, ...) values(?, ?, ...)
     */
    String insertScript = null;

    /**
     * 表是否已经初始化，设置了createTableScript、arrIndexScript、insertScript之后该属性置为true
     */
    @SuppressWarnings("unchecked")
	//boolean isInit = false;

    Table() {
        indexMap = new HashMap();
    }

    /**
     * 设置当前表的列信息
     * 
     * @param arrCols
     *            列信息数组
     */
    @SuppressWarnings("unchecked")
	void setArrCols(Column[] arrCols) {
        this.arrCols = arrCols;
        int iSize = arrCols.length;
        hashCols = new HashMap(iSize * 2);
        for (int i = 0; i < iSize; i++) {
            Column col = arrCols[i];
            col.sn = i;
            // 此处不需要同步
            hashCols.put(col.name, col);
        }
    }

    @SuppressWarnings("unchecked")
	void addIndex(String idxName, String idxValue) {
        DCManager.logger.debug("Table: " + name + ", IdxName: " + idxName
                + ", IdxValue: " + idxValue);
        indexMap.put(idxName, new HashDBIndex(idxName, idxValue));
    }

    @SuppressWarnings("unchecked")
	void clearAllIndex() {
        Iterator it = indexMap.keySet().iterator();
        while (it.hasNext())
            ((HashDBIndex) indexMap.get(it.next())).clear();
    }

//    @SuppressWarnings("unchecked")
//	public Object clone() {
//        Table newone = new Table();
//        newone.schema = schema;
//        newone.name = name;
//        newone.sqlWhereClause = sqlWhereClause;
//        newone.createTableScript = createTableScript;
//        newone.arrIndexScript = arrIndexScript;
//        newone.insertScript = insertScript;
//        newone.setArrCols(arrCols);
//        Iterator it = indexMap.keySet().iterator();
//        while (it.hasNext()) {
//            HashDBIndex old = (HashDBIndex)indexMap.get(it.next());
//            newone.addIndex(old.getIndexName(), old.getIndexContent());
//        }
//
//        return newone;
//    }

}
