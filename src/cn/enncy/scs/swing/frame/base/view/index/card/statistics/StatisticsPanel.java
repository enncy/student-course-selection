package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.swing.component.panel.ScsGrayPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * //TODO
 * <br/>Created in 14:04 2021/4/23
 *
 * @author: enncy
 */
public class StatisticsPanel extends ScsGrayPanel {

    public StatisticsPanel() {
        BorderLayout flowLayout = new BorderLayout();
        flowLayout.setHgap(20);
        flowLayout.setVgap(30);

        this.setPreferredSize(new Dimension(0,800));

        this.setLayout(flowLayout);
        this.setBorder(new EmptyBorder(20,20,20,20));



        NoticePanel noticePanel = new NoticePanel("公告",0.44f);
        SelectionCoursePanel selectionCoursePanel = new SelectionCoursePanel("选课结果", 0.9f);
        SettingPanel settingPanel = new SettingPanel("设置",0.44f);

        this.add(selectionCoursePanel,BorderLayout.CENTER);
        JPanel jPanel = new ScsWhitePanel(new FlowLayout());
        jPanel.add(settingPanel);
        jPanel.add(noticePanel);
        this.add(jPanel,BorderLayout.SOUTH);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();


                super.componentResized(e);
            }
        });
    }
}
