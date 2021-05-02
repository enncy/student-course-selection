package cn.enncy.mybatis.proxy;


import cn.enncy.mybatis.DBUtils;
import cn.enncy.mybatis.ExecuteCallback;
import cn.enncy.mybatis.ResultSetHandler;
import cn.enncy.mybatis.SqlStringHandler;
import cn.enncy.mybatis.annotation.Body;
import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.mybatis.pojo.SqlAnnotation;
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
public class SqlProxyInvocationHandler implements InvocationHandler {

    public Class target;
    private static final Logger logger = getLogger(SqlProxyInvocationHandler.class);

    public SqlProxyInvocationHandler(Class target) {
        this.target = target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invokeMethod(target, method, args, 0);
    }

    /**
     * 处理反射
     *
     * @param target    目标对象
     * @param method    反射代理的订单
     * @param args      方法的参数
     * @param loopIndex 递归深度，超出10则抛出 SqlAnnotationNotFoundException 异常
     * @return: java.lang.Object
     */
    public Object invokeMethod(Class target, Method method, Object[] args, int loopIndex) throws TableAnnotationNotFoundException, SqlAnnotationNotFoundException {
        if (loopIndex > 10) {
            throw new SqlAnnotationNotFoundException("SQL annotation is not found  , please make sure that current class or super class has SQL annotation");
        }
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
            //如果在此方法上获取不到 SQL 语句，则遍历获取父类的方法，向上查找 SQL 语句
            for (Class clazz : target.getInterfaces()) {
                Method[] methods = clazz.getMethods();
                for (Method superMethod : methods) {
                    if (superMethod.getName().equals(method.getName())) {
                        return invokeMethod(target, superMethod, args, loopIndex + 1);
                    }
                }
            }
        } else {
            throw new TableAnnotationNotFoundException("Mapper annotation is not found");
        }
        return null;
    }


    /**
     * 注解处理
     *
     * @param sqlAnnotation sql方法的注解
     * @param args          方法参数
     * @return: java.lang.Object
     */
    public Object annotationHandler(SqlAnnotation sqlAnnotation, Object[] args) throws SqlAnnotationNotFoundException {
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

                    // 目标转换类型根据 resultType 字段 sql 注解的优先级最高，其次 到mapper 的注解
                    Class<?> targetType = sql.resultType();
                    if(sql.resultType()==BaseObject.class){
                        //如果 mapper 不是默认值，则 targetType 为 mapper 的 resultType
                        if(mapper.resultType()!=BaseObject.class){
                            targetType = mapper.resultType();
                        }
                    }
                    // 如果 返回值为列表 List 类型
                    Class<?> returnType = sqlAnnotation.getMethod().getReturnType();
                    if (returnType.isInstance(List.class) || returnType.equals(List.class)) {

                        List<Object> list = new ArrayList<>();
                        while (resultSet.next()) {
                            Object resultTarget = ResultSetHandler.createResultTarget(resultSet, targetType);
                            list.add(resultTarget);
                        }
                        resultSet.close();
                        return list;
                    } else {
                        Object resultTarget = null;
                        if (resultSet.next()) {
                            resultTarget = ResultSetHandler.createResultTarget(resultSet,targetType);
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
        logger.info("[SQL] : " + sqlString + "[RESULT]:" + result);
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
    private String handelSqlString(String sqlString, Parameter[] parameters, Object[] args) throws SqlAnnotationNotFoundException {
        String result = sqlString;
        for (int i = 0; i < parameters.length; i++) {
            // 通过 Body注解  处理
            if (parameters[i].isAnnotationPresent(Body.class)) {
                //如果是插入操作，则解析参数
                if (sqlString.toUpperCase().startsWith(SqlConstant.INSERT)) {
                    result = SqlStringHandler.replaceInsertFields(sqlString, SqlStringHandler.getObjectsValueMap((BaseObject) args[i]));
                } else {
                    if (sqlString.toUpperCase().startsWith(SqlConstant.UPDATE)) {
                        result = SqlStringHandler.replaceUpdateFields(sqlString, SqlStringHandler.getObjectsValueMap((BaseObject) args[i]));
                        result = SqlStringHandler.replaceParams(result, SqlStringHandler.getObjectsValueMap((BaseObject) args[i]));
                    }else{
                        result = SqlStringHandler.replaceParams(sqlString, SqlStringHandler.getObjectsValueMap((BaseObject) args[i]));
                    }

                }

            }
            // 通过 Param注解  处理
            else if (parameters[i].isAnnotationPresent(Param.class)) {
                Param param = parameters[i].getAnnotation(Param.class);
                result = SqlStringHandler.replaceParam(sqlString, param.value(), String.valueOf(args[i]));
            }
            //如果参数都没有以上的注解，则抛出异常
            else {
                throw new SqlAnnotationNotFoundException("The method's parameters  is not match  those annotation:(Body|Param)");
            }
        }

        return result;
    }

}

