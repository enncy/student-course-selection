package cn.enncy.swing.utils.database.annotation;

import cn.enncy.exception.TableAnnotationNotFoundException;

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
    Object annotationHandler(SqlAnnotation sqlAnnotation,Object[] args) throws TableAnnotationNotFoundException;

}
