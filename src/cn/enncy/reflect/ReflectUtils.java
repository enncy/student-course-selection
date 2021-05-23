package cn.enncy.reflect;


import cn.enncy.scs.pojo.BaseObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * //TODO
 * <br/>Created in 14:29 2021/5/23
 *
 * @author: enncy
 */
public class ReflectUtils {

    /**
     * 获取对象中的 key - value 键值对的集合
     *
     * @param object 对象
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> getObjectValueMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        Field[] field = getObjectFields(object.getClass());

        for (Field f : field) {
            try {
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                map.put(f.getName(), f.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    /**
     * 获取对象的属性
     *
     * @param clazz 基本对象类
     * @return: java.lang.reflect.Field[]
     */
    public static Field[] getObjectFields(Class clazz) {
        return getObjectFields(clazz, false);
    }

    /**
     * 获取基本类型对象的属性，并设置是否显示时间
     *
     * @param clazz          基本对象类
     * @param showTimeDetail 是否显示时间
     * @return: java.lang.reflect.Field[]
     */
    public static Field[] getObjectFields(Class clazz, boolean showTimeDetail) {
        List<Field> list = new LinkedList<>(Arrays.asList(clazz.getDeclaredFields()));

        if (clazz.getSuperclass().equals(BaseObject.class)) {
            list.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        }
        //id在前显示
        Field id = null;
        for (Field field : list) {
            if ("id".equals(field.getName())) {
                id = field;
                break;
            }
        }
        list.remove(id);
        //是否显示时间
        if (showTimeDetail) {
            list.removeIf(field -> "update_time".equals(field.getName()));
            list.removeIf(field -> "create_time".equals(field.getName()));
        }
        ((LinkedList<Field>) list).addFirst(id);
        return list.toArray(new Field[]{});
    }

    /**
     * 获取多个对象中的 key - value 键值对的集合
     *
     * @param objects 对象的数组，将全部属性一并加入
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> getObjectsValueMap(BaseObject... objects) {
        Map<String, Object> map = new HashMap<>();
        for (BaseObject object : objects) {
            map.putAll(getObjectValueMap(object));
        }
        return map;
    }


    /**
     * 将对象的值转换成数组
     *
     * @param object    对象
     * @return: java.lang.Object[]
     */
    public static Object[] objectValueToArray(Object object) {
        Field[] declaredFields = getObjectFields(object.getClass());

        Object[] valueArray = new Object[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                valueArray[i] = field.get(object);
            } catch (IllegalAccessException e) {
                valueArray[i] = "null";
                e.printStackTrace();
            }
        }
        return valueArray;
    }

}
