package cn.enncy.scs.view.component.title;


import cn.enncy.scs.view.utils.DragEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 18:44 2021/4/21
 *
 * @author: enncy
 */
public class TitleBarPanel extends JPanel {
    public   TitleBarLeftPanel titleBarLeftPanel = new TitleBarLeftPanel("");

    /**
     *
 * @param jframe
     * @return:
     */
    public TitleBarPanel(JFrame jframe) {

        this.setLayout(new BorderLayout());
        //给父窗体初始化拖拽事件
        DragEvent.initDragEvent(jframe,this);
        //添加右界面
        this.add(new TitleBarRightPanel(jframe),BorderLayout.EAST);
        //添加左界面
        this.add(titleBarLeftPanel,BorderLayout.WEST);
        //设置高度
        this.setPreferredSize(new Dimension(0, 36));
        //设置背景
        this.setBackground(Color.WHITE);
        //双击标题栏，进行缩放
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    TitleBarRightPanel.reSize(jframe);
                }
                super.mouseClicked(e);
            }
        });
    }

    public TitleBarLeftPanel getTitleBarLeftPanel() {
        return titleBarLeftPanel;
    }

}
