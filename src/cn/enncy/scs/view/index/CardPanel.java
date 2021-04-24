package cn.enncy.scs.view.index;


import cn.enncy.scs.view.constant.color.ScsColor;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class CardPanel extends JPanel {

    private CardLayout cardLayout;

    public CardPanel() {

        this.setBackground(new Color(255,255,255));

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.setBackground(ScsColor.GHOST_WHITE);
    }

    public void showCard(String cardName){
        cardLayout.show(this,cardName);
    }
}
