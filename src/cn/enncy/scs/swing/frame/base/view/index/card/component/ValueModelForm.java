package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.pojo.BaseObject;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * //    数据绑定表单
 * <br/>Created in 19:56 2021/5/23
 *
 * @author: enncy
 */
public class ValueModelForm extends JPanel {

    private Class baseObjectClass;
    private BaseObject target;
    private List<ScsValueModel> scsValueModels = new ArrayList<>();

    /**
     * 创建数据绑定表单
     *
     * @param defaultBaseObject 默认数据
     * @return: JPanel  表单对象
     */
    public ValueModelForm(Class baseObjectClass,BaseObject defaultBaseObject) {
        // 数据绑定
        Field[] declaredFields = baseObjectClass.getDeclaredFields();
        GridLayout gridLayout = new GridLayout(declaredFields.length, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        try {
            //new对象，如果已经有默认对象，则不用new
            target = defaultBaseObject == null ? (BaseObject) baseObjectClass.getConstructor().newInstance() : defaultBaseObject;
            for (Field declaredField : declaredFields) {
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                //双向数据绑定
                ScsValueModel scsValueModel = new ScsValueModel(target, declaredField);
                scsValueModels.add(scsValueModel);
                //生成表单
                this.add(scsValueModel.getjLabel());
                this.add(scsValueModel.getjTextField());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    //检验表单正确性
    public boolean isAllValid(){

        if(scsValueModels.size()==0){
            return false;
        }else{
            boolean valid = true;
            for (ScsValueModel scsValueModel : scsValueModels) {
                if(!scsValueModel.isValid()){
                    valid =false;
                }
            }
            return valid;
        }
    }

    public Class getBaseObjectClass() {
        return baseObjectClass;
    }

    public void setBaseObjectClass(Class baseObjectClass) {
        this.baseObjectClass = baseObjectClass;
    }

    public BaseObject getTarget() {
        return target;
    }

    public void setTarget(BaseObject target) {
        this.target = target;
    }
}
