package cn.enncy.scs.pojo.annotation;

import cn.enncy.scs.service.BaseService;

import java.lang.annotation.*;

/**
 * //TODO 外键关联属性
 * <br/>Created in 20:38 2021/5/27
 *
 * @author: enncy
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForeignInfo {
    String  fieldName();

    java.lang.Class<? extends BaseService> service();
}
