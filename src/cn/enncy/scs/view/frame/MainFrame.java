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
public class MainFrame {

    private  static Logger log = Logger.getLogger(MainFrame.class);
    public static JFrame frame = new BaseFrame("学生选课系统",500,400);

    public static void  init(){
        try {
            log.info("加载主题");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.debug = true;
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            // TODO exception
            log.error("加载主题失败:"+e.getMessage());
        }
        log.info("开始显示界面");

        frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.add(new IndexPanel(),BorderLayout.CENTER);

        frame.setUndecorated(true);

        frame.setVisible(true);

    }



}
