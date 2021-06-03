package cn.enncy.reflect;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.annotation.TimeFormat;

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
                ReflectUtils.accessible(f);
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
        return getObjectFields(clazz, true);
    }

    public static Field[] getObjectFields(Class... clazz) {
        List<Field> list = new ArrayList<>();
        for (Class aClass : clazz) {
            list.addAll(Arrays.asList(getObjectFields(aClass, true)));
        }
        return list.toArray(new Field[0]);
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
        if (!showTimeDetail) {
            list.removeIf(field -> field.isAnnotationPresent(TimeFormat.class));
        }
        ((LinkedList<Field>) list).addFirst(id);
        return list.toArray(new Field[]{});
    }


    public static Field getObjectField(Class clazz, String fieldName) {
        List<Field> list = new LinkedList<>(Arrays.asList(clazz.getDeclaredFields()));

        if (clazz.getSuperclass().equals(BaseObject.class)) {
            list.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        }
        for (Field field : list) {
            if (fieldName.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }

    /**
     * 获取多个对象中的 key - value 键值对的集合
     *
     * @param objects 对象的数组，将全部属性一并加入
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> getObjectsValueMap(Object... objects) {
        Map<String, Object> map = new HashMap<>();
        for (Object object : objects) {
            map.putAll(getObjectValueMap(object));
        }
        return map;
    }


    /**
     * 将对象的值转换成数组
     *
     * @param object    对象
     * @param reflectDataRender 值渲染器
     * @return: java.lang.Object[]
     */
    public static Object[] objectValueToArray(Object object, ReflectDataRender reflectDataRender) {
        Field[] declaredFields = getObjectFields(object.getClass());

        Object[] valueArray = new Object[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            ReflectUtils.accessible(field);
            try {
                valueArray[i] = reflectDataRender.render(field,field.get(object));
            } catch (IllegalAccessException e) {
                valueArray[i] = "null";
                e.printStackTrace();
            }
        }
        return valueArray;
    }
    public static Object[] objectValueToArray(Object object) {
        return objectValueToArray(object, (field,data) -> data);
    }



    /**
     * 将数组的值赋值给对象
     *
     * @param values 数组
     * @param target 目标对象
     * @return: java.lang.Object[]
     */
    public static Object valueArrayToObject(Object[] values, Object target) throws Exception {
        Field[] declaredFields = ReflectUtils.getObjectFields(target.getClass());

        if (values.length != declaredFields.length) {
            throw new Exception("values length is not match target object fields length");
        }
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];

            ReflectUtils.accessible(declaredField);
            declaredField.set(target, values[i]);
        }
        return target;
    }

    /**
     * 设置属性可读写
     *
     * @param field 属性
     * @return: void
     */
    public static void accessible(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
    }

}
