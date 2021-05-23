package cn.enncy.scs.swing.component.table;


import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 23:14 2021/4/26
 *
 * @author: enncy
 */
public class ScsTableCellEditor extends AbstractCellEditor implements TableCellEditor {


    private JPanel jPanel;
    private JButton updateButton;
    private JButton deleteButton;
    private int selectedRow = -1;

    public ScsTableCellEditor(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public ScsTableCellEditor(List data) {
        this.jPanel = createCellPanel(data);
    }

    public JPanel createCellPanel(List data) {
        JPanel operation = new ScsWhitePanel();
        operation.setLayout(new FlowLayout());

        updateButton = new JButton("修改");
        updateButton.setForeground(NiceColors.BLUE);
        updateButton.setFont(new Font("微软雅黑", 0, 14));

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("微软雅黑", 0, 14));
        deleteButton.setForeground(NiceColors.RED);


        operation.add(updateButton);
        operation.add(deleteButton);
        return operation;
    }

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

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
}
