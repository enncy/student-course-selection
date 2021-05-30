package cn.enncy.scs.swing.frame.base.view.index;


import cn.enncy.scs.swing.component.ScsIcon;
import cn.enncy.scs.swing.component.ScsIconLabel;
import cn.enncy.scs.swing.component.ScsLabelSelectedListener;
import cn.enncy.scs.swing.component.title.TitleBarLeftPanel;
import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.frame.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class SidePanel extends JPanel {

    public final static int SIDE_WIDTH = 160;
    private ScsLabelSelectedListener scsLabelSelectedListener;
    private ScsIconLabel selectedLabel;
    //根据权限展示不同界面
    public Map<String, ScsIconLabel> scsIconLabels;

    public TitleBarLeftPanel titleBarLeftPanel;

    public SidePanel(TitleBarLeftPanel titleBarLeftPanel) {
        this.titleBarLeftPanel = titleBarLeftPanel;
        scsIconLabels = new LinkedHashMap<>();
        this.setBackground(NiceColors.SIDE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(SIDE_WIDTH, 0));

        this.setLayout(new FlowLayout());

        this.add(new ScsIcon("icon/logo.png", 64, 48));


        ScsIconLabel statistics = new ScsIconLabel("数据统计", "icon/side/statistics.png", SIDE_WIDTH);
        ScsIconLabel information = new ScsIconLabel("信息管理", "icon/side/information.png", SIDE_WIDTH);
        ScsIconLabel personal = new ScsIconLabel("个人信息", "icon/side/personal.png", SIDE_WIDTH);
        ScsIconLabel course = new ScsIconLabel("选课管理", "icon/side/course.png", SIDE_WIDTH);
        //        ScsIconLabel setting = new ScsIconLabel("系统设置", "icon/side/setting.png", SIDE_WIDTH);
        ScsIconLabel logout = new ScsIconLabel("退出", "icon/side/logout.png", SIDE_WIDTH);

        System.out.println("isManager:"+LoginFrame.isManager);
        if (LoginFrame.isManager) {
            addToMap(statistics, information, personal, logout);
        } else {
            addToMap(statistics, course, personal, logout);
        }

        //遍历设置监听器
        for (Map.Entry<String, ScsIconLabel> item : scsIconLabels.entrySet()) {
            ScsIconLabel scsIconLabel = item.getValue();
            scsIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickScsIconLabel(scsIconLabel);
                }
            });

            scsIconLabel.addSCSLableSelectedListener(scsIconLabel1 -> {
                if (scsLabelSelectedListener != null) {
                    scsLabelSelectedListener.onSelected(scsIconLabel);
                }
            });


            this.add(scsIconLabel);
        }


    }

    public void addSCSLableSelectedListener(ScsLabelSelectedListener scsLabelSelectedListener) {
        this.scsLabelSelectedListener = scsLabelSelectedListener;
    }

    public void addToMap(ScsIconLabel... scsIconLabel) {
        for (ScsIconLabel iconLabel : scsIconLabel) {
            scsIconLabels.put(iconLabel.getText(), iconLabel);
        }
    }

    public void clickScsIconLabel(ScsIconLabel scsIconLabel){

        //如果目标未被选中
        if (selectedLabel != null && !scsIconLabel.equals(selectedLabel)) {
            //设置目标被选中
            scsIconLabel.select();
            //之前被选中的目标取消选中
            selectedLabel.cancelSelected();
        } else {
            scsIconLabel.select();
        }
        //转换
        selectedLabel = scsIconLabel;


        if("退出".equals(scsIconLabel.getText())){
            scsIconLabel.setSelected(false);
        }else{
            titleBarLeftPanel.setTitleInfo(" - " + scsIconLabel.getText());
        }

    }

    //默认选中
    public void selectDefault() {
        for(Map.Entry<String, ScsIconLabel> item : scsIconLabels.entrySet()){
            ScsIconLabel scsIconLabel = item.getValue();
            scsIconLabel.cancelSelected();
        }
        ScsIconLabel statistics = scsIconLabels.get("数据统计");
        statistics.hover();
        clickScsIconLabel(statistics);
        this.updateUI();
        this.setVisible(true);

    }
}
