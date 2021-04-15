package cn.enncy.swing.utils.database.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * SqlAnnotation 处理 sql 函数注解语句的对象
 * <br/>Created in 13:49 2021/4/15
 *
 * @author: enncy
 */
public class SqlAnnotation {
    private Class target;
    private Method method;
    private Annotation methodAnnotation;

    public SqlAnnotation(Class target, Method method, Annotation methodAnnotation) {
        this.target = target;
        this.method = method;
        this.methodAnnotation = methodAnnotation;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Annotation getMethodAnnotation() {
        return methodAnnotation;
    }

    public void setMethodAnnotation(Annotation methodAnnotation) {
        this.methodAnnotation = methodAnnotation;
    }
}
