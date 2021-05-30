package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.service.SettingService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 21:55 2021/4/30
 *
 * @author: KL-Skeleton
 */
public class SettingsManagerPanel extends PagingManagerPanel {


    public SettingsManagerPanel( ) {
        super(Setting.class, ServiceFactory.getService(SettingService.class)  );
    }
}
