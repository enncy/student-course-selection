package cn.enncy.mybatis;


import cn.enncy.mybatis.constant.SqlConstant;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**因为 update 操作由于参数非常多，导致sql语句冗余，所以反射获得对象的属性与值，替换 #{SET_ARRAY}
     * 例如 #{SET_ARRAY} : name=#{name} ,account=#{account},pwd=#{pwd}
     * @param sqlString
     * @param paramsMap
     * @return: java.lang.String
     */
    public static String replaceUpdateFields(String sqlString, Map<String, Object> paramsMap){
        String result = sqlString;
        StringBuilder setArray = new StringBuilder();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            if (!"id".equals(entry.getKey())) {
                setArray.append(" ").append(entry.getKey()).append("=").append(entry.getValue()).append(" ,");
            }
        }
        result = replaceParam(result, SqlConstant.SET_ARRAY, setArray);
        return result;
    }

    /**
     * 因为 insert 操作由于参数非常多，导致sql语句冗余，所以反射获得对象的属性与值，替换 #{KEY_ARRAY} 和 #{VALUE_ARRAY}
     * <br/>例如 (#{KEY_ARRAY}) value(#{VALUE_ARRAY}) :  (name,account,pwd) value(enncy,123456,132456)
     * @param sqlString 注解中的 sql 语句
     * @param paramsMap 参数的键值对集合
     * @return: java.lang.String
     */
    public static String replaceInsertFields(String sqlString, Map<String, Object> paramsMap) {
        String result = sqlString;
        StringBuilder keyArray = new StringBuilder();
        StringBuilder valueArray = new StringBuilder();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            if ("id".equals(entry.getKey())) {
                keyArray.append(entry.getKey()).append(",");
                valueArray.append("null").append(",");
            } else {
                keyArray.append(entry.getKey()).append(",");
                valueArray.append("'").append(entry.getValue()).append("'").append(",");
            }
        }
        //消除最后一个 , 符号
        keyArray.replace(keyArray.length() - 1, keyArray.length(), "");
        valueArray.replace(valueArray.length() - 1, valueArray.length(), "");
        result = replaceParam(result, SqlConstant.KEY_ARRAY, keyArray.toString());
        result = replaceParam(result, SqlConstant.VALUE_ARRAY, valueArray.toString());
        result = result.replaceAll("\"", "");
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
    public static String replaceParam(String sql, String key, Object value) {
        return sql.replaceAll("#\\{" + key + "\\}", String.valueOf(value));
    }


    /**
     * 替换 sql 语句中的表名
     *
     * @param sqlString 注解中的 sql 语句
     * @param tableName 表名
     * @return: java.lang.String
     */
    public static String replaceTableName(String sqlString, String tableName) throws SQLException {
        if (!sqlString.contains(SqlConstant.TABLE_NAME)) {
            throw new SQLException(SqlConstant.TABLE_NAME + " param is not found");
        }
        sqlString = replaceParam(sqlString, SqlConstant.TABLE_NAME, tableName);
        sqlString = sqlString.replaceAll("\"", "");
        return sqlString;
    }

    /**
     * 获取对象中的 key - value 键值对的集合
     *
     * @param objects 对象的数组，将全部属性一并加入
     * @return: java.util.Map<java.lang.String                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                               java.lang.Object>
     */
    public static <T> Map<String, Object> getObjectsValueMap(T... objects) {
        Map<String, Object> map = new HashMap<>();
        for (T object : objects) {
            map.putAll(getObjectValueMap(object));
        }
        return map;
    }

    /**
     * 获取对象中的 key - value 键值对的集合
     *
     * @param t 对象
     * @return: java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               java.lang.Object>
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


    /**
     * 驼峰转下划线
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String humpToUnderline(String str) {
        String regex = "([A-Z])";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group();
            str = str.replaceAll(target, "_"+target.toLowerCase());
        }
        return str;
    }

    /**
     * 下划线转驼峰
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String underlineToHump(String str) {
        String regex = "_(.)";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group(1);
            str = str.replaceAll("_"+target, target.toUpperCase());
        }
        return str;
    }


}
