package cn.enncy.scs.pojo;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.service.ServiceFactory;

import java.lang.reflect.Field;
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
     * 获取属性上的外键信息
     * Map<Integer,Object>  中 key 是 信息的id ， value 是根据 filedName 获取的属性值
     *
     * @param field 属性
     * @return: Map<Integer,Object>
     */
    public static Map<Integer,Object> getForeignInfo(Field field) throws IllegalAccessException, NoSuchFieldException {
        if (field.isAnnotationPresent(ForeignInfo.class)) {
            //获取外键信息
            ForeignInfo foreignInfo = field.getAnnotation(ForeignInfo.class);
            ReflectUtils.accessible(field);

            //获取外键业务
            BaseService service = ServiceFactory.getService(foreignInfo.service());
            //通过当前属性值，关联到外键的目标对象
            List<BaseObject> all = service.findAll();
            Map<Integer, Object> stringMap = new LinkedHashMap<>();
            for (BaseObject object : all) {
                //获取外键目标对象的指定属性

                Field targetField = ReflectUtils.getObjectField(object.getClass(), foreignInfo.fieldName());
                //如果外键的属性还存在外键，递归获取
                if(targetField.isAnnotationPresent(ForeignInfo.class)){
                    stringMap.putAll(Objects.requireNonNull(getForeignInfo(targetField)));

                }else{
                    ReflectUtils.accessible(targetField);
                    stringMap.put(object.getId(), targetField.get(object));
                }
            }
            return stringMap;
        }
        return null;
    }

}
