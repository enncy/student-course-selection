package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.AutoCourseSelection;
import cn.enncy.scs.service.AutoCourseSelectionService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 17:22 2021/5/30
 *
 * @author: enncy
 */
public class AutoCourseSelectionManagePanel extends PagingManagerPanel {


    public AutoCourseSelectionManagePanel() {
        super(AutoCourseSelection.class, ServiceFactory.getService(AutoCourseSelectionService.class));
    }
}
