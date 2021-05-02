package cn.enncy.scs.view.index;


import cn.enncy.scs.view.constant.NiceColors;

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


    public CardLayoutPanel() {

        this.setBackground(new Color(255,255,255));
        this.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.setBackground(NiceColors.GHOST_WHITE);
    }

    public void showCard(String cardName){
        cardLayout.show(this,cardName);
    }
}
