package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.constant.color.SCSColor;
import cn.enncy.scs.view.frame.MainFrame;
import cn.enncy.scs.view.panel.SCSPanel;
import cn.enncy.scs.view.utils.event.drag.DragEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 18:44 2021/4/21
 *
 * @author: enncy
 */
public class TitleBarPanel extends JPanel {



    public TitleBarPanel() {

        this.setLayout(new BorderLayout());
        //初始化拖拽事件
        DragEvent.initDragEvent(MainFrame.frame,this);

        FlowLayout rightFlowLayout = new FlowLayout(FlowLayout.RIGHT);
        rightFlowLayout.setHgap(10);
        SCSPanel rightPanel = new SCSPanel(rightFlowLayout);
        FlowLayout leftFlowLayout = new FlowLayout(FlowLayout.LEFT);
        SCSPanel leftPanel = new SCSPanel(leftFlowLayout);

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
            }
        });

        rightPanel.add(min);
        rightPanel.add(max);
        rightPanel.add(close);

        this.add(rightPanel,BorderLayout.EAST);
        this.add(leftPanel,BorderLayout.WEST);

        this.setPreferredSize(new Dimension(0,36));
        this.setBackground(SCSColor.WHITE);
    }



}
