package cn.enncy.scs.swing.component.table;


import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.Enumeration;

/**
 * //TODO
 * <br/>Created in 22:56 2021/4/26
 *
 * @author: enncy
 */
public class ScsTable extends JTable {
    private Object[][] rowData;
    private  Object[] columnNames;
    public ScsTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        this.rowData = rowData;
        this.columnNames = columnNames;
        Enumeration<TableColumn> columns = this.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn tableColumn = columns.nextElement();
            tableColumn.setMinWidth(150);

        }
        this.setRowHeight(44);
    }

    @Override
    public boolean isCellSelected(int row, int column) {
        if("操作".equals(columnNames[column])){
            return true;
        }
        return false;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if("操作".equals(columnNames[column])){
            return true;
        }
        return false;
    }
}
