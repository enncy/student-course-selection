package cn.enncy.mybatis;


import cn.enncy.io.FileContentReader;
import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.proxy.SqlProxyInvocationHandler;
import cn.enncy.scanner.AnnotationScanner;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;


/**
 * //TODO
 * <br/>Created in 23:16 2021/4/14
 *
 * @author: enncy
 */
public class SqlSession {

    private static Logger logger =  Logger.getLogger(SqlSession.class);

    public static <T> T getMapper(Class target) {
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new SqlProxyInvocationHandler(target));

    }

    /**
     * 初始化 sql 建表文件, 先查询是否有表，如果报错则创建新表
     * @param packageName
     * @return: void
     */
    public static void initSql(String packageName){
        AnnotationScanner.readClass(packageName,clazz -> {
            if(clazz.isAnnotationPresent(Mapper.class)){
                Mapper mapper = (Mapper) clazz.getAnnotation(Mapper.class);
                String tableName = mapper.tableName();
                if("".equals(tableName)){
                    throw new Exception("Mapper.tableName is not can be empty");
                }
                // 先查询，如果报错说明未键表
                try {
                    DBUtils.execute("select * from " + tableName);
                }catch (SQLException e) {
                    if(e instanceof SQLSyntaxErrorException){
                        try {
                            //建表
                            String fileContent = FileContentReader.getFileContent(Objects.requireNonNull(SqlSession.class.getClassLoader().getResource("sql/"+tableName + ".sql")).toURI());
                            DBUtils.execute(fileContent);
                            logger.info("[mybatis] : 创建新表 "+tableName);
                        } catch (IOException | SQLException e1) {
                            e1.printStackTrace();
                        }
                    }else{
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
