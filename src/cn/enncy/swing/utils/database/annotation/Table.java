package cn.enncy.swing.utils.database.annotation;

import java.lang.annotation.*;

/**
 * 绑定数据库表名
 * <br/>Created in 14:44 2021/4/15
 *
 * @author: enncy
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String value();
}
