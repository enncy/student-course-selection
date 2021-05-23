package cn.enncy.scs.swing.component.panel;


import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:23 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class ScsGridLayout  implements LayoutManager {



    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int componentCount = parent.getComponentCount();
        for (int i = 0; i < componentCount; i++) {
            Component component = parent.getComponent(i);
            System.out.println(component);
        }
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {

    }
}
