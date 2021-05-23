package cn.enncy.mybatis.database;


import cn.enncy.scs.exception.SqlAnnotationNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //TODO
 * <br/>Created in 16:50 2021/4/15
 *
 * @author: enncy
 */
public abstract class ExecuteCallback {
    public  Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
        return resultSet;
    }
    public int execute(int count) throws SQLException {
        return count;
    }
}
