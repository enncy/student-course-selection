package cn.enncy.scs.swing.frame.base.view.index.card.courses;


import cn.enncy.scs.swing.component.panel.CardPanel;
import cn.enncy.scs.swing.component.panel.ScsGrayPanel;
import cn.enncy.scs.swing.component.scroll.ScsScrollPanel;

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

        CardPanel cardPanel = new CardPanel("可选课程",1f);

        OptionalCoursePanel optionalCoursePanel = new OptionalCoursePanel();
        ScsScrollPanel scsScrollPanel = new ScsScrollPanel(optionalCoursePanel);
        cardPanel.add(scsScrollPanel,BorderLayout.CENTER);

        this.add(cardPanel,BorderLayout.CENTER);
    }
}
