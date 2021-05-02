package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.constant.NiceColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 14:07 2021/4/25
 *
 * @author: enncy
 */
public class StatisticsCard extends AdaptivePanel{

    public String imageUrl = "";
    public String text = "";


    public StatisticsCard(float percent, String statisticName) {
        super(percent);
        this.setSize(new Dimension(80,100));
        this.reSize(this,percent);
        this.setBorder(new EmptyBorder(20,20,20,20));
        this.setLayout(new BorderLayout());

        //统计描述
        JLabel jLabel = new JLabel(statisticName,JLabel.LEFT);
        jLabel.setFont(new Font("微软雅黑",0,14));
        jLabel.setForeground(NiceColors.DARK_GRAY);
        this.add(jLabel,BorderLayout.NORTH);

        this.setBackground(NiceColors.WHITE);
        this.repaint();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }


    public void setImageLabel(String imageUrl,String text) {
        this.imageUrl = imageUrl;
        this.text = text;
        //图标和数据
        JLabel iconLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(imageUrl)).getPath());
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(32, 32,Image.SCALE_AREA_AVERAGING ));
        iconLabel.setIcon(imageIcon);
        iconLabel.setBorder(new EmptyBorder(10,0,0,0));
        iconLabel.setText(text);
        iconLabel.setFont(new Font("微软雅黑",0,24));
        this.add(iconLabel,BorderLayout.CENTER);
    }
}
