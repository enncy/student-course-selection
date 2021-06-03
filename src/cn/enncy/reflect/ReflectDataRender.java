package cn.enncy.reflect;

import java.lang.reflect.Field;

/**
 * //TODO
 * <br/>Created in 14:08 2021/6/1
 *
 * @author: enncy
 */
public interface ReflectDataRender {
    Object render(Field field,Object data);
}
