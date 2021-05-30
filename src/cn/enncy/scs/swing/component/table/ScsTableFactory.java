package cn.enncy.scs.swing.component.table;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.Info;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManagePanel;

import javax.swing.*;
import java.lang.reflect.Field;
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

    //创建管理列表
    public static JTable createJTableWithOperate(List data, ManagePanel managePanel){
        return createJTableWithOperate(data, managePanel, new ScsTableCellEditor(data, managePanel));
    }


    /**
     * 生成带有操作按钮的表格
     * @param data  数据列表
     * @param managePanel 目标容器
     * @return: javax.swing.JTable
     */
    private static JTable createJTableWithOperate(List data, ManagePanel managePanel,TableCellEditorImpl tableCellEditor) {

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
        //给表单添加操作列
        jTable.setTableCellEditor(tableCellEditor);


        return jTable;
    }




}
