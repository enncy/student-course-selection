package cn.enncy.scs.view.index;


import cn.enncy.scs.view.component.panel.DropShadowPanel;
import cn.enncy.scs.view.component.scroll.ScsScrollPanel;
import cn.enncy.scs.view.component.title.TitlePanel;
import cn.enncy.scs.view.frame.MainFrame;
import cn.enncy.scs.view.index.card.course.CoursePanel;
import cn.enncy.scs.view.index.card.information.InformationPanel;
import cn.enncy.scs.view.index.card.setting.SettingPanel;
import cn.enncy.scs.view.index.card.statistics.StatisticsPanel;
import cn.enncy.scs.view.index.card.teacher.TeacherPanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:21 2021/4/21
 *
 * @author: enncy
 */
public class IndexPanel extends DropShadowPanel {

    public static final CardLayoutPanel CARD_LAYOUT_PANEL = new CardLayoutPanel();
    public static TitlePanel titlePanel = new TitlePanel(MainFrame.frame);
    public IndexPanel(SidePanel sidePanel, CardLayoutPanel cardLayoutPanel) {
        super(8);
        BorderLayout borderLayout  =new BorderLayout();
        this.setLayout(borderLayout);
        this.add(sidePanel,BorderLayout.WEST);

        //卡片布局
        JPanel jPanel = new JPanel(new BorderLayout());
        //设置窗口标题
        titlePanel.getTitleBarPanel().getTitleBarLeftPanel().setTitle("学生选课系统");
        jPanel.add(titlePanel,BorderLayout.NORTH);
        jPanel.add(cardLayoutPanel,BorderLayout.CENTER);
        this.add(jPanel,BorderLayout.CENTER);

        CoursePanel coursePanel = new CoursePanel();
        InformationPanel informationPanel = new InformationPanel();
        SettingPanel settingPanel = new SettingPanel();
        StatisticsPanel statisticsPanel = new StatisticsPanel();
        TeacherPanel teacherPanel = new TeacherPanel();


        cardLayoutPanel.add(new ScsScrollPanel(statisticsPanel), "数据统计");
        cardLayoutPanel.add(informationPanel, "信息管理");
        cardLayoutPanel.add(teacherPanel, "教师管理");
        cardLayoutPanel.add(coursePanel, "课程管理");
        cardLayoutPanel.add(settingPanel, "系统设置");



        sidePanel.addSCSLableSelectedListener(scsIconLabel -> {
            System.out.println(scsIconLabel.getText());
            cardLayoutPanel.showCard(scsIconLabel.getText());
        });


    }

    public IndexPanel() {
        this(new SidePanel(titlePanel.getTitleBarPanel().getTitleBarLeftPanel()),CARD_LAYOUT_PANEL);
    }


}
