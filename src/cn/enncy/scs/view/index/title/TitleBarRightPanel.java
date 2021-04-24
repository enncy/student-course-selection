package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.component.panel.ScsWhitePanel;
import cn.enncy.scs.view.frame.MainFrame;

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

    public TitleBarRightPanel() {
        FlowLayout rightFlowLayout = new FlowLayout(FlowLayout.RIGHT);
        rightFlowLayout.setHgap(10);
        this.setLayout(rightFlowLayout);


        // 最小化按钮
        TitleButton min = new TitleButton("—");
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.frame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        //最大化
        TitleButton max = new TitleButton("□");
        max.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int state =  MainFrame.frame.getExtendedState();
                if(state==JFrame.MAXIMIZED_BOTH){
                    MainFrame.frame.setExtendedState(JFrame.NORMAL);
                }
                if(state==JFrame.ICONIFIED || state==JFrame.NORMAL){
                    MainFrame.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });
        //关闭
        TitleButton close = new TitleButton("×");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MainFrame.frame.dispose();
                System.exit(0);
            }
        });


        this.add(min);
        this.add(max);
        this.add(close);
    }
}
