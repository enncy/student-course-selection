package cn.enncy.scs.view.utils.event.drag;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * //TODO 拖拽事件
 * <br/>Created in 19:08 2021/4/21
 *
 * @author: enncy
 */
public class DragEvent {


    /**
     * 目标拖拽事件
     * @param moveComponent 被移动的组件
     * @param dragTarget    点击进行移动的组件
     * @return: void
     */
    public static void initDragEvent(Component moveComponent, Component dragTarget){

        final boolean[] isDragging = new boolean[1];
        final int[] x = new int[1];
        final int[] y = new int[1];

        dragTarget.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {     //鼠标点击时记录一下初始位置
                isDragging[0] = true;
                x[0] = e.getX();
                y[0] = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {  //鼠标松开时
                isDragging[0] = false;
            }
        });
        //时刻更新鼠标位置
        //添加指定的鼠标移动侦听器，以接收发自此组件的鼠标移动事件。如果侦听器 l 为 null，则不会抛出异常并且不执行动作。
        dragTarget.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //修改位置
                //只要鼠标是点击的（isDragging），就时刻更改窗体的位置
                if (isDragging[0]) {
                    int left = moveComponent.getLocation().x;
                    int top = moveComponent.getLocation().y;
                    moveComponent.setLocation(left + e.getX() - x[0], top + e.getY() - y[0]);
                }
            }
        });
    }

}
