package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.frame.base.view.index.card.component.dialog.ManagerInsertDialog;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * //TODO
 * <br/>Created in 23:49 2021/6/2
 *
 * @author: enncy
 */
public class InsertInfomationButton extends JButton {

    public InsertInfomationButton(ServiceComponent serviceComponent) {
        super("添加信息");

        this.setForeground(NiceColors.WHITE);
        this.setFont(new Font("微软雅黑",1,12));
        this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));

        this.addActionListener(e->{
            try {
                new ManagerInsertDialog("添加信息", serviceComponent,null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
        });
    }
}
