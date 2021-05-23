package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.OptionalCourse;
import cn.enncy.scs.service.OptionalCourseService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 10:15 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class OptionalCourseManagePanel extends ManagePanel {


    public OptionalCourseManagePanel() {
        super(OptionalCourse.class, new OptionalCourseService());
    }
}
