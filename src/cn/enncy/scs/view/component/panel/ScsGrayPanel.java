package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.constant.color.NiceColors;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:16 2021/4/25
 *
 * @author: enncy
 */
public class ScsGrayPanel extends JPanel {
    public ScsGrayPanel() {
        this.setBackground(NiceColors.GHOST_WHITE);
    }

    public ScsGrayPanel(LayoutManager layout) {
        super(layout);

        this.setBackground(NiceColors.GHOST_WHITE);
    }

}
