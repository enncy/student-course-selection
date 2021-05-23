package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.service.StudentService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ManagePanel;

/**
 * //TODO
 * <br/>Created in 13:53 2021/4/26
 *
 * @author: enncy
 */
public class StudentsManagePanel extends ManagePanel {


    public StudentsManagePanel( ) {
        super(Student.class, new StudentService());
    }
}
