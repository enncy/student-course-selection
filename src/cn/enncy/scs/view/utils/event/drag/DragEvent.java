package cn.enncy.scs.view.utils.event.drag;


import javax.swing.*;
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

                try {
                    //如果从最大化状态拖动，则变回正常尺寸
                    JFrame jFrame = ((JFrame) moveComponent);
                    int state =  jFrame.getExtendedState();

                    if(state==JFrame.MAXIMIZED_BOTH){
                        // 获取侧边栏的宽度，如果没侧边栏可以忽略
                        int sideWidth = moveComponent.getWidth() - e.getComponent().getWidth();
                        //还原尺寸
                        jFrame.setExtendedState(JFrame.NORMAL);
                        //获取屏幕大小
                        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                        // 获取整个屏幕中，拖拽组件点击x坐标相对于整个屏幕的比例，
                        // 例如 rate 为 0.5 ，则说明点击的位置，是拖拽组件的正中央
                        double rate = ((double) (e.getXOnScreen() - sideWidth)/ (double) width);
                        // 获取被移动窗体中，拖拽组件距离窗体x坐标的距离
                        int resultX = (int) ((moveComponent.getWidth()-sideWidth)   * rate);
                        // 替换点击按下时的坐标
                        x[0] = resultX;
                        moveComponent.setLocation(e.getXOnScreen() - resultX ,y[0]);

                    }else{
                        //修改位置
                        //只要鼠标是点击的（isDragging），就时刻更改窗体的位置
                        if (isDragging[0]) {
                            int left = moveComponent.getLocation().x;
                            int top = moveComponent.getLocation().y;
                            moveComponent.setLocation(left + e.getX() - x[0], top + e.getY() - y[0]);
                        }
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        });
    }

}
