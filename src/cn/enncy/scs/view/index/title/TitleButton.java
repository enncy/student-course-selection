package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.constant.color.ScsColor;

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
        this.setBackground(ScsColor.WHITE);



        this.setFocusable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(ScsColor.GHOST_WHITE);
                e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(ScsColor.WHITE);
            }
        });


    }
}
