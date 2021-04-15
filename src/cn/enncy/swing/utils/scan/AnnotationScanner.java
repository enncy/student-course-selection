package cn.enncy.swing.utils.scan;


import cn.enncy.swing.exception.SqlAnnotationNotFoundException;
import cn.enncy.swing.utils.database.DBUtils;
import cn.enncy.swing.utils.database.ExecuteCallback;
import cn.enncy.swing.utils.database.annotation.Table;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //TODO
 * <br/>Created in 14:19 2021/4/14
 *
 * @author: enncy
 */
public class AnnotationScanner {
    public static void  init(String packageName){

        FileScanner fileScanner = new FileScanner();
        try {
            Class[] classes = fileScanner.scan(packageName);
            for (Class clazz : classes) {
                createTable(clazz);
            }
        } catch (URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 自动建表
     * @return: void
     */
    public static void createTable(Class clazz) throws IOException {
        Annotation annotation =   getClassAnnotation(clazz, Table.class);
        if(annotation!=null){
            Table table = (Table) annotation;
            File file = new File(AnnotationScanner.class.getClassLoader().getResource(table.value()+".sql").getPath());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                result.append(line).append("\n");
            }
            System.out.println(result.toString());
            DBUtils.execute(result.toString(), new ExecuteCallback() {
                @Override
                public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
                    return null;
                }

                @Override
                public int execute(int count) {
                    return 0;
                }
            });

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
