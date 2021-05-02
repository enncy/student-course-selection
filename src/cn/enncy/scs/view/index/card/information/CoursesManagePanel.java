package cn.enncy.scs.view.index.card.information;


import cn.enncy.scs.pojo.Course;
import cn.enncy.scs.service.CourseService;
import cn.enncy.scs.view.index.card.component.ManagePanel;

import javax.swing.*;

/**
 * //TODO
 * <br/>Created in 10:04 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class CoursesManagePanel extends ManagePanel {

    public CoursesManagePanel() {
        super(Course.class, new CourseService());

        JPanel headerPanel = this.getHeaderPanel();
        headerPanel.add(new JButton("查询"));
    }
}
