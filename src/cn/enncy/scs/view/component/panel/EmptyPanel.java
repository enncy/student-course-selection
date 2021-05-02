package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.constant.NiceColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 20:59 2021/4/28
 *
 * @author: enncy
 */
public class EmptyPanel extends ScsWhitePanel{


    public EmptyPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(50,0,50,0));

        JLabel jLabel = new JLabel("",JLabel.CENTER);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(CardPanel.class.getClassLoader().getResource("icon/empty.png")).getPath());
        icon.setImage(icon.getImage().getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING));
        jLabel.setIcon(icon);

        JLabel description = new JLabel("暂无数据",JLabel.CENTER);
        description.setFont(new Font("微软雅黑",0,16));
        description.setForeground(NiceColors.DARK_GRAY);

        this.add(jLabel,BorderLayout.NORTH);
        this.add(description,BorderLayout.SOUTH);
    }
}
