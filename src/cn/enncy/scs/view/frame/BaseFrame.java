package cn.enncy.scs.view.frame;


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
public class BaseFrame extends JFrame {

    /**
     * @param title              标题
     * @param width              宽度
     * @param height             长度
     * @param locationRelativeTo 相对位置
     */
    public BaseFrame(String title, int width, int height, Component locationRelativeTo) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(locationRelativeTo);
        this.init();
    }

    /**
     * 默认居中的基础布局
     * @param title  标题
     * @param width  宽度
     * @param height 长度
     */
    public BaseFrame(String title, int width, int height) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.init();
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
