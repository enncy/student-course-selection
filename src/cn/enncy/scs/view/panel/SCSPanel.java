package cn.enncy.scs.view.panel;


import cn.enncy.scs.view.constant.color.SCSColor;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 20:02 2021/4/21
 *
 * @author: enncy
 */
public class SCSPanel extends JPanel {

    public SCSPanel() {
        this.setBackground(SCSColor.WHITE);
    }

    public SCSPanel(LayoutManager layout) {
        super(layout);
        this.setBackground(SCSColor.WHITE);
    }
}
