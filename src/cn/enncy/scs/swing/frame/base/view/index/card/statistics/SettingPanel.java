package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.service.SettingService;
import cn.enncy.scs.swing.component.panel.CardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * //TODO   设置显示面板
 * <br/>Created in 21:26 2021/4/25
 *
 * @author: enncy
 */
public class SettingPanel extends CardPanel {


    public SettingPanel(String title,float percent) {
        super(title,percent);

        SettingService settingService = ServiceFactory.getService(SettingService.class);

        //遍历设置
        List<BaseObject> all = settingService.findAll();
        Vector<String> vector = new Vector<>();
        JList<String> objectJList = new JList<>();
        for (BaseObject baseObject : all) {
            Setting setting = (Setting) baseObject;
            vector.add(setting.getDescription() + ":" + setting.getValue());
        }
        objectJList.setListData(vector);
        objectJList.setEnabled(false);
        this.add(objectJList, BorderLayout.CENTER);
    }
}
