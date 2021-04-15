package cn.enncy.swing.utils.database.annotation;


import java.lang.annotation.*;

/**
 * //TODO
 * <br/>Created in 13:11 2021/4/15
 *
 * @author: enncy
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {
    String value();
}
