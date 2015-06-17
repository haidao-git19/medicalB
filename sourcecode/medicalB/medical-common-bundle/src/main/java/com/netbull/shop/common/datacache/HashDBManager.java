package com.netbull.shop.common.datacache;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 缓存对调用者提供的接口
 * @author yeah
 *
 */
public class HashDBManager {
	 /**
     * 默认构造方法, 暂无实现必要
     */
    HashDBManager() {
    }

    /**
     * 保存所有需缓存的数据表的Hash表
     */
    @SuppressWarnings("unchecked")
	static HashMap allTables = new HashMap();

    /**
     * 增加一张Hash数据表
     *
     * @param dcTable
     *            与该表相关的DCTable，其中包含表结构信息。
     * @param initSize
     *            表初始大小
     * @return 生成的HashDBTable对象
     */
    @SuppressWarnings("unchecked")
	public static HashDBTable createTable(Table dcTable) {
        HashDBTable hashDBTable = null;
        hashDBTable = new HashDBTable(dcTable);
        // 加载数据的线程有多条，需要同步
        synchronized (allTables) {
            allTables.put(dcTable.name, hashDBTable);
        }

        return hashDBTable;
    }

    /**
     * 替换一张Hash数据表，如果旧表存在，旧表被覆盖；否则加入新表。
     *
     * @param table
     *            HashDBTable实例
     */
    @SuppressWarnings("unchecked")
	public static void replaceTable(HashDBTable table) {
        synchronized (allTables) {
            allTables.put(table.dcTable.name, table);
        }
    }

    /**
     * 向指定表中增加一条数据
     *
     * @param rs
     *            数据集
     * @param hashDBTable
     *            要增加数据的表
     * @return true表示操作成功
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	static boolean insertRow(ResultSet rs, HashDBTable hashDBTable)
            throws SQLException {
        // 生成HashDBRow实例
        HashDBRow row = genRow(rs, hashDBTable);
        // 将HashDBRow实例加入各索引
        Iterator iterator = hashDBTable.dcTable.indexMap.keySet().iterator();
        while (iterator.hasNext()) {
            HashDBIndex index = (HashDBIndex) hashDBTable.dcTable.indexMap
                    .get(iterator.next());
            synchronized (index) {
                index.add(rs, row);
            }
        }
        return true;
    }

    /**
     * 向指定表中增加一条数据
     *
     * @param strPKeyValue
     *            该记录的主键值
     * @param rs
     *            数据集
     * @param dcTable
     *            该表相关的DCTable对象
     * @return true表示操作成功
     * @throws Exception
     */
    static boolean insertRow(ResultSet rs, Table dcTable) throws Exception {
        return insertRow(rs, (HashDBTable) allTables.get(dcTable.name));
    }

    private static HashDBRow genRow(ResultSet rs, HashDBTable hashDBTable)
            throws SQLException {
        HashDBRow hashDBrow = new HashDBRow(hashDBTable.dcTable);
        Column[] arrCols = hashDBTable.dcTable.arrCols;
        int iSize = arrCols.length;
        for (int i = 0; i < iSize; i++) {
            String name = arrCols[i].name;
            Object obj = null;
            switch (arrCols[i].type) {
            case 1: // INTEGER            	
                obj = Integer.valueOf(rs.getInt(name));
                if (obj != null) {
                    hashDBrow.column[i] = obj;
                }
                break;
            case 2: // DOUBLE
                obj = Double.valueOf(rs.getDouble(name));
                if (obj != null) {
                    hashDBrow.column[i] = obj;
                }
                break;
            case 3: // DATE
                obj = rs.getTimestamp(name);
                if (obj != null) {
                    hashDBrow.column[i] = obj;
                }
                break;
            case 4: // BINARY
                obj = readBLOB(rs, name);
                if (obj != null) {
                    hashDBrow.column[i] = obj;
                }
                break;
            default: // VARCHAR
                obj = rs.getString(name);
                if (obj != null) {
                    hashDBrow.column[i] = obj;
                }
                break;
            }
        }
        return hashDBrow;
    }

    /**
     * 读取指定列的BLOB数据
     *
     * @param rs
     *            数据集
     * @param colName
     *            列名称
     * @return 该列包含的BLOB数据，如果该列数据为null则返回null。
     * @throws Exception
     */
    static byte[] readBLOB(ResultSet rs, String colName) throws SQLException {
        Blob blob = rs.getBlob(colName);
        if (blob == null) {
            return null;
        } else {
            return blob.getBytes(1, (int) blob.length());
        }
    }

