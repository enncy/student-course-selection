package cn.enncy.scs.swing.component.dialog;


import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.frame.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 13:14 2021/4/24
 *
 * @author: enncy
 */
public class ScsAlert extends JDialog {

    //弹窗个数
    private static int num=0;
    public static Component parent = MainFrame.frame;

    public static ScsAlert alert(String text, Color borderColor, Color backgroundColor){
        JLabel jLabel = new JLabel(text);
        jLabel.setFont(new Font("微软雅黑", 0, 12));
        return alert(jLabel,borderColor,backgroundColor);
    }

    public static ScsAlert success(String text){
        return alert(text,NiceColors.LIGHT_GREEN,NiceColors.WHITE_GREEN);
    }
    public static ScsAlert warining(String text){
        return alert(text,NiceColors.LIGHT_ORANGE,NiceColors.WHITE_ORANGE);
    }
    public static ScsAlert info(String text){
        return alert(text,NiceColors.LIGHT_BLUE,NiceColors.WHITE_BLUE);
    }
    public static ScsAlert error(String text){
        return alert(text,NiceColors.LIGHT_RED,NiceColors.WHITE_RED);
    }

    public static ScsAlert alert( Component component, Color borderColor, Color backgroundColor){
        return alert(parent, component, borderColor, backgroundColor);
    }

    public static ScsAlert alert(Component parent,Component component, Color borderColor, Color backgroundColor){
        num++;
        ScsAlert scsAlert = new ScsAlert(10 +(num*50),component,borderColor,backgroundColor);
        scsAlert.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(2000);

                Component frame = parent;
                int x=frame.getX() + (frame.getWidth()/2) -( scsAlert.getWidth()/2);
                int y=frame.getY()+ 50;
                while (scsAlert.getY()>frame.getY()){
                    scsAlert.setLocation(x, scsAlert.getY()-1);
                }
                scsAlert.setVisible(false);
                num--;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        return scsAlert;
    }


    private ScsAlert(int height, Component component, Color borderColor, Color backgroundColor) {
        this.setSize(250,30);
        this.setCenter(height);
        this.setUndecorated(true);
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new LineBorder(borderColor));
        jPanel.setBackground(backgroundColor);
        jPanel.setSize(250,30);
        jPanel.add(component);
        this.add(jPanel);
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(0,0,0,0));
    }

    private void setCenter(int height){
        Component component = MainFrame.frame;
        int x=component.getX() + (component.getWidth()/2) -( this.getWidth()/2);
        int y=component.getY()+ height;
        this.setLocation(x,y);
    }

    public static void autoAlert(int status,String lessThanZeroMsg,String equalsZeroMsg,String moreThanZeroMsg){
        if(status==0) {
            ScsAlert.warining(equalsZeroMsg);
        } else if(status>0) {
            ScsAlert.success(moreThanZeroMsg);
        } else {
            ScsAlert.error(lessThanZeroMsg);
        }
    }

}
