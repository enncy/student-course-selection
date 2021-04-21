package cn.enncy.scs.view.frame;


import cn.enncy.scs.view.index.IndexPanel;
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
    public static JFrame frame = new BaseFrame("学生选课系统",1000,1000);

    public static void  init(){
        try {
            log.info("加载主题");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            // TODO exception
            log.error("加载主题失败:"+e.getMessage());
        }
        log.info("开始显示界面");

        frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        frame.add(new IndexPanel());
        frame.setUndecorated(true);

        frame.setBackground(new Color(0,0,0,10));
        frame.setVisible(true);

    }



}
