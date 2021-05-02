package cn.enncy.scs.view.index.card.courses;


import cn.enncy.scs.view.component.panel.CardPanel;
import cn.enncy.scs.view.component.panel.ScsGrayPanel;

import java.awt.*;

/**
 * //TODO
 * <br/>Created in 14:14 2021/5/2
 *
 * @author: enncy
 */
public class CoursePanel extends ScsGrayPanel {


    public CoursePanel() {
        this.setLayout(new BorderLayout());

        CardPanel cardPanel = new CardPanel("可选课程",0.9f);

        CourseSelectionPanel courseSelectionPanel = new CourseSelectionPanel();

        cardPanel.add(courseSelectionPanel,BorderLayout.CENTER);

        this.add(cardPanel,BorderLayout.CENTER);
    }
}
