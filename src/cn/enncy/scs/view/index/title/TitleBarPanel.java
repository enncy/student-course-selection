package cn.enncy.scs.view.index.title;


import cn.enncy.scs.view.constant.color.ScsColor;
import cn.enncy.scs.view.utils.event.drag.DragEvent;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 * <br/>Created in 18:44 2021/4/21
 *
 * @author: enncy
 */
public class TitleBarPanel extends JPanel {




    public TitleBarPanel(Component component) {

        this.setLayout(new BorderLayout());
        //初始化拖拽事件
        DragEvent.initDragEvent(component,this);


        this.add(new TitleBarRightPanel(),BorderLayout.EAST);
        this.add(new TitleBarLeftPanel(),BorderLayout.WEST);

        this.setPreferredSize(new Dimension(0,36));

        this.setBackground(ScsColor.WHITE);
    }



}
