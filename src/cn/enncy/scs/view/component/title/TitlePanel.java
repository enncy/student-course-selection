package cn.enncy.scs.view.component.title;


import cn.enncy.scs.view.constant.color.NiceColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 11:16 2021/4/24
 *
 * @author: enncy
 */
public class TitlePanel extends JPanel {

    public TitlePanel(JFrame jFrame) {

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5,5,0,5));
        this.setBackground(NiceColors.WHITE);
        TitleBarPanel titleBarPanel = new TitleBarPanel(jFrame);

        this.add(titleBarPanel,BorderLayout.NORTH);
    }
}