package cn.enncy.swing.frame;


import org.apache.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 0:04 2021/4/14
 *
 * @author: enncy
 */
public class MainFrame extends Frame {

    private  static Logger log = Logger.getLogger(MainFrame.class);

    public static void  init(){
        try {
            log.info("加载主题");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            // TODO exception
        }
        log.info("开始显示界面");
        JFrame frame = new BaseFrame("学生选课系统",800,600);

        frame.setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout());
        frame.add(new JButton("button"));
        frame.add(new JButton("button"));
        frame.add(new JLabel("button"));
        frame.add(new JTextArea("button"));
        frame.add(new JCheckBox("button"));
        frame.add(new JCheckBox("button"));

        frame.setVisible(true);
    }

}
