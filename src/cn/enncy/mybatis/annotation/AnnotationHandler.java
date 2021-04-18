package cn.enncy.mybatis.annotation;

import cn.enncy.scs.exception.TableAnnotationNotFoundException;

import java.sql.SQLException;

/**
 * //TODO
 * <br/>Created in 13:32 2021/4/15
 *
 * @author: enncy
 */
public interface AnnotationHandler {

    /**
     * 处理sql函数注解
     * @param sqlAnnotation SqlAnnotation
     * @param args 函数参数
     * @return void
     */
    Object annotationHandler(SqlAnnotation sqlAnnotation,Object[] args) throws TableAnnotationNotFoundException, SQLException;

}
