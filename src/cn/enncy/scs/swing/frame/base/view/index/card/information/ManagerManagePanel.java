package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Manager;
import cn.enncy.scs.service.ManagerService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 21:54 2021/5/29
 *
 * @author: enncy
 */
public class ManagerManagePanel extends PagingManagerPanel {


    public ManagerManagePanel() {
        super(Manager.class, ServiceFactory.getService(ManagerService.class));
    }
}
