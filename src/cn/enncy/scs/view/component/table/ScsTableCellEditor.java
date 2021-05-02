package cn.enncy.scs.view.component.table;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.view.component.panel.ScsWhitePanel;
import cn.enncy.scs.view.constant.NiceColors;
import cn.enncy.scs.view.frame.MainFrame;
import cn.enncy.scs.view.index.card.component.ManageDialog;
import cn.enncy.scs.view.index.card.component.ManagePanel;
import cn.enncy.scs.view.index.card.component.ManageType;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    public ScsTableCellEditor(List data, ManagePanel managePanel) {
        this.jPanel = createCellPanel(data, managePanel);
    }

    public JPanel createCellPanel(List data,ManagePanel managePanel) {
        JPanel operation = new ScsWhitePanel();
        operation.setLayout(new FlowLayout());

        updateButton = new JButton("修改");
        updateButton.setForeground(NiceColors.BLUE);
        updateButton.setFont(new Font("微软雅黑", 0, 14));

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("微软雅黑", 0, 14));
        deleteButton.setForeground(NiceColors.RED);


        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedRow != -1) {
                    new ManageDialog("添加信息",managePanel, (BaseObject) data.get(selectedRow), ManageType.UPDATE);
                }

                super.mouseClicked(e);
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(MainFrame.frame,"是否删除数据?删除后不可恢复！","警告",JOptionPane.YES_NO_OPTION);
                if(i==JOptionPane.YES_OPTION){
                    managePanel.getBaseService().deleteById(((BaseObject)data.get(selectedRow)).getId());
                    managePanel.initTablePanel();
                }
                super.mouseClicked(e);
            }
        });

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
}
