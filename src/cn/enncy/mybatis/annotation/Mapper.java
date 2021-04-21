package cn.enncy.mybatis.annotation;

import cn.enncy.scs.pojo.BaseObject;

import java.lang.annotation.*;

/**
 * //TODO
 * <br/>Created in 22:11 2021/4/15
 *
 * @author: enncy
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapper {
    String tableName();
    Class bean() default BaseObject.class;
}
