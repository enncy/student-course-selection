package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.index.IndexPanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * //TODO 自适应面板
 * <br/>Created in 14:23 2021/4/25
 *
 * @author: enncy
 */
public class AdaptivePanel extends ScsWhitePanel {

    public   int minWidth = 600;
    public float minPercent = 0.4f;

    public AdaptivePanel(float percent) {

         reSize(this, percent);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                Component originComponent = e.getComponent();
                reSize(originComponent, percent);
            }
        });
    }
    //自动适应布局
    public  void reSize(Component originComponent ,float percent){
        Component component = IndexPanel.CARD_LAYOUT_PANEL;

        if(component.getWidth()<minWidth){
            Dimension dimension = new Dimension((int) (component.getWidth() * minPercent), originComponent.getHeight());
            originComponent.setPreferredSize(dimension);
        }else{
            Dimension dimension = new Dimension((int) (component.getWidth() * percent), originComponent.getHeight());
            originComponent.setPreferredSize(dimension);
        }

    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public float getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(float minPercent) {
        this.minPercent = minPercent;
    }
}
