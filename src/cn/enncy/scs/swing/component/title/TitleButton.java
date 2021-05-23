package cn.enncy.scs.swing.component.title;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 19:27 2021/4/21
 *
 * @author: enncy
 */
public class TitleButton extends JPanel {

    public TitleButton(String text ) {
        // 操作按钮
        JLabel jLabel = new JLabel(text);
        jLabel.setFont(new Font("宋体",Font.BOLD,16));
        this.add(jLabel);
        this.setBackground(NiceColors.WHITE);
        //取消默认选中
        this.setFocusable(false);
        //添加鼠标移入移出事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Component component = e.getComponent();
                //设置背景颜色
                component.setBackground(NiceColors.GHOST_WHITE);
                //设置鼠标样式：点击样式
                component.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(NiceColors.WHITE);
            }
        });
    }
}
