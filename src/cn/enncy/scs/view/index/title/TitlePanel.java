package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.constant.color.ScsColor;

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


    
    public TitlePanel(Component component) {
        this.setLayout(new BorderLayout());

        this.setBorder(new EmptyBorder(5,0,0,5));
        this.setBackground(ScsColor.WHITE);
        this.add(new TitleBarPanel(component),BorderLayout.NORTH);
    }
}
