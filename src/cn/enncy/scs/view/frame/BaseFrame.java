package cn.enncy.scs.view.frame;


import cn.enncy.scs.view.component.frame.ResizeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * //TODO   基础Frame组件
 * <br/>Created in 0:13 2021/4/14
 *
 * @author: enncy
 */
public class BaseFrame extends ResizeFrame {

    /**
     * @param title              标题
     * @param minWidth           最小宽度
     * @param minHeight          最小长度
     * @param locationRelativeTo 相对位置
     */
    public BaseFrame(String title,int minWidth,int minHeight , Component locationRelativeTo) {
        super(minWidth,minHeight);
        this.setTitle(title);
        this.setSize(minWidth,minHeight);
        this.setLocationRelativeTo(locationRelativeTo);
        this.init();
    }

    /**
     * 默认居中的基础布局
     * @param title  标题
     * @param minWidth  宽度
     * @param minHeight 长度
     */
    public BaseFrame(String title, int minWidth, int minHeight) {
        this(title,  minWidth, minHeight, null);
    }

    public BaseFrame( int width, int height) {
        this("", width, height);
    }

    /**
     * 初始化
     * @return: void
     */
    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
//                System.out.println(e.getX()+" | "+e.getY());
                super.mouseMoved(e);
            }
        });
    }




}
