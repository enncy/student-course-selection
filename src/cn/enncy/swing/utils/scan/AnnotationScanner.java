package cn.enncy.swing.utils.scan;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * //TODO
 * <br/>Created in 14:19 2021/4/14
 *
 * @author: enncy
 */
public class AnnotationScanner {
    public static void init(String packageName){

//        FileScanner fileScanner = new FileScanner();
//        try {
//            Class[] classes = fileScanner.scan(packageName);
//            System.out.println(classes.length);
//            for (Class clazz : classes) {
//
//                if(getClassAnnotation(clazz,Service.class)!=null){
//                    Method[] methods = clazz.getMethods();
//                    for (Method method : methods) {
//                        SQL annotation =  getMethodAnnotation(method,SQL.class);
//                        if(annotation!=null){
//                            System.out.println(annotation);
//                        }
//                    }
//                }
//            }
//        } catch (URISyntaxException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 获取类上的注解
     * @param clazz 目标类
     * @param annotationClass   目标注解
     * @return: java.lang.annotation.Annotation
     */
    public static  <T  extends Annotation> Annotation getClassAnnotation(Class clazz, Class<T> annotationClass){
        if (clazz.isAnnotationPresent(annotationClass)) {
            return  clazz.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 获取类中方法的注解
     * @param method 目标类
     * @param annotationClass   目标注解
     * @return: java.lang.annotation.Annotation
     */
    public static  <T extends Annotation> T getMethodAnnotation(Method method, Class<T> annotationClass){
        if (method.isAnnotationPresent(annotationClass)) {
            return method.getAnnotation(annotationClass);
        }
        return null;
    }

}
