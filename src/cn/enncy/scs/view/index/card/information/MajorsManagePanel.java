package cn.enncy.scs.view.index.card.information;


import cn.enncy.scs.pojo.Major;
import cn.enncy.scs.service.MajorService;
import cn.enncy.scs.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 13:52 2021/4/26
 *
 * @author: enncy
 */
public class MajorsManagePanel extends ManagePanel {



    public MajorsManagePanel() {
        super(Major.class,new MajorService());

    }
}
