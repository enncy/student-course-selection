package cn.enncy.scs.view.index.card.statistics;


import cn.enncy.scs.view.component.panel.ScsGrayPanel;
import cn.enncy.scs.view.component.panel.StatisticsCard;
import cn.enncy.scs.view.index.CardLayoutPanel;

import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:04 2021/4/23
 *
 * @author: enncy
 */
public class StatisticsPanel extends ScsGrayPanel {

    public StatisticsPanel() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(20);
        flowLayout.setVgap(30);
        this.setPreferredSize(new Dimension(CardLayoutPanel.PANEL_WIDTH,CardLayoutPanel.PANEL_HEIGHT));

        this.setLayout(flowLayout);
        StatisticsCard courseNum = new StatisticsCard( 0.21f, "课程总数");
        courseNum.setImageLabel("icon/course-orange.png","1234");
        StatisticsCard optionalCourseNum = new StatisticsCard( 0.21f, "可选课程");
        optionalCourseNum.setImageLabel("icon/carry-out.png","155");
        StatisticsCard selectedCourseNum = new StatisticsCard( 0.21f, "已选课程");
        selectedCourseNum.setImageLabel("icon/schedule.png","1255");
        StatisticsCard remainingCourseNum = new StatisticsCard( 0.21f, "剩余课程");
        remainingCourseNum.setImageLabel("icon/funnel-plot.png","316");
        this.add(courseNum);
        this.add(optionalCourseNum);
        this.add(selectedCourseNum);
        this.add(remainingCourseNum);


        NoticePanel noticePanel = new NoticePanel("公告",0.44f);
        SelectionCoursePanel selectionCoursePanel = new SelectionCoursePanel("选课结果", 0.9f);
        SettingPanel settingPanel = new SettingPanel("设置",0.44f);

        this.add(selectionCoursePanel);
        this.add(settingPanel);
        this.add(noticePanel);


    }
}
