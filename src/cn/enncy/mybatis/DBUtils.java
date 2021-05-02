package cn.enncy.mybatis;


import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.scs.exception.SqlAnnotationNotFoundException;
import cn.enncy.scs.utils.PropertiesUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
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
    private static PropertiesUtils setting;

    static {
        try {
            setting = new PropertiesUtils("setting.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("[数据库参数]:url:"+setting.get("url")+" , user:"+setting.get("user")+" , pwd:"+setting.get("pwd"));
    }

    public  static Object execute(String sql) throws SQLException {
        return execute(sql, new ExecuteCallback() {
            @Override
            public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
                return resultSet;
            }

            @Override
            public int execute(int count) {
                return count;
            }
        });
    }

    public static Object execute(String sql, ExecuteCallback executeCallBack) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            //启动JDBC驱动
            String driver = setting.get("driver");

            Class.forName(driver);
            //链接数据库
            connection = DriverManager.getConnection(
                    setting.get("url"),
                    setting.get("user"),
                    setting.get("pwd"));
            //查询数据
            statement = connection.createStatement();
            //执行sql

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
            //下面是异常处理
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | SqlAnnotationNotFoundException | ClassNotFoundException e) {
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
