package cn.enncy.scs.view.component.dialog;


import cn.enncy.scs.view.component.panel.DropShadowPanel;
import cn.enncy.scs.view.frame.BaseFrame;
import cn.enncy.scs.view.component.title.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 17:15 2021/4/24
 *
 * @author: enncy
 */
public class ScsDialog extends BaseFrame {

    public ScsDialog() {
        super(400,300,300,200);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/logo.png")).getPath());
        // 设置标题栏的图标为face.gif
        this.setIconImage(imageIcon.getImage());
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        DropShadowPanel dropShadowPanel = new DropShadowPanel(8);
        TitlePanel titlePanel = new TitlePanel(this);

        dropShadowPanel.add(titlePanel);
        this.add(dropShadowPanel);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setVisible(true);


    }
}
