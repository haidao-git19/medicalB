package com.netbull.shop.common.datacache;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Hash缓存中存放索引
 * @author yeah
 *
 */
public class HashDBIndex {
	 /**
     * 索引名称
     */
    private String indexName = null;

    /**
     * 索引列
     */
    private String[] idxColumns = null;

    /**
     * 索引内容
     */
    @SuppressWarnings("unchecked")
	protected HashMap idxMap = null;

    /**
     * 索引表达式内容
     */
    private String idxContent = null;

    /**
     * 构造方法
     * 
     * @param name
     *            索引名称
     * @param content
     *            索引列，多列之间用“$”分隔
     */
    @SuppressWarnings("unchecked")
	public HashDBIndex(String name, String content) {
        indexName = name;
        idxContent = content;
        idxColumns = content.split("\\$");
        idxMap = new HashMap();
    }

    /**
     * 增加一行数据
     * @param rs 数据集
     * @throws SQLException 
     * @throws Exception  读取数据异常 
     */
    @SuppressWarnings("unchecked")
	public void add(ResultSet rs, HashDBRow row) throws SQLException  {
        String PKValues = rs.getString(idxColumns[0]);
        for (int i = 1; i < idxColumns.length; i++)
            PKValues = PKValues + "$" + rs.getString(idxColumns[i]);
        idxMap.put(PKValues, row);
    }

    @SuppressWarnings("unchecked")
	public void removeValue(HashDBRow row) {
        Iterator iterator = idxMap.keySet().iterator();
        Object key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            if (row.equals(idxMap.get(key)))
                break;
        }
        if (key != null)
            idxMap.remove(key);
    }

    public void clear() {
        idxMap.clear();
    }

    public String getIndexName() {
        return indexName;
    }
    
    public String getIndexContent() {
        return idxContent;
    }

    public String[] getIndexColumns() {
        return idxColumns;
    }

    public int size() {
        return idxMap.size();
    }
}
