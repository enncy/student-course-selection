package cn.enncy.scs.view.index;


import cn.enncy.scs.view.constant.color.SCSColor;
import cn.enncy.scs.view.frame.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * //TODO
 * <br/>Created in 18:21 2021/4/21
 *
 * @author: enncy
 */
public class IndexPanel extends JPanel {

    //边框宽带
    private static final int BORDER_SIZE = 5;

    private boolean isDragging = false;


    public IndexPanel(SidePanel sidePanel, MainPanel mainPanel) {
        BorderLayout borderLayout  =new BorderLayout();
        this.setLayout(borderLayout);
        this.add(sidePanel,BorderLayout.WEST);
        this.add(mainPanel,BorderLayout.CENTER);
        this.setBorder(new LineBorder(SCSColor.BLACK));



        initResizeEvent(this);
    }

    public IndexPanel() {
        this(new SidePanel(),new MainPanel());
    }

    private void initResizeEvent(Component component){


        // 1 上下 2 左右 3 斜着的


        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });

        //修改窗口大小的事件处理
        component.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println((component.getWidth()) + " | " + (e.getY()));



                if (isDragging) {

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int height = e.getComponent().getHeight();
                int width = e.getComponent().getWidth();
                int y = e.getY();
                int x = e.getX();
                int dy = Math.abs(height-y);
                int dx = Math.abs(width-x);




                // 4个斜方向的伸缩
                if(x<BORDER_SIZE && y<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                }else if(x<BORDER_SIZE && dy<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                }else if(y<BORDER_SIZE && dx<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                }else if(dy<BORDER_SIZE && dx<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                }

                // 上下左右伸缩
                else if(dy<BORDER_SIZE || y<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                } else if(dx<BORDER_SIZE || x<BORDER_SIZE){
                    MainFrame.frame.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                }else {
                    MainFrame.frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }

                super.mouseMoved(e);
            }
        });
    }
}
