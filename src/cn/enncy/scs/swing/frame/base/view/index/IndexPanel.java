package cn.enncy.scs.swing.frame.base.view.index;


import cn.enncy.scs.swing.component.panel.DropShadowPanel;
import cn.enncy.scs.swing.component.scroll.ScsScrollPanel;
import cn.enncy.scs.swing.component.title.DragPanel;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.MainFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.courses.CoursePanel;
import cn.enncy.scs.swing.frame.base.view.index.card.information.InformationPanel;
import cn.enncy.scs.swing.frame.base.view.index.card.personal.PersonalPanel;
import cn.enncy.scs.swing.frame.base.view.index.card.statistics.StatisticsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO   首页
 * <br/>Created in 18:21 2021/4/21
 *
 * @author: enncy
 */
public class IndexPanel extends DropShadowPanel {

    public DragPanel dragPanel;
    public SidePanel sidePanel;
    public InformationPanel informationPanel = new InformationPanel();
    public IndexPanel( ) {
        super(15);
        CardLayoutPanel cardLayoutPanel = CardLayoutPanel.getInstance();
        dragPanel = new DragPanel(MainFrame.frame);
        sidePanel = new SidePanel(dragPanel.getTitleBarPanel().getTitleBarLeftPanel());

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.add(sidePanel, BorderLayout.WEST);

        //卡片布局
        JPanel jPanel = new JPanel(new BorderLayout());

        //设置窗口标题
        dragPanel.getTitleBarPanel().getTitleBarLeftPanel().setTitle("学生选课系统");
        dragPanel.getContainer().add(cardLayoutPanel, BorderLayout.CENTER);
        jPanel.add(dragPanel, BorderLayout.CENTER);

        this.add(jPanel, BorderLayout.CENTER);


        StatisticsPanel statisticsPanel = new StatisticsPanel();
        PersonalPanel personalPanel = new PersonalPanel();
        CoursePanel coursePanel = new CoursePanel();

        if (LoginFrame.isManager) {

            cardLayoutPanel.add(informationPanel, "信息管理");
        }
        cardLayoutPanel.add(new ScsScrollPanel(statisticsPanel), "数据统计");
        cardLayoutPanel.add(personalPanel, "个人信息");
        cardLayoutPanel.add(coursePanel, "选课管理");


        sidePanel.addSCSLableSelectedListener(scsIconLabel -> {

            if (scsIconLabel.getText().equals("退出")) {
                sidePanel.selectDefault();
                MainFrame.frame.dispose();
                LoginFrame.loginDialog.setVisible(true);
            }else{
                cardLayoutPanel.showCard(scsIconLabel.getText());
            }

        });
        sidePanel.selectDefault();
        cardLayoutPanel.showCard("数据统计");

    }


}
