package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.constant.NiceColors;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 11:28 2021/4/24
 *
 * @author: enncy
 */
public class ScsWhitePanel extends JPanel {

    public ScsWhitePanel() {
        this.setBackground(NiceColors.WHITE);
    }

    public ScsWhitePanel(LayoutManager layout) {
        super(layout);
        this.setBackground(NiceColors.WHITE);
    }
}
