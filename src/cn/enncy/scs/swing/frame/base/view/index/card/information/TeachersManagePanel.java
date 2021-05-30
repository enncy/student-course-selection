package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.service.TeacherService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 21:37 2021/4/30
 *
 * @author: KL-Skeleton
 */
public class TeachersManagePanel  extends PagingManagerPanel {


    public TeachersManagePanel() {
        super(Teacher.class, ServiceFactory.getService(TeacherService.class)  );
    }
}
