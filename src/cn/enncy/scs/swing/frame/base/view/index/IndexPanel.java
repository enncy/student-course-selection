package cn.enncy.scs.swing.frame.base.view.index;


import cn.enncy.scs.swing.component.panel.DropShadowPanel;
import cn.enncy.scs.swing.component.scroll.ScsScrollPanel;
import cn.enncy.scs.swing.component.title.TitlePanel;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.MainFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.courses.CoursePanel;
import cn.enncy.scs.swing.frame.base.view.index.card.information.InformationPanel;
import cn.enncy.scs.swing.frame.base.view.index.card.personal.PersonalPanel;
import cn.enncy.scs.swing.frame.base.view.index.card.statistics.StatisticsPanel;

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
        titlePanel.getContainer().add(cardLayoutPanel,BorderLayout.CENTER);
        jPanel.add(titlePanel,BorderLayout.CENTER);

        this.add(jPanel,BorderLayout.CENTER);



        StatisticsPanel statisticsPanel = new StatisticsPanel();
        PersonalPanel personalPanel = new PersonalPanel();
        CoursePanel coursePanel = new CoursePanel();

        if(LoginFrame.isManager){
            InformationPanel informationPanel = new InformationPanel();
            cardLayoutPanel.add(informationPanel, "信息管理");
        }
        cardLayoutPanel.add(new ScsScrollPanel(statisticsPanel), "数据统计");
        cardLayoutPanel.add(personalPanel, "个人信息");
        cardLayoutPanel.add(coursePanel, "选课管理");



        sidePanel.addSCSLableSelectedListener(scsIconLabel -> {
            System.out.println(scsIconLabel.getText());
            cardLayoutPanel.showCard(scsIconLabel.getText());
        });


    }

    public IndexPanel() {
        this(new SidePanel(titlePanel.getTitleBarPanel().getTitleBarLeftPanel()),CARD_LAYOUT_PANEL);
    }


}
