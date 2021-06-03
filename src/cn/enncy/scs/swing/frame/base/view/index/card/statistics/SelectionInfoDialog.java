package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.pojo.Course;
import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.swing.component.ScsList;
import cn.enncy.scs.swing.component.dialog.ScsDialog;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 22:53 2021/6/2
 *
 * @author: enncy
 */
public class SelectionInfoDialog extends ScsDialog {


    public SelectionInfoDialog(Course course, Teacher teacher, Student student) {
        super("选课信息");

        JPanel container = this.getContainer();
        JPanel jPanel = new ScsWhitePanel(new GridLayout(1,2));

        jPanel.add(new ScsList("教师信息",teacher));
        jPanel.add(new ScsList("课程信息",course));
        jPanel.add(new ScsList("学生信息",student));
        container.add(jPanel, BorderLayout.CENTER);
        this.pack();
        this.setCanResize(false);
        this.setLocationRelativeTo(null);

    }
}
