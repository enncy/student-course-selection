package cn.enncy.scs.swing.frame.base.view.index.card.information;


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
public class InformationPanel  extends ScsGrayPanel {
    public ManagerManagePanel managerManagePanel = new ManagerManagePanel();
    public  StudentsManagePanel studentsManagePanel = new StudentsManagePanel();
    public  TeachersManagePanel teachersManagePanel = new TeachersManagePanel();
    public  ClassesManagePanel classesManagePanel = new ClassesManagePanel();
    public  MajorsManagePanel majorsManagePanel = new MajorsManagePanel();
    public  SettingsManagerPanel settingsManagerPanel = new SettingsManagerPanel();
    public  CoursesManagePanel coursesManagePanel = new CoursesManagePanel();
    public CourseSelectionManagePanel courseSelectionManagePanel = new CourseSelectionManagePanel();
    public AutoCourseSelectionManagePanel autoCourseSelectionManagePanel = new AutoCourseSelectionManagePanel();
    public CancelSelectionManagePanel cancelSelectionManagePanel = new CancelSelectionManagePanel();
    public OptionalCourseManagePanel optionalCourseManagePanel = new OptionalCourseManagePanel();
    public GiveCourseManagePanel giveCourseManagePanel = new GiveCourseManagePanel();

    public InformationPanel() {
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(20,20,20,20));




        tabbedPane.addTab("学生",studentsManagePanel  );
        tabbedPane.addTab("老师",teachersManagePanel  );
        tabbedPane.addTab("专业",majorsManagePanel  );
        tabbedPane.addTab("班级",classesManagePanel  );
        tabbedPane.addTab("课程",coursesManagePanel  );
        tabbedPane.addTab("授课",giveCourseManagePanel  );
        tabbedPane.addTab("可选课程",optionalCourseManagePanel  );
        tabbedPane.addTab("选课",courseSelectionManagePanel  );
        tabbedPane.addTab("自动选课",autoCourseSelectionManagePanel  );
        tabbedPane.addTab("选课撤销",cancelSelectionManagePanel  );
        tabbedPane.addTab("系统设置",settingsManagerPanel  );
        tabbedPane.addTab("管理员",managerManagePanel  );
        this.add(tabbedPane,BorderLayout.CENTER);
    }


}
