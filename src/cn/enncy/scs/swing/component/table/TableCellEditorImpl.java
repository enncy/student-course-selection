package cn.enncy.scs.swing.component.table;


import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 23:14 2021/4/26
 *
 * @author: enncy
 */
public abstract class TableCellEditorImpl extends AbstractCellEditor implements TableCellEditor {


    private JPanel jPanel;
    //格子面板区域
    private JPanel operationPanel;
    //选中的列
    private int selectedRow = -1;

    public TableCellEditorImpl(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public TableCellEditorImpl() {
        this.jPanel = createCellPanel();
    }

    public JPanel createCellPanel() {
        operationPanel = new ScsWhitePanel();
        operationPanel.setLayout(new FlowLayout());
        buildComponent(operationPanel);
        return operationPanel;
    }

    /**
     * 给子类提供组件创建的方法
     *
     * @param operationPanel
     * @return: void
     */
    public abstract void buildComponent(JPanel operationPanel);


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return jPanel;
    }

    @Override
    public Object getCellEditorValue() {

        return jPanel;
    }

    public JPanel getjPanel() {
        return jPanel;
    }


    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JPanel getOperationPanel() {
        return operationPanel;
    }

    public void setOperationPanel(JPanel operationPanel) {
        this.operationPanel = operationPanel;
    }

    public int getSelectedRow() {
        return selectedRow;
    }


}
