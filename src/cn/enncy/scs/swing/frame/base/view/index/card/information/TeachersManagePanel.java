package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.service.TeacherService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 21:37 2021/4/30
 *
 * @author: KL-Skeleton
 */
public class TeachersManagePanel  extends ManagePanel {


    public TeachersManagePanel() {
        super(Teacher.class, new TeacherService());
    }
}
