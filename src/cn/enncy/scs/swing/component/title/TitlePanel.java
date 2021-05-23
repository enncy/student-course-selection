package cn.enncy.scs.swing.component.title;


import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 11:16 2021/4/24
 *
 * @author: enncy
 */
public class TitlePanel extends ScsWhitePanel {
    public  TitleBarPanel titleBarPanel  ;
    private final JPanel container;
    public TitlePanel(JFrame jFrame) {
        titleBarPanel=  new TitleBarPanel(jFrame);
        container = new JPanel( new BorderLayout());
        this.setLayout(new BorderLayout());

        this.add(titleBarPanel,BorderLayout.NORTH);
        this.add(container,BorderLayout.CENTER);

    }

    public TitleBarPanel getTitleBarPanel() {
        return titleBarPanel;
    }


    public JPanel getContainer() {
        return container;
    }
}
