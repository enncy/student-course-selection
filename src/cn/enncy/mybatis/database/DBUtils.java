package cn.enncy.mybatis.database;


import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.scs.exception.SqlAnnotationNotFoundException;
import cn.enncy.scs.utils.PropertiesUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

/**
 * //TODO
 * <br/>Created in 13:31 2021/4/14
 *
 * @author: enncy
 */

public class DBUtils {


    private static final Logger logger = Logger.getLogger(DBUtils.class);
    private static PropertiesUtils setting = PropertiesUtils.getInstance("setting.properties");


    /**
     * 执行简单的 sql 语句，没有返回值
     *
     * @param sql sql语句
     * @return: void
     */
    public static void execute(String sql) throws SQLException {
        Connection connection = createConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        executeSql(sql, statement);
        connection.close();
        statement.close();
    }


    /**
     * 执行sql语句，而且可以在回调函数中执行业务逻辑
     *
     * @param sql   sql语句
     * @param executeCallBack   回调
     * @return: java.lang.Object
     */
    public static Object execute(String sql, ExecuteCallback executeCallBack) throws SQLException {


        Connection connection = createConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        try {
            //执行sql语句
            return executeSql(sql, statement, executeCallBack);
        }
        //下面是异常处理
        catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | SqlAnnotationNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.err.println("出现重复的值：" + e.getMessage());
            } else {
                throw e;
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据执行 sql 语句的 类型自动判断，并返回回调值
     *
     * @param sql sql语句
     * @return: java.lang.Object
     */
    public static Object executeSql(String sql, Statement statement, ExecuteCallback executeCallBack) throws SQLException, InvocationTargetException, NoSuchMethodException, SqlAnnotationNotFoundException, InstantiationException, IllegalAccessException {
        if (sql.toUpperCase().startsWith(SqlConstant.INSERT) || sql.toUpperCase().startsWith(SqlConstant.UPDATE) || sql.toUpperCase().startsWith(SqlConstant.DELETE)) {
            int count = statement.executeUpdate(sql);
            return executeCallBack.execute(count);
        } else if (sql.toUpperCase().startsWith(SqlConstant.SELECT)) {
            ResultSet resultSet = statement.executeQuery(sql);
            return executeCallBack.executeQuery(resultSet);
        } else {
            boolean count = statement.execute(sql);
            return executeCallBack.execute(count ? 1 : 0);
        }
    }

    /**
     * 根据执行 sql 语句的 类型自动判断
     *
     * @param sql sql语句
     * @return: java.lang.Object
     */
    public static void executeSql(String sql, Statement statement) throws SQLException {
        if (sql.toUpperCase().startsWith(SqlConstant.INSERT) || sql.toUpperCase().startsWith(SqlConstant.UPDATE) || sql.toUpperCase().startsWith(SqlConstant.DELETE)) {
            statement.executeUpdate(sql);
        } else {
            statement.execute(sql);
        }
    }

    /**
     * 链接数据库
     *
     * @return: java.sql.Connection
     */
    public static Connection createConnection() throws SQLException {
        try {
            //启动JDBC驱动
            String driver = setting.get("driver");
            Class.forName(driver);
            //链接数据库
            return DriverManager.getConnection(
                    setting.get("url"),
                    setting.get("user"),
                    setting.get("pwd"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
