package cn.enncy.scs.view.index.card.course;


import cn.enncy.scs.view.component.dialog.ScsAlert;
import cn.enncy.scs.view.component.dialog.ScsDialog;
import cn.enncy.scs.view.component.panel.ScsWhitePanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //TODO
 * <br/>Created in 14:03 2021/4/23
 *
 * @author: enncy
 */
public class CoursePanel extends ScsWhitePanel {


    public CoursePanel() {
        JButton jButton = new JButton("成功");
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScsAlert.success("成功！！！！！！！！！！！");
                super.mouseClicked(e);
            }
        });
        this.add(jButton);

        JButton jButton2 = new JButton("通知");
        jButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScsAlert.info("成功！！！！！！！！！！！");
                super.mouseClicked(e);
            }
        });
        this.add(jButton2);

        JButton jButton3 = new JButton("警告");
        jButton3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScsAlert.warining("成功！！！！！！！！！！！");
                super.mouseClicked(e);
            }
        });
        this.add(jButton3);

        JButton jButton4 = new JButton("失败");
        jButton4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScsAlert.error("成功！！！！！！！！！！！");
                super.mouseClicked(e);
            }
        });
        this.add(jButton4);

        JButton jButton5 = new JButton("普通窗口");
        jButton5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ScsDialog();
                super.mouseClicked(e);
            }
        });
        this.add(jButton5);


    }
}
