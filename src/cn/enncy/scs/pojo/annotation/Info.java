package cn.enncy.scs.pojo.annotation;


import java.lang.annotation.*;

/**
 * //TODO   字段描述
 * <br/>Created in 21:31 2021/4/26
 *
 * @author: enncy
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Info {

    String value();
    boolean notNull() default true;

}
