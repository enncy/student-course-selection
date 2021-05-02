package cn.enncy.scs.view.component.table;


import cn.enncy.mybatis.ResultSetHandler;
import cn.enncy.scs.pojo.Info;
import cn.enncy.scs.view.index.card.component.ManagePanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.lang.reflect.Field;
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

        Field[] targetDeclaredFields = ResultSetHandler.getBaseObjectFields(managePanel.getClazz());


        int rowLength = data.size();
        int colLength = targetDeclaredFields.length;

        Object[][] row = new Object[rowLength ][colLength+1];
        Object[] col = new Object[colLength+1];
        int i;
        int j=0;
        //反射设置表格数据
        for (i = 0; i < rowLength; i++) {

            Field[] declaredFields = getBaseObjectFields(data.get(i));
            for ( j = 0; j < colLength; j++) {
                Field field = declaredFields[j];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    row[i][j] = field.get(data.get(i));
                } catch (IllegalAccessException e) {
                    row[i][j] = "无";
                    e.printStackTrace();
                }
            }

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
        ScsTableCellEditor scsTableCellEditor = new ScsTableCellEditor(data,managePanel);
        jTable.getColumn("操作").setCellEditor( scsTableCellEditor);

        jTable.getColumn("操作").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return scsTableCellEditor.createCellPanel(data,managePanel);
            }
        });
        return jTable;
    }

    private static Field[] getBaseObjectFields(Object object){
        return ResultSetHandler.getBaseObjectFields(object.getClass());
    }


}
