package cn.enncy.scs.view.index;


import cn.enncy.scs.view.constant.color.SCSColor;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class SidePanel  extends JPanel {

    public SidePanel() {

        this.setBackground(SCSColor.SIDE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(120,0));
        GridLayout gridLayout = new GridLayout(6, 1);
        gridLayout.setHgap(20);

        this.setLayout(gridLayout);



    }
}
