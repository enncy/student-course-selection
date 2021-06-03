package cn.enncy.scs.factory;


import cn.enncy.scs.pojo.*;
import cn.enncy.scs.service.*;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;
import org.apache.log4j.Logger;

import java.lang.Class;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //TODO   业务组件工厂
 * <br/>Created in 20:47 2021/6/1
 *
 * @author: enncy
 */
public class ServiceComponentFactory {
    public static Map<Class<? extends BaseService>, List<ServiceComponent>> baseServiceMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(ServiceComponentFactory.class);

    static {
        logger.info("开始初始化业务组件");
        ServiceComponentFactory.initServiceComponent(Student.class, StudentService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(Teacher.class, TeacherService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(Major.class, MajorService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(cn.enncy.scs.pojo.Class.class, ClassService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(Course.class, CourseService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(GiveCourses.class, GiveCoursesService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(OptionalCourse.class, OptionalCourseService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(CourseSelection.class, CourseSelectionService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(AutoCourseSelection.class, AutoCourseSelectionService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(CancelSelection.class, CancelSelectionService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(Setting.class, SettingService.class, PagingManagerPanel.class);
        ServiceComponentFactory.initServiceComponent(Manager.class, ManagerService.class, PagingManagerPanel.class);
        logger.info("业务组件初始化成功！");

    }

    /**
     *  获取业务组件
     *
     * @param pojoType  业务对象类型
     * @param baseServiceType   业务类型
     * @param serviceComponentType  组件类型
     * @return: cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent
     */
    public static  List<ServiceComponent> initServiceComponent(Class<? extends BaseObject> pojoType, Class<? extends BaseService> baseServiceType, Class<? extends ServiceComponent> serviceComponentType) {


        List<ServiceComponent> target = baseServiceMap.get(baseServiceType);
        if (target == null) {
            try {
                target = new ArrayList<>();
                BaseService service = ServiceFactory.getService(baseServiceType);
                ServiceComponent serviceComponent = serviceComponentType.getConstructor(Class.class, BaseService.class).newInstance(pojoType, service);
                target.add(serviceComponent);
                baseServiceMap.put(baseServiceType, target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    public static  List<ServiceComponent> getServiceComponent(Class<? extends BaseService> baseServiceType) {
        return baseServiceMap.get(baseServiceType);
    }

    public static void addServiceComponent(Class<? extends BaseService> baseService, ServiceComponent serviceComponent){
        List<ServiceComponent> serviceComponents = baseServiceMap.get(baseService);
        serviceComponents.add(serviceComponent);
    }


}
