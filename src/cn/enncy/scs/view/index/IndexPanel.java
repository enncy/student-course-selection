package cn.enncy.scs.view.index;


import cn.enncy.scs.view.frame.MainFrame;
import cn.enncy.scs.view.index.card.course.CoursePanel;
import cn.enncy.scs.view.index.card.information.InformationPanel;
import cn.enncy.scs.view.index.card.setting.SettingPanel;
import cn.enncy.scs.view.index.card.statistics.StatisticsPanel;
import cn.enncy.scs.view.index.card.teacher.TeacherPanel;
import cn.enncy.scs.view.index.title.TitlePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:21 2021/4/21
 *
 * @author: enncy
 */
public class IndexPanel extends JPanel {



    public IndexPanel(SidePanel sidePanel, CardPanel cardPanel) {
        BorderLayout borderLayout  =new BorderLayout();
        this.setLayout(borderLayout);
        this.add(sidePanel,BorderLayout.WEST);

        //卡片布局
        JPanel jPanel = new JPanel(new BorderLayout());
        TitlePanel titlePanel = new TitlePanel(MainFrame.frame);
        jPanel.add(titlePanel,BorderLayout.NORTH);
        jPanel.add(cardPanel,BorderLayout.CENTER);
        this.add(jPanel,BorderLayout.CENTER);

        CoursePanel coursePanel = new CoursePanel();
        InformationPanel informationPanel = new InformationPanel();
        SettingPanel settingPanel = new SettingPanel();
        StatisticsPanel statisticsPanel = new StatisticsPanel();
        TeacherPanel teacherPanel = new TeacherPanel();


        cardPanel.add(statisticsPanel, "数据统计");
        cardPanel.add(informationPanel, "信息管理");
        cardPanel.add(teacherPanel, "教师管理");
        cardPanel.add(coursePanel, "课程管理");
        cardPanel.add(settingPanel, "系统设置");



        sidePanel.addSCSLableSelectedListener(scsIconLabel -> {
            System.out.println(scsIconLabel.getText());
            cardPanel.showCard(scsIconLabel.getText());
        });


    }

    public IndexPanel() {
        this(new SidePanel(),new CardPanel());
    }


}
