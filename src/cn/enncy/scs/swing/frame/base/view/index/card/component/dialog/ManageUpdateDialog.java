package cn.enncy.scs.swing.frame.base.view.index.card.component.dialog;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.swing.component.dialog.ScsAlert;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

import java.lang.reflect.InvocationTargetException;

/**
 * //TODO
 * <br/>Created in 22:41 2021/5/29
 *
 * @author: enncy
 */
public class ManageUpdateDialog extends ManageDialog {


    public ManageUpdateDialog(String title, ServiceComponent serviceComponent, BaseObject defaultBaseObject) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(title, serviceComponent, defaultBaseObject);
        BaseObject target = getValueModelForm().getTarget();

        getCancelButton().addActionListener(e -> this.dispose());

        getConfirmButton().addActionListener(e -> {
            if (target != null) {
                if (!getValueModelForm().isAllValid()) {
                    ScsAlert.error("字段不能为空！");
                    return;
                }
                getBaseService().update(target);
                this.dispose();
            } else {
                ScsAlert.error("添加数据失败！数据对象为空！");
            }
        });


    }
}
