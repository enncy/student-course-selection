package cn.enncy.scs.swing.component.table;


import cn.enncy.reflect.ReflectDataRender;
import cn.enncy.scs.pojo.BaseObjectUtils;
import cn.enncy.scs.pojo.annotation.TimeFormat;

import java.lang.reflect.Field;

/**
 * //TODO   时间渲染器
 * <br/>Created in 14:19 2021/6/1
 *
 * @author: enncy
 */
public class TimeFormatDataRender implements ReflectDataRender {
    @Override
    public Object render(Field field, Object data) {
        if(field.isAnnotationPresent(TimeFormat.class)){
            return BaseObjectUtils.getFormatTime((Long) data);
        }else{
            return data;
        }
    }
}
