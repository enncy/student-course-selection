package cn.enncy.scs.swing.component.table;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Info;
import cn.enncy.scs.swing.frame.MainFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManageDialog;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManagePanel;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManageType;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 20:43 2021/4/26
 *
 * @author: enncy
 */
public class ScsTableFactory {


    /**
     * 生成带有操作按钮的表格
     * @param data  数据列表
     * @param managePanel 目标容器
     * @return: javax.swing.JTable
     */
    public static JTable createJTableWithOperate(List data, ManagePanel managePanel) {

        Field[] targetDeclaredFields = ReflectUtils.getObjectFields(managePanel.getBaseObjectClass());


        int rowLength = data.size();
        int colLength = targetDeclaredFields.length;

        Object[][] row = new Object[rowLength ][colLength+1];
        Object[] col = new Object[colLength+1];
        int i;
        int j=0;

        //反射设置表格数据
        for (i = 0; i < rowLength; i++) {
            Object[] objects = ReflectUtils.objectValueToArray(data.get(i));
            List  list = new ArrayList(Arrays.asList(objects));
            //增加一个位置，预留给操作列表
            list.add("null");
            row[i] = list.toArray();
        }

        //反射设置表格头部
        for (i = 0; i < colLength; i++) {
            Field field = targetDeclaredFields[i];
            if (field.isAnnotationPresent(Info.class)) {
                Info annotation = field.getAnnotation(Info.class);
                col[i] = annotation.value();
            } else {
                col[i] = targetDeclaredFields[i].getName();
            }

        }
        //添加操作列
        col[i] = "操作";


        ScsTable jTable = new ScsTable(row, col);
        setTableCellRender(jTable, data, managePanel);

//        jTable .getTableHeader().setResizingAllowed(false);
        return jTable;
    }

    public static JTable setTableCellRender(JTable jTable, List data, ManagePanel managePanel){
        ScsTableCellEditor scsTableCellEditor = new ScsTableCellEditor(data);

        jTable.getColumn("操作").setCellEditor( scsTableCellEditor);
        jTable.getColumn("操作").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return scsTableCellEditor.createCellPanel(data);
            }
        });

        //初始化按钮事件
        JButton deleteButton = scsTableCellEditor.getDeleteButton();
        JButton updateButton = scsTableCellEditor.getUpdateButton();

        //修改按钮
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = scsTableCellEditor.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        new ManageDialog("修改信息",managePanel, (BaseObject) data.get(selectedRow), ManageType.UPDATE);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException noSuchMethodException) {
                        noSuchMethodException.printStackTrace();
                    }
                }
                super.mouseClicked(e);
            }
        });
        //删除按钮
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = scsTableCellEditor.getSelectedRow();
                int i = JOptionPane.showConfirmDialog(MainFrame.frame,"是否删除数据?删除后不可恢复！","警告",JOptionPane.YES_NO_OPTION);
                if(i==JOptionPane.YES_OPTION){
                    //删除数据
                    managePanel.getBaseService().deleteById(((BaseObject)data.get(selectedRow)).getId());
                    //更新表格面板
                    managePanel.updateDataList();
                    managePanel.updateTablePanel();
                }
                super.mouseClicked(e);
            }
        });

        return jTable;
    }


}
