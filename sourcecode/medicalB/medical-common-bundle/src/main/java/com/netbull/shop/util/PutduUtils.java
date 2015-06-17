
package com.netbull.shop.util;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class PutduUtils {
   
	private static Logger logger = Logger.getLogger(PutduUtils.class);
    /**
     * 根据列表查询sql生成count (1) 的sql
     * 
     * @param sql
     *            查询sql
     * @return
     */
    public static String getCountSql(StringBuilder sql) {

        int index = StringUtils.lowerCase(sql.toString()).indexOf(" from ");
        sql.substring(index);
        return "select count(1) as value" + sql.substring(index);
    }

    /**
     * 获取字符串长度
     * 
     * @param str
     *            字符串
     * @return
     */
    public static int length(String str) {

        int length = 0;
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 128) {
                length += 2;
            }
            else {
                length++;
            }
        }
        return length;
    }

    /**
     * 生成序列号
     * 
     * @param nextval
     *            序列值
     * @param jdbcTemplate
     *            连接访问对象
     * @param prefix
     *            序列前缀
     * @param length
     *            序列长度
     * @return
     */
    public static String getID(String nextval, JdbcTemplate jdbcTemplate, String prefix, int length) {

        int seq = jdbcTemplate.queryForInt(nextval);
        return prefix + String.format("%1$0" + length + "d", seq);
    }

    /**
     * 获取文件扩展名
     * 
     * @param fileName
     * @return
     */
    public static String getFileNameExtension(String fileName) {

        String fileExt = "";
        if (!StringUtils.isEmpty(fileName)) {
            int beginIndex = fileName.lastIndexOf(".");
            if (beginIndex > 0) {
                fileExt = fileName.substring(beginIndex + 1).intern();
            }
        }
        return fileExt;
    }

    /**
     * 递归解析对象
     * 
     * @param sb
     * @param object
     * @param _class
     */
    private static void recursiveQuery(StringBuilder sb, Object object, Class _class) {

//        if (_class == null) {
//            return;
//        }
//
//        Field[] fields = _class.getDeclaredFields();
//        if (fields == null || fields.length == 0) {
//            return;
//        }
//        Object value = null;
//        String name = null;
//        for (Field field : fields) {
//            name = field.getName();
//            if (name.startsWith("query_")) {
//                field.setAccessible(true);
//                try {
//                    value = field.get(object);
//                }
//                catch (IllegalArgumentException e) {
//                }
//                catch (IllegalAccessException e) {
//                }
//                if (value != null) {
//                    if (value instanceof String) {
//                        queryAppend(sb, name, (String) value);
//                    }
//                    else if (value instanceof Integer || value instanceof Long) {
//                        queryAppend(sb, name, String.valueOf(value));
//                    }
//                }
//            }
//        }
//        recursiveQuery(sb, object, _class.getSuperclass());
    }
    
    
}
