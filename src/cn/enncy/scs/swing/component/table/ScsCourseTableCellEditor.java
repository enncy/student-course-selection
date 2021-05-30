package cn.enncy.scs.swing.component.table;


import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * //TODO       选课列表操作渲染类
 * <br/>Created in 20:02 2021/5/27
 *
 * @author: enncy
 */
public class ScsCourseTableCellEditor extends TableCellEditorImpl {

    //查看课程信息按钮
    private JButton showInfoButton;
    //选课按钮
    private JButton courseSelectionButton;
    //自动选课按钮
    private JButton autoSelectionButton;
    //数据列表
    private  List data;
    //业务组件
    private ServiceComponent serviceComponent;

    public ScsCourseTableCellEditor(List data, ServiceComponent serviceComponent) {
        this.data = data;
        this.serviceComponent = serviceComponent;
    }

    @Override
    public void buildComponent(JPanel operationPanel) {

        showInfoButton = new JButton("详情");
        showInfoButton.setForeground(NiceColors.BLACK);
        showInfoButton.setFont(new Font("微软雅黑", 0, 14));


        courseSelectionButton = new JButton("选课");
        courseSelectionButton.setForeground(NiceColors.BLUE);
        courseSelectionButton.setFont(new Font("微软雅黑", 0, 14));

        autoSelectionButton = new JButton("自动选课");
        autoSelectionButton.setFont(new Font("微软雅黑", 0, 14));
        autoSelectionButton.setForeground(NiceColors.ORANGE);

        operationPanel.add(showInfoButton);
        operationPanel.add(courseSelectionButton);
        operationPanel.add(autoSelectionButton);
    }

    public JButton getCourseSelectionButton() {
        return courseSelectionButton;
    }

    public JButton getAutoSelectionButton() {
        return autoSelectionButton;
    }

    public JButton getShowInfoButton() {
        return showInfoButton;
    }

}
