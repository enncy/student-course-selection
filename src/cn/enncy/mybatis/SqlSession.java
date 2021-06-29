package cn.enncy.mybatis;


import cn.enncy.mybatis.proxy.SqlProxyInvocationHandler;

import java.lang.reflect.Proxy;


/**
 * //TODO
 * <br/>Created in 23:16 2021/4/14
 *
 * @author: enncy
 */
public class SqlSession {
    public static <T> T getMapper(Class target) {
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new SqlProxyInvocationHandler(target));
    }

}
