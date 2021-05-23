package cn.enncy.scs.swing.component.panel;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * //TODO
 * <br/>Created in 14:43 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class GridPanel extends ScsWhitePanel {
    public   int minWidth = 200;
    public float minPercent = 0.4f;
    public Component parent;

    public GridPanel(Component parent,float percent) {
        this.parent = parent;

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
        Component component = parent;

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
