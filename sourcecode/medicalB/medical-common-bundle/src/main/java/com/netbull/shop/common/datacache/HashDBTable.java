package com.netbull.shop.common.datacache;

/**
 * 缓存读取接口
 * @author yeah
 *
 */
public class HashDBTable {
	
    /**
     * 与实例相关的DCTable对象，其中表述表结构等信息
     */
    Table dcTable = null;

    /**
     * 构造方法，产生一个新的实例
     * 
     * @param dcTable
     *            与实例相关的DCTable对象
     * @param initSize
     *            表初始大小
     */
    HashDBTable(Table dcTable) {
        this.dcTable = dcTable;
    }

    /**
     * 获得表中记录数
     * 
     * @return 返回表中记录数
     */
    public int getTableSize() {
        return getDefaultIndex().size();
    }


    /**
     * 供各业务组件调用, 取该表的默认索引的所有主键值列表
     * 
     * @return 所有主键值列表
     */
    public Object[] getPKeyValues() {
        return getDefaultIndex().idxMap.keySet().toArray();
    }

    /**
     * 供各业务组件调用, 取该表的所有记录列表
     * 
     * @return 所有记录列表，其中每个元素为Map.Entry对象，
     *         Map.Entry.getKey()可获取该记录的主键值，Map.Entry.getValue()可获得该记录的HashDBRow对象
     */
    public Object[] getRows() {
        return getDefaultIndex().idxMap.entrySet().toArray();
    }

    /**
     * 供各业务组件调用, 通过表名和主键值取一条数据记录
     * 
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @return 包装该数据记录的HashDBRow对象 无该对象返回null
     */
    public HashDBRow getRow(String strPKeyValue) {
        return getRow(DCManager.DEFALUT_INDEX_NAME, strPKeyValue);
    }

    public HashDBRow getRow(String idxName, String strPKeyValue) {
        return (HashDBRow) ((HashDBIndex) dcTable.indexMap.get(idxName)).idxMap
                .get(strPKeyValue);
    }

    /**
     * 供各业务组件调用, 通过表名和主键值取一条数据记录
     * 
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @param strColumnName
     *            需要的字段名
     * @return 存在HASH缓存中的对象生成的字符串, 调用该Object的toString()方法生成, 无该对象返回null
     */
    public String getString(String strPKeyValue, String strColumnName) {
        return getString(DCManager.DEFALUT_INDEX_NAME, strPKeyValue, strColumnName);
    }

    public String getString(String idxName, String strPKeyValue,
            String strColumnName) {
        Object o = getObject(idxName, strPKeyValue, strColumnName);
        return o == null ? null : o.toString();
    }

    /**
     * 供各业务组件调用, 通过表名和主键值取一条数据记录
     * 
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @param strColumnName
     *            需要的字段名
     * @return 存在HASH缓存中的对象, 可能为Integer, Double, String, byte[], Date五种,
     *         无该对象返回null
     */
    public Object getObject(String strPKeyValue, String strColumnName) {
        return getObject(DCManager.DEFALUT_INDEX_NAME, strPKeyValue, strColumnName);
    }

    public Object getObject(String idxName, String strPKeyValue,
            String strColumnName) {
        HashDBRow hashDBRow = getRow(idxName, strPKeyValue);
        if (hashDBRow == null) {
            return null;
        }
        return hashDBRow.getObject(strColumnName);
    }

    public HashDBIndex getDefaultIndex() {
        return (HashDBIndex) dcTable.indexMap.get(DCManager.DEFALUT_INDEX_NAME);
    }
}
