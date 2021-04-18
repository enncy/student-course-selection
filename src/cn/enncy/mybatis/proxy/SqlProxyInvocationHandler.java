package cn.enncy.mybatis.proxy;


import cn.enncy.mybatis.DBUtils;
import cn.enncy.mybatis.ExecuteCallback;
import cn.enncy.mybatis.ResultSetHandler;
import cn.enncy.mybatis.SqlStringHandler;
import cn.enncy.mybatis.annotation.*;
import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.scs.exception.SqlAnnotationNotFoundException;
import cn.enncy.scs.exception.TableAnnotationNotFoundException;
import cn.enncy.scs.pojo.BaseObject;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * //TODO
 * <br/>Created in 22:38 2021/4/14
 *
 * @author: enncy
 */
public class SqlProxyInvocationHandler implements InvocationHandler, AnnotationHandler {

    public Class target;
    private static final Logger logger = getLogger(SqlProxyInvocationHandler.class);

    public SqlProxyInvocationHandler(Class target) {
        this.target = target;

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //获取类上的 Table 注解
        if (target.isAnnotationPresent(Mapper.class)) {
            //获取方法注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof SQL) {
                    SqlAnnotation sqlAnnotation = new SqlAnnotation(target, method, annotation);
                    return annotationHandler(sqlAnnotation, args);
                }
            }
        } else {
            throw new TableAnnotationNotFoundException("Table annotation is not found");
        }

        return null;
    }

    @Override
    public Object annotationHandler(SqlAnnotation sqlAnnotation, Object[] args)   {
        Mapper mapper = (Mapper) target.getAnnotation(Mapper.class);
        SQL sql = (SQL) sqlAnnotation.getMethodAnnotation();
        //处理 sql 语句
        String sqlString = handelSqlString(sql.value(), sqlAnnotation.getMethod().getParameters(), args);


        // 执行 sql 语句 并返回值
        Object result = null;
        try {
            // 处理表名
            sqlString = SqlStringHandler.replaceTableName(sqlString, mapper.tableName());

            result = DBUtils.execute(sqlString, new ExecuteCallback() {
                @Override
                public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
                    // 如果 返回值为列表 List 类型
                    if (sqlAnnotation.getMethod().getReturnType().isInstance(List.class)) {
                        List<Object> list = new ArrayList<>();
                        while (resultSet.next()) {
                            Object resultTarget = ResultSetHandler.createResultTarget(resultSet, sql.resultType());
                            list.add(resultTarget);
                        }
                        resultSet.close();
                        return list;
                    } else {
                        Object resultTarget = null;
                        if(resultSet.next()){
                            resultTarget = ResultSetHandler.createResultTarget(resultSet, sqlAnnotation.getMethod().getReturnType());
                        }
                        resultSet.close();
                        return resultTarget;
                    }
                }
                @Override
                public int execute(int count) {
                    return count;
                }
            });
        } catch (SQLException e) {
            logger.error("[SQL] : " + sqlString);
            e.printStackTrace();
        }
        logger.info("[SQL] : " + sqlString + "[RESULT]:"+result);
        return result;

    }

    /**
     * 处理 sql 语句
     *
     * @param sqlString  sql语句
     * @param parameters 代理对象反射获得的参数
     * @param args       代理对象的参数
     * @return: java.lang.String
     */
    private String handelSqlString(String sqlString, Parameter[] parameters, Object[] args) {
        String result = "";
        for (int i = 0; i < parameters.length; i++) {
            // 通过 Body注解  处理
            if (parameters[i].isAnnotationPresent(Body.class)) {
                //如果是插入操作，则解析参数
                if(sqlString.toUpperCase().startsWith(SqlConstant.INSERT)){
                    result = SqlStringHandler.replaceInsertFields(sqlString,SqlStringHandler.getObjectsValueMap(args[i],new BaseObject()));
                }else{
                    result = SqlStringHandler.replaceParams(sqlString, SqlStringHandler.getObjectsValueMap(args[i],new BaseObject()));
                }

            }
            // 通过 Param注解  处理
            else if (parameters[i].isAnnotationPresent(Param.class)) {
                Param param = parameters[i].getAnnotation(Param.class);
                result = SqlStringHandler.replaceParam(sqlString, param.value(), String.valueOf(args[i]));
            }
        }

        return result;
    }

}

