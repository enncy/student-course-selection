package cn.enncy.scs.factory;


import java.util.HashMap;
import java.util.Map;

/**
 * //TODO
 * <br/>Created in 20:54 2021/6/1
 *
 * @author: enncy
 */
public class BaseFactory<K,T>{

    public Map<Class<K>, T> baseServiceMap = new HashMap<>();

    public  T get(Class<K> targetType) {
        T target = baseServiceMap.get(targetType);
        if(target==null){
            try{
                target = (T) targetType.getConstructor(). newInstance();
                baseServiceMap.put(targetType, target);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return target;
    }

}
