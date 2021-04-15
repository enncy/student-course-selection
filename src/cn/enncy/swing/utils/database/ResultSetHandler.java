package cn.enncy.swing.utils.database;


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
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(!field.canAccess(target)){
                field.setAccessible(true);
            }
            field.set(target,resultSet.getObject(field.getName(), field.getType()));
        }
        return target;
    }

}
