package cn.enncy.scs.swing.component;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.BaseObject;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * //TODO   显示类信息
 * <br/>Created in 19:42 2021/5/30
 *
 * @author: enncy
 */
public class ScsList extends JList {

    public ScsList(String title,BaseObject baseObject) {

        Object[] objects = ReflectUtils.objectValueToArray(baseObject);
        Field[] declaredFields = ReflectUtils.getObjectFields(baseObject.getClass());
        String[] info = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            String str = declaredFields[i].getName() + ":" + objects[i];
            info[i] = str;
        }
        this.setListData(info);
        this.setEnabled(false);
        this.setBorder(BorderFactory.createTitledBorder(title));
    }
}
