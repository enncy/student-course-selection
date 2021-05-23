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
    public  ClassesManagePanel classesManagePanel = new ClassesManagePanel();
    public  MajorsManagePanel majorsManagePanel = new MajorsManagePanel();
    public  StudentsManagePanel studentsManagePanel = new StudentsManagePanel();
    public  TeachersManagePanel teachersManagePanel = new TeachersManagePanel();
    public  SettingsManagerPanel settingsManagerPanel = new SettingsManagerPanel();
    public  CoursesManagePanel coursesManagePanel = new CoursesManagePanel();
    public OptionalCourseManagePanel optionalCourseManagePanel = new OptionalCourseManagePanel();

    public InformationPanel() {
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(20,20,20,20));


        tabbedPane.addTab("专业管理",majorsManagePanel  );
        tabbedPane.addTab("班级管理",classesManagePanel  );
        tabbedPane.addTab("学生管理",studentsManagePanel  );
        tabbedPane.addTab("老师管理",teachersManagePanel  );
        tabbedPane.addTab("课程管理",coursesManagePanel  );
        tabbedPane.addTab("可选课程管理",optionalCourseManagePanel  );
        tabbedPane.addTab("系统设置",settingsManagerPanel  );

        this.add(tabbedPane,BorderLayout.CENTER);
    }


}
