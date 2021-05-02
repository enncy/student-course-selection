package cn.enncy.scs.view.component.scroll;


import cn.enncy.scs.view.index.CardLayoutPanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 22:56 2021/4/25
 *
 * @author: enncy
 */
public class ScsScrollPanel extends  JScrollPane{

    public ScsScrollPanel(Component view) {
        super(view,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setPreferredSize(new Dimension(CardLayoutPanel.PANEL_WIDTH ,CardLayoutPanel.PANEL_HEIGHT));
        this.getVerticalScrollBar().setUnitIncrement(20);
        this.getHorizontalScrollBar().setUnitIncrement(20);
    }

}
