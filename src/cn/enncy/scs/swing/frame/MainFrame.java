package cn.enncy.scs.swing.frame;


import cn.enncy.scs.swing.frame.base.BaseFrame;
import cn.enncy.scs.swing.frame.base.view.index.IndexPanel;
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

    private static final Logger log = Logger.getLogger(MainFrame.class);
    public static   JFrame frame;


    public static void init() {

        log.info("开始显示界面");
        frame =new BaseFrame("学生选课系统", 1200, 700, 600, 400);
        frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        frame.setLocationRelativeTo(null);
        IndexPanel indexPanel = new IndexPanel();
        frame.add(indexPanel, BorderLayout.CENTER);

        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);

    }

    public static void initTheme() {
        try {
            log.info("加载主题");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.debug = true;
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            // TODO exception
            log.error("加载主题失败:" + e.getMessage());
        }
    }


}
