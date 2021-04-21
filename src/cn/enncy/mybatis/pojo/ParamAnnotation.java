package cn.enncy.mybatis.pojo;


import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 *  ParamAnnotation 处理 sql 函数形参注解的对象
 * <br/>Created in 13:51 2021/4/15
 *
 * @author: enncy
 */
public class ParamAnnotation extends SqlAnnotation {
    private Parameter parameter;
    private Annotation parameterAnnotation;

    public ParamAnnotation(SqlAnnotation sqlAnnotation, Parameter parameter, Annotation parameterAnnotation) {
        super(sqlAnnotation.getTarget(),sqlAnnotation.getMethod(), sqlAnnotation.getMethodAnnotation());
        this.parameter = parameter;
        this.parameterAnnotation = parameterAnnotation;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Annotation getParameterAnnotation() {
        return parameterAnnotation;
    }

    public void setParameterAnnotation(Annotation parameterAnnotation) {
        this.parameterAnnotation = parameterAnnotation;
    }
}
