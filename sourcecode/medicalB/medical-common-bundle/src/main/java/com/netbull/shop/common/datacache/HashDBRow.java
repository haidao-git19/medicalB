package com.netbull.shop.common.datacache;

/**
 * 封装的数据库中的数据
 * @author yeah
 *
 */
public class HashDBRow {
	/**
     * 实例中包括的每列数据
     */
    Object[] column = null;

    /**
     * 实例相关的DCTable，其中包括列信息
     */
    Table dcTable = null;

    /**
     * 构造方法，产生一个新的实例
     * 
     * @param dcTable
     *            实例相关的DCTable
     */
    public HashDBRow(Table dcTable) {
        this.dcTable = dcTable;
        column = new Object[dcTable.arrCols.length];
    }

    /**
     * 取该记录中指定字段的值
     * 
     * @param strColumnName
     *            字段名
     * @return 存在HASH缓存中的对象生成的字符串, 调用该Object的toString()方法生成, 无该对象返回null
     */
    public String getString(String strColumnName) {
        Object o = getObject(strColumnName);
        return o == null ? null : o.toString();
    }

    /**
     * 取该记录中指定字段的值
     * 
     * @param i
     *            该字段位置
     * @return 存在HASH缓存中的对象生成的字符串, 调用该Object的toString()方法生成, 无该对象返回null
     */
    public String getString(int iColumnIndex) {
        Object o = getObject(iColumnIndex);
        return o == null ? null : o.toString();
    }

    /**
     * 取该记录中指定字段的值
     * 
     * @param strColumnName
     *            字段名
     * @return 存在HASH缓存中的对象, 可能为Integer, Double, String, byte[], Date五种,
     *         无该对象返回null
     */
    public Object getObject(String strColumnName) {
        Column col = (Column) dcTable.hashCols.get(strColumnName);
        if (col == null) {
            return null;
        }
        return column[col.sn];
    }

    /**
     * 取该记录中指定字段的值
     * 
     * @param i
     *            该字段位置
     * @return 存在HASH缓存中的对象, 可能为Integer, Double, String, byte[], Date五种,
     *         无该对象返回null
     */
    public Object getObject(int iColumnIndex) {
        return column[iColumnIndex];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("*** Begin of the Row ***\r\n");
        for (int i = 0; i < column.length; i++) {
            sb.append("\r\nNO.").append(i).append(" $$ ");
            sb.append(dcTable.arrCols[i].name).append(" $$ ");
            sb.append(column[i]);
        }
        sb.append("\r\n\r\n*** End of the Row ***");
        return sb.toString();
    }

}
