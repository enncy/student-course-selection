package cn.enncy.scs.view.index.card.information;


import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.service.SettingService;
import cn.enncy.scs.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 21:55 2021/4/30
 *
 * @author: KL-Skeleton
 */
public class SettingsManagerPanel extends ManagePanel {


    public SettingsManagerPanel( ) {
        super(Setting.class, new SettingService());
    }
}
