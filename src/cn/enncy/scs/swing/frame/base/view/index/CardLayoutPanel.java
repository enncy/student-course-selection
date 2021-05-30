package cn.enncy.scs.swing.frame.base.view.index;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class CardLayoutPanel extends JPanel {

    private CardLayout cardLayout;
    public static final int PANEL_WIDTH = 1024;
    public static final int PANEL_HEIGHT = 700;

    private static final CardLayoutPanel CARD_LAYOUT_PANEL = new CardLayoutPanel();
    public CardLayoutPanel() {

        this.setBackground(new Color(255,255,255));
        this.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.setBackground(NiceColors.GHOST_WHITE);
    }

    public static CardLayoutPanel getInstance(){
        return CARD_LAYOUT_PANEL;
    }

    public void showCard(String cardName){
        cardLayout.show(this,cardName);
    }
}
