package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.constant.color.ScsColor;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 20:02 2021/4/21
 *
 * @author: enncy
 */
public class ScsPanel extends JPanel {

    public ScsPanel() {
        this.setBackground(ScsColor.GHOST_WHITE);
    }

    public ScsPanel(LayoutManager layout) {
        super(layout);
        this.setBackground(ScsColor.GHOST_WHITE);
    }
}