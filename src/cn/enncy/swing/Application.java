package cn.enncy.swing;


import cn.enncy.swing.frame.MainFrame;
import cn.enncy.swing.mapper.ManagerMapper;
import cn.enncy.swing.pojo.Manager;
import cn.enncy.swing.service.ManagerService;
import cn.enncy.swing.utils.database.SqlSession;
import cn.enncy.swing.utils.scan.AnnotationScanner;

/**
 * //TODO
 * <br/>Created in 14:16 2021/4/14
 *
 * @author: enncy
 */
public class Application {

    public static void main(String[] args) {
//        MainFrame.init();
        AnnotationScanner.init("cn.enncy.swing.service");

        ManagerMapper managerMapper = new ManagerService();
        Manager manager = new Manager("言小溪","enncy","132525");
        System.out.println(managerMapper.insert(manager));


//        Manager manager =  managerMapper.findByAccount("enncy") ;
//        System.out.println(managerMapper.deleteByUid(manager.getUid()));
    }

}