    /**
     * 从指定表中删除记录
     *
     * @param strTableName
     *            表名称
     * @param strPKeyValue
     *            该条记录的主键值
     * @return true表示操作成功
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	static boolean deleteRow(String strTableName, String strPKeyValue)
            throws Exception {
        HashDBTable hashDBTable = (HashDBTable) allTables.get(strTableName);
        HashDBRow row = hashDBTable.getRow(strPKeyValue);
        // 遍历所有索引
        if (row!=null)
        {//为空的无意义
            Iterator it = hashDBTable.dcTable.indexMap.keySet().iterator();
            while (it.hasNext())
            {
                HashDBIndex index = (HashDBIndex) hashDBTable.dcTable.indexMap
                    .get(it.next());
                synchronized (index)
                {
                    index.removeValue(row);
                }
            }
        }

        return true;
    }

    /**
     * 供各业务组件调用的静态方法, 通过表名取指定数据表
     *
     * @param strTableName
     *            需要的数据表表名
     * @return 包装该数据表的HashDBTable对象
     */
    public static HashDBTable getTable(String strTableName) {
        return (HashDBTable) allTables.get(strTableName);
    }

    /**
     * 供各业务组件调用的静态方法, 通过表名和主键值取一条数据记录
     *
     * @param strTableName
     *            需要的数据表表名
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @return 包装该数据记录的HashDBRow对象
     */
    public static HashDBRow getRow(String strTableName, String strPKeyValue) {
        return getRow(strTableName, "default", strPKeyValue);
    }

    public static HashDBRow getRow(String strTableName, String idxName,
            String strPKeyValue) {
        HashDBTable hashDBTable = (HashDBTable) allTables.get(strTableName);
        if (hashDBTable == null) {// 表不存在，直接返回null
            return null;
        }
        return hashDBTable.getRow(idxName, strPKeyValue);
    }

    /**
     * 供各业务组件调用的静态方法, 通过表名,主键值和字段名取该字段对应的对象
     *
     * @param strTableName
     *            需要的数据表表名
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @param strColumnName
     *            需要的字段名
     * @return 存在HASH缓存中的对象生成的字符串, 调用该Object的toString()方法生成, 无该对象返回null
     */
    public static String getString(String strTableName, String strPKeyValue,
            String strColumnName) {
        return getString(strTableName, "default", strPKeyValue, strColumnName);
    }

    public static String getString(String strTableName, String idxName,
            String strPKeyValue, String strColumnName) {
        Object o = getObject(strTableName,idxName, strPKeyValue, strColumnName);
        return o == null ? null : o.toString();
    }

    /**
     * 供各业务组件调用的静态方法, 通过表名,主键值和字段名取该字段对应的对象
     *
     * @param strTableName
     *            需要的数据表表名
     * @param strPKeyValue
     *            需要的数据记录主键值
     * @param strColumnName
     *            需要的字段名
     * @return 存在HASH缓存中的对象, 可能为Integer, Double, String, byte[], Date五种,
     *         无该对象返回null
     */
    public static Object getObject(String strTableName, String strPKeyValue,
            String strColumnName) {
        return getObject(strTableName, "default", strPKeyValue, strColumnName);
    }

    public static Object getObject(String strTableName, String idxName,
            String strPKeyValue, String strColumnName) {
        HashDBTable hashDBTable = (HashDBTable) allTables.get(strTableName);
        if (hashDBTable == null) {
            return null;
        }
        return hashDBTable.getObject(idxName, strPKeyValue, strColumnName);
    }

    /**
     * 获取指定表的所有索引信息
     *
     * @param tableName
     *            表名
     * @return 索引集合，集合中每个对象为HashDBIndex类型
     */
    @SuppressWarnings("unchecked")
	public static List getAllIndex(String tableName) {
        HashDBTable hashDBTable = (HashDBTable) allTables.get(tableName);
        if (hashDBTable == null) {
            return null;
        }
        Iterator it = hashDBTable.dcTable.indexMap.keySet().iterator();
        List result = new ArrayList();
        while (it.hasNext())
            result.add(hashDBTable.dcTable.indexMap.get(it.next()));
        return result;
    }
}
