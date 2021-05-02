package cn.enncy.scs.view.index.card.information;


import cn.enncy.scs.pojo.Class;
import cn.enncy.scs.service.ClassService;
import cn.enncy.scs.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 13:52 2021/4/26
 *
 * @author: enncy
 */
public class ClassesManagePanel  extends ManagePanel {


    public ClassesManagePanel( ) {
        super(Class.class, new ClassService());
    }
}
