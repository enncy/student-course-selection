package cn.enncy.scs.swing.component.title;


import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO   拖拽面板
 * <br/>Created in 11:16 2021/4/24
 *
 * @author: enncy
 */
public class DragPanel extends ScsWhitePanel {
    public  TitleBarPanel titleBarPanel  ;
    private final JPanel container;
    public DragPanel(JFrame jFrame) {
        titleBarPanel=  new TitleBarPanel(jFrame);
        container = new JPanel( new BorderLayout());
        this.setLayout(new BorderLayout());
        container.setBackground(NiceColors.WHITE);
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
