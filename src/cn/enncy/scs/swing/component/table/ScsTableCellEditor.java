package cn.enncy.scs.swing.component.table;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.frame.MainFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;
import cn.enncy.scs.swing.frame.base.view.index.card.component.dialog.ManageUpdateDialog;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * //TODO  管理列表操作渲染类
 * <br/>Created in 23:14 2021/4/26
 *
 * @author: enncy
 */
public class ScsTableCellEditor extends TableCellEditorImpl{

    //更新按钮
    private JButton updateButton;
    //删除按钮
    private JButton deleteButton;
    //数据列表
    private List data;
    //业务组件
    private ServiceComponent serviceComponent;

    public ScsTableCellEditor(List data, ServiceComponent serviceComponent) {
        this.data = data;
        this.serviceComponent = serviceComponent;
    }

    @Override
    public void buildComponent(JPanel operationPanel) {
        updateButton = new JButton("修改");
        updateButton.setForeground(NiceColors.BLUE);
        updateButton.setFont(new Font("微软雅黑", 0, 14));

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("微软雅黑", 0, 14));
        deleteButton.setForeground(NiceColors.RED);

        ScsTableCellEditor scsTableCellEditor = this;
        //修改按钮
        updateButton.addActionListener(e->{
            int selectedRow = scsTableCellEditor.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    new ManageUpdateDialog("修改信息",serviceComponent, (BaseObject) data.get(selectedRow));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                }
            }
        });
        //删除按钮
        deleteButton.addActionListener(e->{
            int selectedRow = scsTableCellEditor.getSelectedRow();
            int i = JOptionPane.showConfirmDialog(MainFrame.frame,"是否删除数据?删除后不可恢复！","警告",JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION){
                //删除数据
                serviceComponent.getBaseService().deleteById(((BaseObject)data.get(selectedRow)).getId());
            }
        });

        operationPanel.add(updateButton);
        operationPanel.add(deleteButton);
    }


    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }
}
