package cn.enncy.mybatis;


import cn.enncy.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理查询的返回值
 * <br/>Created in 17:48 2021/4/15
 *
 * @author: enncy
 */
public class ResultSetHandler {

    /**
     * 处理查询的返回值，并根据返回类型，创建新的目标返回值对象
     * @param resultSet 查询返回集
     * @param resultType    函数的返回值类型
     * @return: java.lang.Object
     */
    public static  Object createResultTarget(ResultSet resultSet,Class<?>  resultType) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object target = resultType.getConstructor().newInstance();
        Field[] fields = ReflectUtils.getObjectFields(target.getClass());
        for (Field field : fields) {
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
            field.set(target,resultSet.getObject(field.getName(), field.getType()));
        }

        return target;
    }




    public static   Object stringToTarget(String string, Class<?> t) throws  NumberFormatException{


        if(double.class.equals(t)){
            return Double.parseDouble(string);
        }
        else if(int.class.equals(t)){

            return Integer.parseInt(string);
        }
        else if(float.class.equals(t)){
            return Float.parseFloat(string);
        }
        else if(short.class.equals(t)){
            return Short.parseShort(string);
        }
        else if(boolean.class.equals(t)){
            return Boolean.parseBoolean(string);
        }else{
            return string;
        }
    }

}
