package cn.enncy.scs.view.component.dialog;


import cn.enncy.scs.view.component.panel.DropShadowPanel;
import cn.enncy.scs.view.component.title.TitlePanel;
import cn.enncy.scs.view.frame.BaseFrame;

import java.awt.*;

/**
 * //TODO
 * <br/>Created in 17:15 2021/4/24
 *
 * @author: enncy
 */
public class ScsDialog extends BaseFrame {
    private TitlePanel titlePanel = new TitlePanel(this);
    public ScsDialog(String title) {
        super(400,300,400,300);


        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        DropShadowPanel dropShadowPanel = new DropShadowPanel(8);

        titlePanel.getTitleBarPanel().getTitleBarLeftPanel().setTitle(title);
        dropShadowPanel.add(titlePanel);
        this.add(dropShadowPanel);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));

        this.setVisible(true);

    }

    public TitlePanel getTitlePanel() {
        return titlePanel;
    }
}
