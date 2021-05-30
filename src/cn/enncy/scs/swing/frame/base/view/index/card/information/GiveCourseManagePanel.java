package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.GiveCourses;
import cn.enncy.scs.service.GiveCoursesService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 15:09 2021/5/30
 *
 * @author: enncy
 */
public class GiveCourseManagePanel  extends PagingManagerPanel {


    public GiveCourseManagePanel( ) {
        super(GiveCourses.class, ServiceFactory.getService(GiveCoursesService.class));
    }
}
