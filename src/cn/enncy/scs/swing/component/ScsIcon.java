package cn.enncy.scs.swing.component;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 14:15 2021/4/22
 *
 * @author: enncy
 */
public class ScsIcon extends JLabel {

    public ScsIcon(String imgUri, int w, int h) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgUri)).getPath());
        icon.setImage(icon.getImage().getScaledInstance(w,h ,Image.SCALE_AREA_AVERAGING ));
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setIcon(icon);
    }
}
