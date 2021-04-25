package cn.enncy.scs.view.index;


import cn.enncy.scs.view.component.ScsIcon;
import cn.enncy.scs.view.component.ScsIconLabel;
import cn.enncy.scs.view.component.ScsLabelSelectedListener;
import cn.enncy.scs.view.constant.color.NiceColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 18:23 2021/4/21
 *
 * @author: enncy
 */
public class SidePanel  extends JPanel {

    public final static int SIDE_WIDTH = 160;
    private ScsLabelSelectedListener scsLabelSelectedListener;
    private ScsIconLabel selectedLabel = null;

    public SidePanel() {

        this.setBackground(NiceColors.SIDE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(SIDE_WIDTH,0));

        this.setLayout(new FlowLayout());

        this.add(new ScsIcon("icon/logo.png",64,48));
        ScsIconLabel statistics = new ScsIconLabel("数据统计", "icon/side/statistics.png", SIDE_WIDTH);
        ScsIconLabel information = new ScsIconLabel("信息管理", "icon/side/information.png", SIDE_WIDTH);
        ScsIconLabel person_add = new ScsIconLabel("教师管理", "icon/side/person_add.png", SIDE_WIDTH);
        ScsIconLabel course = new ScsIconLabel("课程管理", "icon/side/course.png", SIDE_WIDTH);
        ScsIconLabel setting = new ScsIconLabel("系统设置", "icon/side/setting.png", SIDE_WIDTH);


        List<ScsIconLabel> labelList = new ArrayList<>(Arrays.asList(statistics, information, person_add, course, setting));
        for (ScsIconLabel scsIconLabel : labelList) {
            scsIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ScsIconLabel component = (ScsIconLabel) e.getComponent();
                    //如果目标未被选中
                    if(  selectedLabel!=null  && ! component.equals(selectedLabel) ){
                        ScsIconLabel scsIconLabel1 = component;
                        //设置目标被选中
                        scsIconLabel.selected();
                        //之前被选中的目标取消选中
                        selectedLabel.cancelSelected();
                        //转换
                        selectedLabel = scsIconLabel;
                    }else{
                        component.selected();
                        selectedLabel =  component;
                    }

                }
            });

            scsIconLabel.addSCSLableSelectedListener(scsIconLabel1 -> {
                if(scsLabelSelectedListener !=null){
                    scsLabelSelectedListener.onSelected(scsIconLabel);
                }
            });

            this.add(scsIconLabel);
        }


    }

    public void addSCSLableSelectedListener(ScsLabelSelectedListener scsLabelSelectedListener){
        this.scsLabelSelectedListener = scsLabelSelectedListener;
    }
}
