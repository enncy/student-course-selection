package cn.enncy.scs.view.index.card.information;


import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.service.StudentService;
import cn.enncy.scs.view.index.card.component.ManagePanel;

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
