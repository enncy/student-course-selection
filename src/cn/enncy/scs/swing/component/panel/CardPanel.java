package cn.enncy.scs.swing.component.panel;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;



/**
 * //TODO   卡片布局
 * <br/>Created in 21:32 2021/4/25
 *
 * @author: enncy
 */
public class CardPanel  extends AdaptivePanel {

    private ScsWhitePanel container ;

    public CardPanel( String title,float percent) {
        super(percent);
        //主容器
        container = new ScsWhitePanel();
        container.setBorder(new EmptyBorder(10,10,10,10));

        //标题容器
        ScsWhitePanel titlePanel = new ScsWhitePanel(new BorderLayout());
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1,250));
        this.reSize(this,percent);
        this.setMinPercent(0.9f);

        this.setBorder(new EmptyBorder(10,10,10,10));
        //标题
        JLabel jLabel = new JLabel(title);
        jLabel.setFont(new Font("微软雅黑",0,16));
        jLabel.setBorder(new EmptyBorder(10,10,10,10));
        titlePanel.add(jLabel,BorderLayout.NORTH);

        //分割线
        JLabel separator = new JLabel("");
        separator.setBorder(new LineBorder(NiceColors.GRAY));
        separator.setPreferredSize(new Dimension(1,1));
        titlePanel.add(separator,BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        setEmpty(container);

    }


    // 设置为空的图案
    public static void setEmpty(JComponent component){
        if(component.getComponents().length==0){
            EmptyPanel emptyPanel = new EmptyPanel();
            component.add(emptyPanel,"icon");
        }else{
            for (Component c : component.getComponents()) {
                if("icon".equals(c.getName())){
                    component.remove(c);
                }
            }
        }
    }


    @Override
    public Component add(Component comp) {
        setEmpty(container);
        return container.add(comp);
    }

}
