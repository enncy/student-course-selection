package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.component.panel.ScsWhitePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 11:52 2021/4/24
 *
 * @author: enncy
 */
public class TitleBarLeftPanel extends ScsWhitePanel {
    public JLabel jLabel;
    public   static TitleChangeListener titleChangeListener = new TitleChangeListener() {
        @Override
        public void onChange(String title) {

        }
    };

    public TitleBarLeftPanel() {
        FlowLayout leftFlowLayout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(leftFlowLayout);

        // 设置标题栏
        jLabel = new JLabel("学生管理系统");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("icon/logo.png")).getPath());
        icon.setImage(icon.getImage().getScaledInstance(16, 16 ,Image.SCALE_AREA_AVERAGING ));
        jLabel.setFont(new Font("微软雅黑",0,12));
        jLabel.setIcon(icon);
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.add(jLabel);
    }

    public void setTitle(String title){
        jLabel.setText(title);
    }


}
