package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.CancelSelection;
import cn.enncy.scs.service.CancelSelectionService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 17:29 2021/5/30
 *
 * @author: enncy
 */
public class CancelSelectionManagePanel extends PagingManagerPanel {


    public CancelSelectionManagePanel( ) {
        super(CancelSelection.class, ServiceFactory.getService(CancelSelectionService.class));
    }
}
