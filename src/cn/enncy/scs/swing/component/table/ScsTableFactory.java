package cn.enncy.scs.swing.component.table;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.BaseObjectUtils;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

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
    public static JTable createJTableWithOperate(List data, ServiceComponent serviceComponent) {
        return createJTableWithOperate(data, serviceComponent, new ScsTableCellEditor(data, serviceComponent));
    }


    /**
     * 生成带有操作按钮的表格
     *
     * @param data        数据列表
     * @param serviceComponent 目标容器
     * @return: javax.swing.JTable
     */
    private static JTable createJTableWithOperate(List data,  ServiceComponent serviceComponent, TableCellEditorImpl tableCellEditor) {

        Field[] targetDeclaredFields = ReflectUtils.getObjectFields(serviceComponent.getBaseObjectClass());

        Object[][] row = new Object[data.size()][];
        Object[] col = pushValueToArray(BaseObjectUtils.getFieldName(targetDeclaredFields), "操作");
        //反射设置表格数据
        for (int i = 0; i < data.size(); i++) {
            //渲染数据
            Object[] objects = ReflectUtils.objectValueToArray(data.get(i),new TimeFormatDataRender());

            row[i] = pushValueToArray(objects, "null");
        }

        ScsTable jTable = new ScsTable(row, col);
        //给表单添加操作列
        jTable.setTableCellEditor(tableCellEditor);

        return jTable;
    }


    /**
     * 创建一个新的数组，并在末尾添加指定的值
     *
     * @param arr   数组
     * @param value 值
     * @return: java.lang.Object[]
     */
    public static Object[] pushValueToArray(Object[] arr, Object value) {
        List<Object> objects = new ArrayList<>(Arrays.asList(arr));
        objects.add(value);
        return objects.toArray(new Object[0]);
    }


}
