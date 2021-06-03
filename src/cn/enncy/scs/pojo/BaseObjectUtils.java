package cn.enncy.scs.pojo;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.annotation.ForeignInfo;
import cn.enncy.scs.pojo.annotation.Info;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.service.SettingService;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * //TODO   基本对象的格式化工具类
 * <br/>Created in 20:32 2021/5/29
 *
 * @author: enncy
 */
public class BaseObjectUtils {


    /**
     * 获取属性名
     *
     * @param field 属性
     * @return: java.lang.String
     */
    public static String getFieldName(Field field) {
        if (field.isAnnotationPresent(Info.class)) {
            Info info = field.getAnnotation(Info.class);
            return info.value();
        } else {
            ReflectUtils.accessible(field);
            return field.getName();
        }
    }

    /**
     * 获取属性名
     *
     * @param fields 属性
     * @return: java.lang.String
     */
    public static String[] getFieldName(Field... fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(getFieldName(field));
        }
        return fieldNames.toArray(new String[0]);
    }


    /**
     * 获取属性上的外键信息
     * Map<Integer,Object>  中 key 是 信息的id ， value 是根据 filedName 获取的属性值
     *
     * @param field 属性
     * @return: Map<Integer, Object>
     */
    public static Map<Integer, String> getForeignInfos(Field field) throws IllegalAccessException {
        Map<Integer, String> allMap = new LinkedHashMap<>();
        if (field.isAnnotationPresent(ForeignInfo.class)) {
            //获取外键信息
            ForeignInfo foreignInfo = field.getAnnotation(ForeignInfo.class);
            //获取外键业务
            BaseService service = ServiceFactory.getService(foreignInfo.service());
            //通过当前属性值，关联到外键的目标对象
            List<BaseObject> all = service.findAll();

            for (BaseObject baseObject : all) {
                //新建集合
                Map<Integer, String> foreignInfos = new LinkedHashMap<>();
                //找到带有 ForeignInfo 注解，和 外键信息指定名字的 属性
                String[] fieldNames = foreignInfo.fieldName().split(",");
                for (String fieldName : fieldNames) {
                    //获取指定的属性名
                    Field objectField = ReflectUtils.getObjectField(baseObject.getClass(), fieldName);

                    assert objectField != null;
                    //如果属性包含外键
                    if (objectField.isAnnotationPresent(ForeignInfo.class)) {
                        Map<Integer, String> newMap = getForeignInfos(objectField);
                        //遍历，并合并每一个值
                        newMap.forEach(((integer, string) -> foreignInfos.merge(integer, string, (a, b) -> a + "-" + b)));
                    }
                    //获取属性值
                    else {
                        ReflectUtils.accessible(objectField);
                        foreignInfos.put(baseObject.getId(), String.valueOf(objectField.get(baseObject)));
                    }
                }
                allMap.putAll(foreignInfos);
            }
        }
        return allMap;
    }


    /**
     * 获取外键上的信息
     *
     * @param baseObject 默认对象
     * @param field      包含外键的属性
     * @return: java.lang.Object
     */
    public static Object getForeignInfo(BaseObject baseObject, Field field) throws IllegalAccessException {
        //获取外键注解
        ForeignInfo foreignInfo = field.getAnnotation(ForeignInfo.class);
        //通过工厂获取外键业务
        BaseService foreignService = ServiceFactory.getService(foreignInfo.service());
        ReflectUtils.accessible(field);
        //获取外键id
        int id = (int) field.get(baseObject);
        //通过外键 id 获取外键的对象
        BaseObject foreignObject = foreignService.findOneById(id);
        //获取指定的外键名
        Field declaredField = ReflectUtils.getObjectField(foreignObject.getClass(), foreignInfo.fieldName());
        assert declaredField != null;
        ReflectUtils.accessible(declaredField);
        return declaredField.get(foreignObject);
    }


    /**
     * 格式化时间
     *
     * @param time
     * @return: java.lang.String
     */
    public static String getFormatTime(long time) {
        SettingService settingService = ServiceFactory.getService(SettingService.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(settingService.getTimeFormat());
        return simpleDateFormat.format(new Date(time));
    }


}
