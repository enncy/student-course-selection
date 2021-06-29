package cn.enncy.scs.swing.component.table;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO   选课结果表单渲染器
 * <br/>Created in 20:50 2021/6/2
 *
 * @author: enncy
 */
public class ScsSelectionResultTableCellEditor extends TableCellEditorImpl {
    //查看课程信息按钮
    private JButton showInfoButton;

    //撤销选课按钮
    private JButton cancelSelectionButton;



    public JButton getCancelSelectionButton() {
        return cancelSelectionButton;
    }
    public JButton getShowInfoButton() {
        return showInfoButton;
    }


    @Override
    public void buildComponent(JPanel operationPanel) {

        showInfoButton = new JButton("详情");
        showInfoButton.setForeground(NiceColors.BLACK);
        showInfoButton.setFont(new Font("微软雅黑", 0, 14));

        cancelSelectionButton = new JButton("撤销选课");
        cancelSelectionButton.setFont(new Font("微软雅黑", 0, 14));
        cancelSelectionButton.setForeground(NiceColors.RED);

        operationPanel.add(showInfoButton);
        operationPanel.add(cancelSelectionButton);
    }

}
