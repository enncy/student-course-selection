package cn.enncy.scs.swing.component;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 23:48 2021/4/21
 *
 * @author: enncy
 */
public class ScsLabel extends JLabel {

    private   int lineHeight = 48;


    private int fontSize = 14;
    private int borderSize = 10;
    private Color backgroundColor = NiceColors.SIDE_PANEL_COLOR;

    public ScsLabel(){  }

    public ScsLabel(String text, int w, int h) {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setText(text);
        this.setFont(new Font("微软雅黑", 0,fontSize));
        this.setBorder(new EmptyBorder(0,borderSize,0,borderSize));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        this.setPreferredSize(new Dimension(w, lineHeight));
        this.setForeground(NiceColors.GRAY);
        this.setBackground(backgroundColor);
        this.setOpaque(true);

    }

    public ScsLabel(String text, int w) {
        this(text, w, 48);
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        this.setFont(new Font("微软雅黑", 0,fontSize));
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        this.setBorder(new EmptyBorder(0,borderSize,0,borderSize));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackground(backgroundColor);
    }
}
