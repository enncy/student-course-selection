package cn.enncy.swing.utils.database;


import cn.enncy.swing.exception.SqlAnnotationNotFoundException;

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
    Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException;
    int execute(int count);
}
