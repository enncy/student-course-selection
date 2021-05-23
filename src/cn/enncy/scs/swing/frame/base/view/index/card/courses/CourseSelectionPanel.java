package cn.enncy.scs.swing.frame.base.view.index.card.courses;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Course;
import cn.enncy.scs.pojo.OptionalCourse;
import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.service.CourseService;
import cn.enncy.scs.service.OptionalCourseService;
import cn.enncy.scs.service.TeacherService;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.component.table.ScsTable;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 17:37 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class CourseSelectionPanel extends ScsWhitePanel {

    public CourseSelectionPanel()  {
        OptionalCourseService service = new OptionalCourseService();
        CourseService courseService = new CourseService();
        TeacherService teacherService = new TeacherService();


        List<BaseObject> all = service.findAll();
        int fieldsLength = ReflectUtils.getObjectFields(OptionalCourse.class).length;
        Object[][] data = new Object[all.size()][fieldsLength];
        Object[] column = new Object[]{"id","课程名","教师名","课程最大人数","创建时间","更新时间"};


        for (int i = 0; i < all.size(); i++) {
            BaseObject baseObject = all.get(i);


            Field[] declaredFields = ReflectUtils.getObjectFields(baseObject.getClass()) ;

            for (int j = 0; j < fieldsLength; j++) {
                if(!declaredFields[j].isAccessible()){
                    declaredFields[j].setAccessible(true);
                }
                try {
                    if("course_id".equals(declaredFields[j].getName())){
                        int id = (int) declaredFields[j].get(baseObject);
                        System.out.println(courseService.findOneById(id));
                        data[i][j] = ((Course)courseService.findOneById(id)).getName();
                    }else if("teacher_id".equals(declaredFields[j].getName())){
                        int id = (int) declaredFields[j].get(baseObject);
                        data[i][j] = ((Teacher)teacherService.findOneById(id)).getName();
                    }else{
                        data[i][j] = declaredFields[j].get(baseObject);
                    }

                } catch (Throwable e) {
                    try {
                        data[i][j] = declaredFields[j].get(baseObject);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    e.printStackTrace();
                }

            }
        }


        ScsTable scsTable = new ScsTable(data, column);

        this.setLayout(new BorderLayout());
        this.add(scsTable.getTableHeader(),BorderLayout.NORTH);
        this.add(scsTable,BorderLayout.CENTER);
        this.setBorder(new EmptyBorder(10,10,10,10));
    }
}
