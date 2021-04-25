package cn.enncy.scs.view.component.panel;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 15:35 2021/4/24
 *
 * @author: enncy
 */
public class DropShadowPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public int pixels;

    public DropShadowPanel(int pix) {
        this.pixels = pix;
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        int shade = 0;
        int topOpacity = 80;
        for (int i = 0; i < pixels; i++) {
            g.setColor(new Color(shade, shade, shade, ((topOpacity / (pixels*2)) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }
}