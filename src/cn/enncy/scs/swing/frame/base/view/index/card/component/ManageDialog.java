package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.dialog.ScsAlert;
import cn.enncy.scs.swing.component.dialog.ScsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public ManageDialog(String title, ManagePanel managePanel, BaseObject defaultBaseObject, ManageType manageType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(title);
        Class baseObjectClass = managePanel.getBaseObjectClass();
        this.baseService = managePanel.getBaseService();
        //创建双向数据绑定表单
        ValueModelForm valueModelForm = new ValueModelForm(baseObjectClass, defaultBaseObject);

        //不可縮放
        this.setCanResize(false);
        ManageDialog manageDialog = this;

        //获取窗体组件的容器
        JPanel jPanel = manageDialog.getTitlePanel().getContainer();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //操作组件
        JPanel operate = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageDialog.dispose();
            }
        });


        JButton confirmButton = new JButton("确定");

        BaseObject target = valueModelForm.getTarget();
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (target != null) {
                    if (!valueModelForm.isAllValid()) {
                        ScsAlert.error("字段不能为空！");
                        return;
                    }
                    //如果是插入操作
                    if (manageType == ManageType.INSERT) {
                        int insert = baseService.insert(target);
                        if (insert == 0) {
                            System.err.println(target);
                            ScsAlert.error("添加数据失败！请检查是否有重复数据！");
                        } else {
                            //更新表格面板
                            managePanel.updateDataList();
                            managePanel.updateTablePanel();
                            manageDialog.dispose();
                        }
                    }
                    //更新操作
                    else if (manageType == ManageType.UPDATE) {
                        baseService.update(target);
                        //更新表格面板
                        managePanel.updateDataList();
                        managePanel.updateTablePanel();
                        manageDialog.dispose();
                    }
                } else {
                    ScsAlert.error("添加数据失败！数据对象为空！");
                }

            }
        });
        operate.add(cancelButton);
        operate.add(confirmButton);

        JPanel container = new JPanel(new BorderLayout());

        container.add(valueModelForm, BorderLayout.CENTER);
        container.add(operate, BorderLayout.SOUTH);

        jPanel.add(container);
        this.pack();
    }


}
