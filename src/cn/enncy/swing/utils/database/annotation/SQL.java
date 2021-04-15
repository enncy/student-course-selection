package cn.enncy.swing.utils.database.annotation;

import java.lang.annotation.*;

/**
 * 执行 sql 语句
 * <br/>Created in 14:41 2021/4/15
 *
 * @author: enncy
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQL {
    String value();
    Class resultType() default Object.class;
}
