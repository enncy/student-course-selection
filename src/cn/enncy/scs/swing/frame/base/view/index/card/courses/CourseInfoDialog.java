package cn.enncy.scs.swing.frame.base.view.index.card.courses;


import cn.enncy.scs.pojo.Course;
import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.swing.component.ScsList;
import cn.enncy.scs.swing.component.dialog.ScsDialog;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 19:36 2021/5/30
 *
 * @author: enncy
 */
public class CourseInfoDialog  extends ScsDialog {


    public CourseInfoDialog(Course course, Teacher teacher) {
        super("课程详情");
        JPanel container = this.getContainer();
        JPanel jPanel = new ScsWhitePanel(new FlowLayout());
        System.out.println(course);
        jPanel.add(new ScsList("教师信息",teacher));
        jPanel.add(new ScsList("课程信息",course));
        container.add(jPanel, BorderLayout.CENTER);
        this.pack();
        this.setCanResize(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
