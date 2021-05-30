package cn.enncy.scs.swing.frame.base.view.index.card.information;


import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.service.StudentService;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

/**
 * //TODO
 * <br/>Created in 13:53 2021/4/26
 *
 * @author: enncy
 */
public class StudentsManagePanel extends PagingManagerPanel {


    public StudentsManagePanel( ) {
        super(Student.class,  ServiceFactory.getService(StudentService.class)  );
    }
}
