package cn.enncy.scanner;


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
    /**
     * 获取指定包类下的 class
     * @param packageName
     * @param classReade
     * @return: void
     */
    public static void  readClass(String packageName,ClassReader classReader){

        FileScanner fileScanner = new FileScanner();
        try {
            Class[] classes = fileScanner.scan(packageName);
            for (Class clazz : classes) {
                classReader.read(clazz);
            }
        } catch (URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
