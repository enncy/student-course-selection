package cn.enncy.swing.utils.database;


import cn.enncy.exception.SqlAnnotationNotFoundException;
import cn.enncy.swing.utils.PropertiesUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

/**
 * //TODO
 * <br/>Created in 13:31 2021/4/14
 *
 * @author: enncy
 */

public class DBUtils {

    private static final String INSERT = "INSERT";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";
    private static final String SELECT = "SELECT";

    private static PropertiesUtil setting = new PropertiesUtil("src/setting.properties");

    public static Object execute(String sql, ExecuteCallback executeCallBack) {
        Connection connection = null;
        Statement statement = null;
        try {
            //启动JDBC驱动
            String driver = setting.getString("driver");

            Class.forName(driver);
            //链接数据库
            connection = DriverManager.getConnection(
                    setting.getString("url"),
                    setting.getString("user"),
                    setting.getString("pwd"));
            //查询数据
            statement = connection.createStatement();
            //执行sql

            if (sql.startsWith(INSERT) || sql.startsWith(UPDATE) || sql.startsWith(DELETE)) {
                int  count = statement.executeUpdate(sql);
                return executeCallBack.execute(count);
            } else if (sql.startsWith(SELECT)) {
                ResultSet resultSet = statement.executeQuery(sql);
                return executeCallBack.executeQuery(resultSet);
            }
            //下面是异常处理
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
