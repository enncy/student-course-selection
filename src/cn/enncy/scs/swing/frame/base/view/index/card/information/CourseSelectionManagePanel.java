package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.service.CourseSelectionService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 17:22 2021/5/30
 *
 * @author: enncy
 */
public class CourseSelectionManagePanel extends PagingManagerPanel {


    public CourseSelectionManagePanel( ) {
        super(CourseSelection.class, ServiceFactory.getService(CourseSelectionService.class));
    }
}
