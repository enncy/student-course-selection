package cn.enncy.scs.swing.frame.base.view.index.card.component.dialog;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.dialog.ScsDialog;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ValueModelForm;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * //TODO
 * <br/>Created in 23:33 2021/4/27
 *
 * @author: enncy
 */
public class ManageDialog extends ScsDialog {


    private BaseService baseService;
    private ValueModelForm valueModelForm;
    private ServiceComponent serviceComponent;
    private JButton confirmButton;
    private JButton cancelButton;

    public ManageDialog(String title, ServiceComponent serviceComponent, BaseObject defaultBaseObject) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(title);
        this.setVisible(false);
        Class baseObjectClass = serviceComponent.getBaseObjectClass();
        this.serviceComponent = serviceComponent;
        this.baseService = serviceComponent.getBaseService();
        //创建双向数据绑定表单
        valueModelForm = new ValueModelForm(baseObjectClass, defaultBaseObject);
        //不可縮放
        this.setCanResize(false);
        ManageDialog manageDialog = this;

        //获取窗体组件的容器
        JPanel jPanel = manageDialog.getContainer();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //操作组件
        JPanel operate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cancelButton = new JButton("取消");
        confirmButton = new JButton("确定");


        operate.add(cancelButton);
        operate.add(confirmButton);

        JPanel container = new JPanel(new BorderLayout());

        container.add(valueModelForm, BorderLayout.CENTER);
        container.add(operate, BorderLayout.SOUTH);

        jPanel.add(container);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public BaseService getBaseService() {
        return baseService;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public ValueModelForm getValueModelForm() {
        return valueModelForm;
    }
}
