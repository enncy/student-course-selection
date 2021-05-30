package cn.enncy.scs.service;


import java.util.HashMap;
import java.util.Map;

/**
 * //TODO   业务工厂
 * <br/>Created in 21:10 2021/5/27
 *
 * @author: enncy
 */
public class ServiceFactory {

    public static  Map<Class<? extends BaseService>, BaseService> baseServiceMap = new HashMap<>();

    public static <T extends BaseService> T getService(Class<T> serviceType) {
        BaseService target = baseServiceMap.get(serviceType);
        if(target==null){
            try{
                target =  serviceType.getConstructor().newInstance();
                baseServiceMap.put(serviceType, target);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return (T) target;
    }

}
