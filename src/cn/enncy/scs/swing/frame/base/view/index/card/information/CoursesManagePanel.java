package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Course;
import cn.enncy.scs.service.CourseService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

import javax.swing.*;

/**
 * //TODO
 * <br/>Created in 10:04 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class CoursesManagePanel extends PagingManagerPanel {

    public CoursesManagePanel() {
        super(Course.class, ServiceFactory.getService(CourseService.class)  );

        JPanel headerPanel = this.getHeaderPanel();
        headerPanel.add(new JButton("查询"));
    }
}
