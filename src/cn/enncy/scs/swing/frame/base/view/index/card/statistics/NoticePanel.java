package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.service.SettingService;
import cn.enncy.scs.swing.component.panel.CardPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO 公告面板
 * <br/>Created in 21:23 2021/4/25
 *
 * @author: enncy
 */
public class NoticePanel extends CardPanel {


    public NoticePanel(String title,float percent) {
        super(title,percent);

        SettingService settingService = ServiceFactory.getService(SettingService.class);
        Setting notice = (Setting) settingService.findByName("notice");
        JPanel jPanel = new ScsWhitePanel();
        if(!notice.getValue().equals("无")){
            JLabel jLabel = new JLabel("公告:" + notice.getValue());
            jLabel.setFont(new Font("微软雅黑",1,20));
            jPanel.add(jLabel);
        }

        this.add(jPanel, BorderLayout.CENTER);
    }

}
