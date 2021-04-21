package cn.enncy.scs.view.index;


import cn.enncy.scs.view.constant.color.SCSColor;
import cn.enncy.scs.view.index.title.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class MainPanel extends JPanel {

    public MainPanel(TitleBarPanel titleBarPanel) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(255,255,255));
        this.add(titleBarPanel,BorderLayout.NORTH);

        //主容器
        JPanel panel = new JPanel();
        panel.setBackground(SCSColor.GHOST_WHITE);
        this.add(panel);
    }

    public MainPanel() {
        this(new TitleBarPanel());
    }
}
