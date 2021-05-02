package cn.enncy.scs.view.component.title;


import cn.enncy.scs.view.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 11:52 2021/4/24
 *
 * @author: enncy
 */
public class TitleBarRightPanel extends ScsWhitePanel {


    // jFrame 是需要控制的目标窗口
    public TitleBarRightPanel(JFrame jFrame) {

        //右对齐
        FlowLayout rightFlowLayout = new FlowLayout(FlowLayout.RIGHT);
        rightFlowLayout.setHgap(10);
        this.setLayout(rightFlowLayout);

        // 最小化按钮
        TitleButton min = new TitleButton("—");
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        //最大化
        TitleButton max = new TitleButton("□");
        max.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reSize(jFrame);
            }
        });
        //关闭
        TitleButton close = new TitleButton("×");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.dispose();
            }
        });
        this.add(min);
        this.add(max);
        this.add(close);
    }

    /**
     * 根据状态进行缩放
     * @param jFrame    目标窗体
     * @return: void
     */
    public static void reSize(JFrame jFrame){
        int state =  jFrame.getExtendedState();

        if(state==JFrame.MAXIMIZED_BOTH){
            //还原最大化之前的尺寸
            jFrame.setExtendedState(JFrame.NORMAL);
        }
        else if(state==JFrame.ICONIFIED || state==JFrame.NORMAL){
            //最大化
            jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
}
