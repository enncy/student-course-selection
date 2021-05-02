package cn.enncy.mybatis;


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
public interface ExecuteCallback {
    default Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
        return null;
    }
    default int execute(int count) throws SQLException {
        return 0;
    }
}
