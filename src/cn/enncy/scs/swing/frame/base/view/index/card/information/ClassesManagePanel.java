package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Class;
import cn.enncy.scs.service.ClassService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 13:52 2021/4/26
 *
 * @author: enncy
 */
public class ClassesManagePanel  extends PagingManagerPanel {


    public ClassesManagePanel( ) {
        super(Class.class, new ClassService());
    }
}
