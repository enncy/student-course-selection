package cn.enncy.scs.swing.component.dialog;


import javax.swing.border.Border;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:39 2021/4/24
 *
 * @author: enncy
 */
public class RoundBorder implements Border {
    private Color color;

    public RoundBorder(Color color) {// 有参数的构造方法
        this.color = color;
    }

    public RoundBorder() {// 无参构造方法
        this.color = Color.BLACK;
        // 如果实例化时，没有传值
        // 默认是黑色边框
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(10,10,10,10);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    // 实现Border（父类）方法
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height) {
        g.setColor(color);
        g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 10, 10);
        g.setColor(Color.white);
        g.fillRoundRect(1, 1, c.getWidth()-2, c.getHeight()-2, 10, 10);
    }
}

