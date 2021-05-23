package cn.enncy.scs.swing.frame.base;


import cn.enncy.scs.swing.component.frame.ResizeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

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
    public BaseFrame(String title,int width, int height,int minWidth,int minHeight , Component locationRelativeTo) {
        super(minWidth,minHeight);
        this.setTitle(title);
        this.setSize(width,height);
        this.setLocationRelativeTo(locationRelativeTo);
        this.init();
    }

    /**
     * 默认居中的基础布局
     * @param title  标题
     * @param minWidth  宽度
     * @param minHeight 长度
     */
    public BaseFrame(String title,int width, int height,int minWidth, int minHeight) {
        this(title,width,height,  minWidth, minHeight, null);
    }

    public BaseFrame( int width, int height,int minWidth, int minHeight) {
        this("",width,height,  minWidth, minHeight);
    }

    /**
     * 初始化
     * @return: void
     */
    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/logo.png")).getPath());
        // 设置标题栏的图标为face.gif
        this.setIconImage(imageIcon.getImage());

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
//                System.out.println(e.getX()+" | "+e.getY());
                super.mouseMoved(e);
            }
        });
    }




}
