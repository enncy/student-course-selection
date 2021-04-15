package cn.enncy.swing.utils.database;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理 sql 语句
 * <br/>Created in 16:03 2021/4/15
 *
 * @author: enncy
 */
public class SqlStringHandler {


    /**
     * 根据 paramsMap 替换 sql 语句中的 #{x} 值 , 如果有 id 参数，则设置为空值 null
     *
     * @param sqlString 注解中的 sql 语句
     * @param paramsMap 参数的键值对集合
     * @return: java.lang.String
     */
    public static String replaceParams(String sqlString, Map<String, Object> paramsMap) {
        String result = sqlString;
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = String.valueOf(entry.getValue());
            if (!"id".equals(mapKey)) {
                result = replaceParam(result, mapKey, mapValue);
            } else {
                result = replaceParam(result, "id", null);
            }
        }
        return result;
    }


    /**
     * 用 value 替换 sql key 的值
     *
     * @param sql   sql语句
     * @param key   被替换的值
     * @param value 替换的值
     * @return: java.lang.String
     */
    public static String replaceParam(String sql, String key, String value) {
        return sql.replaceAll("#\\{"+key+"\\}","\""+value+"\"");
    }


    /**
     * 替换 sql 语句中的表名
     *
     * @param sqlString 注解中的 sql 语句
     * @param tableName 表名
     * @return: java.lang.String
     */
    public static String replaceTableName(String sqlString, String tableName) {
        return sqlString.replaceAll("#\\{table_name\\}", tableName);
    }

    /**
     * 获取对象中的 key - value 键值对的集合
     *
     * @param t 对象
     * @return: java.util.Map<java.lang.String               ,               java.lang.Object>
     */
    public static <T> Map<String, Object> getObjectValueMap(T t) {
        Map<String, Object> map = new HashMap<>();
        Field[] field = t.getClass().getDeclaredFields();
        for (Field f : field) {
            try {
                if (!f.canAccess(t)) {
                    f.setAccessible(true);
                }
                map.put(f.getName(), f.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }



}
