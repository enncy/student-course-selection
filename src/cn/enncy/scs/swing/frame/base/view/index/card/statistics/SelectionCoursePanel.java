package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.factory.ServiceComponentFactory;
import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.service.CourseSelectionService;
import cn.enncy.scs.swing.component.panel.CardPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO   选课结果面板
 * <br/>Created in 21:27 2021/4/25
 *
 * @author: enncy
 */
public class SelectionCoursePanel extends CardPanel {



    public SelectionCoursePanel(String title, float percent) {
        super(title, percent);
        //新建选课结果组件
        SelectionCourseServicePanel selectionCourseServicePanel = new SelectionCourseServicePanel(CourseSelection.class,ServiceFactory.getService(CourseSelectionService.class));
        //将业务组件加入
        ServiceComponentFactory.addServiceComponent(CourseSelectionService.class,selectionCourseServicePanel);
        JPanel jPanel = new ScsWhitePanel(new BorderLayout());

        jPanel.add(selectionCourseServicePanel);

        this.add(jPanel, BorderLayout.CENTER);

    }

}
