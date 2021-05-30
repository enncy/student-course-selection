package cn.enncy.scs.swing.frame.base.view.index.card.personal;


import cn.enncy.scs.pojo.Manager;
import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.swing.component.ScsIcon;
import cn.enncy.scs.swing.component.panel.CardPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.frame.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 10:06 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class PersonalPanel extends CardPanel {


    public PersonalPanel( ) {
        super("个人信息",90);
        JPanel jPanel = new ScsWhitePanel(new FlowLayout(FlowLayout.LEFT));
        if(LoginFrame.isManager){
            Manager manager = LoginFrame.manager;
            JList<String> jList = new JList<>();
            jList.setListData(new String[]{
                    "id:" + manager.getId(),
                    "名字:" + manager.getName(),
                    "账号:" + manager.getAccount(),
                    "密码:" + manager.getPassword(),
                    "创建时间:" + manager.getFormatCreateTime(),
                    "更新时间:" + manager.getFormatUpdateTime(),
            });
            jList.setEnabled(false);
            jPanel.add(new ScsIcon("icon/personal-bg.png",160,160) );
            jPanel.add(jList);
        }else{
            Student student = LoginFrame.student;
            JList<String> jList = new JList<>();
            jList.setListData(new String[]{
                    "id:" + student.getId(),
                    "名字:" + student.getName(),
                    "账号:" + student.getAccount(),
                    "密码:" + student.getPassword(),
                    "创建时间:" + student.getFormatCreateTime(),
                    "更新时间:" + student.getFormatUpdateTime(),
            });
            jList.setEnabled(false);
            jPanel.add(new ScsIcon("icon/personal-bg.png",160,160) );
            jPanel.add(jList);
        }
        this.add(jPanel, BorderLayout.CENTER);
    }
}
