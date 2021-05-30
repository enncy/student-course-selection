package cn.enncy.scs.swing.component.dialog;


import cn.enncy.scs.swing.component.panel.DropShadowPanel;
import cn.enncy.scs.swing.component.title.DragPanel;
import cn.enncy.scs.swing.frame.base.BaseFrame;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 17:15 2021/4/24
 *
 * @author: enncy
 */
public class ScsDialog extends BaseFrame {
    private DragPanel dragPanel = new DragPanel(this);
    public ScsDialog(String title) {
        super(400,300,400,300);


        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        DropShadowPanel dropShadowPanel = new DropShadowPanel(15);

        dragPanel.getTitleBarPanel().getTitleBarLeftPanel().setTitle(title);
        dropShadowPanel.add(dragPanel);
        this.add(dropShadowPanel);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));

        this.setVisible(true);

    }

    public JPanel getContainer(){
        return dragPanel.getContainer();
    }

    public DragPanel getDragPanel() {
        return dragPanel;
    }
}
