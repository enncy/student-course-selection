package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.factory.ServiceComponentFactory;
import cn.enncy.scs.service.*;
import cn.enncy.scs.swing.component.panel.ScsGrayPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:03 2021/4/23
 *
 * @author: enncy
 */
public class InformationPanel extends ScsGrayPanel {

    public InformationPanel() {
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(20, 20, 20, 20));


        tabbedPane.addTab("学生", ServiceComponentFactory.getServiceComponent(StudentService.class).get(0));
        tabbedPane.addTab("老师", ServiceComponentFactory.getServiceComponent(TeacherService.class).get(0));
        tabbedPane.addTab("专业", ServiceComponentFactory.getServiceComponent(MajorService.class).get(0));
        tabbedPane.addTab("班级", ServiceComponentFactory.getServiceComponent(ClassService.class).get(0));
        tabbedPane.addTab("课程", ServiceComponentFactory.getServiceComponent(CourseService.class).get(0));
        tabbedPane.addTab("授课", ServiceComponentFactory.getServiceComponent(GiveCoursesService.class).get(0));
        tabbedPane.addTab("可选课程", ServiceComponentFactory.getServiceComponent(OptionalCourseService.class).get(0));
        tabbedPane.addTab("选课", ServiceComponentFactory.getServiceComponent(CourseSelectionService.class).get(0));
        tabbedPane.addTab("自动选课", ServiceComponentFactory.getServiceComponent(AutoCourseSelectionService.class).get(0));
        tabbedPane.addTab("选课撤销", ServiceComponentFactory.getServiceComponent(CancelSelectionService.class).get(0));
        tabbedPane.addTab("系统设置", ServiceComponentFactory.getServiceComponent(SettingService.class).get(0));
        tabbedPane.addTab("管理员", ServiceComponentFactory.getServiceComponent(ManagerService.class).get(0));
        this.add(tabbedPane, BorderLayout.CENTER);
    }


}
