package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.OptionalCourse;
import cn.enncy.scs.service.OptionalCourseService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 10:15 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class OptionalCourseManagePanel extends PagingManagerPanel {


    public OptionalCourseManagePanel() {
        super(OptionalCourse.class,  ServiceFactory.getService(OptionalCourseService .class) );
    }
}
