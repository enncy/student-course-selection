package cn.enncy.scs.swing.frame.base.view.index.card.courses;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.pojo.*;
import cn.enncy.scs.pojo.annotation.ForeignInfo;
import cn.enncy.scs.pojo.annotation.TimeFormat;
import cn.enncy.scs.service.*;
import cn.enncy.scs.swing.component.table.ScsCourseTableCellEditor;
import cn.enncy.scs.swing.component.table.ScsTable;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 17:37 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class OptionalCoursePanel extends ServiceComponent {

    //获取选课业务
    private static final CourseSelectionService courseSelectionService = ServiceFactory.getService(CourseSelectionService.class);
    //获取授课业务
    private static final GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
    //获取课程业务
    private static final CourseService courseService = ServiceFactory.getService(CourseService.class);
    // 教师业务
    private static final TeacherService teacherService = ServiceFactory.getService(TeacherService.class);

    public OptionalCoursePanel() {
        super(OptionalCourse.class, ServiceFactory.getService(OptionalCourseService.class));

    }


    @Override
    public List<BaseObject> createDataList() {
        return getBaseService().findAll();
    }


    @Override
    public void initHeadPanel(JPanel headerPanel) {

    }

    @Override
    public JTable createTable() {

        List<BaseObject> all = getBaseService().findAll();
        int fieldsLength = ReflectUtils.getObjectFields(GiveCourses.class).length + 1;
        Object[][] data = new Object[all.size()][fieldsLength];
        Object[] column = new Object[]{"id", "课程名", "教师名", "课程最大人数", "创建时间", "更新时间", "操作"};


        for (int i = 0; i < all.size(); i++) {
            OptionalCourse optionalCourse = (OptionalCourse) all.get(i);
            GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
            GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(optionalCourse.getGive_courses_id());


            Field[] declaredFields = ReflectUtils.getObjectFields(giveCourses.getClass());

            for (int j = 0; j < fieldsLength; j++) {
                if (j == fieldsLength - 1) {
                    continue;
                }
                ReflectUtils.accessible(declaredFields[j]);
                try {
                    //如果有外键注解，则获取外键的名字信息，并展示
                    if (declaredFields[j].isAnnotationPresent(ForeignInfo.class)) {
                        //显示
                        data[i][j] = BaseObjectUtils.getForeignInfo(giveCourses, declaredFields[j]);
                    } else {
                        data[i][j] = declaredFields[j].get(giveCourses);
                    }
                    if (declaredFields[j].isAnnotationPresent(TimeFormat.class)) {
                        data[i][j] = BaseObjectUtils.getFormatTime((Long) data[i][j]);
                    }
                } catch (Throwable e) {
                    try {
                        data[i][j] = declaredFields[j].get(giveCourses);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    e.printStackTrace();
                }

            }

        }

        //增加操作列
        for (Object[] datum : data) {
            List<Object> objects = new ArrayList<>(Arrays.asList(datum));
            objects.add("null");
        }

        ScsTable scsTable = new ScsTable(data, column);
        ScsCourseTableCellEditor scsCourseTableCellEditor = new ScsCourseTableCellEditor(all, this);
        scsTable.setTableCellEditor(scsCourseTableCellEditor);
        scsTable.getColumn("操作").setMinWidth(200);
        JButton showInfoButton = scsCourseTableCellEditor.getShowInfoButton();

        showInfoButton.addActionListener(e -> {
            int selectedRow = scsCourseTableCellEditor.getSelectedRow();
            //获取可选课程的对象
            OptionalCourse optionalCourse = (OptionalCourse) all.get(selectedRow);
            //可选课程 id
            int give_courses_id = optionalCourse.getGive_courses_id();
            //获取授课信息
            GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(give_courses_id);
            //获取课程
            Course course = (Course) courseService.findOneById(giveCourses.getCourse_id());
            //获取授课教师
            Teacher teacher = (Teacher) teacherService.findOneById(giveCourses.getTeacher_id());
            //显示弹窗
            new CourseInfoDialog(course, teacher);
        });

        //选课
        JButton courseSelectionButton = scsCourseTableCellEditor.getCourseSelectionButton();
        courseSelectionButton.addActionListener(e -> {
            int selectedRow = scsCourseTableCellEditor.getSelectedRow();
            if (selectedRow != -1) {

                //获取可选课程的对象
                OptionalCourse optionalCourse = (OptionalCourse) all.get(selectedRow);
                //可选课程 id
                int give_courses_id = optionalCourse.getGive_courses_id();
                //获取授课信息
                GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(give_courses_id);

                // 选课
                CourseSelection courseSelection = new CourseSelection();
                courseSelection.setGive_courses_id(giveCourses.getId());
                courseSelection.setStudent_id(LoginFrame.getStudent().getId());
                courseSelectionService.insert(courseSelection);

            }

        });
        //自动选课
        JButton autoSelectionButton = scsCourseTableCellEditor.getAutoSelectionButton();
        autoSelectionButton.addActionListener(e -> {
            int selectedRow = scsCourseTableCellEditor.getSelectedRow();
            if (selectedRow != -1) {
                AutoCourseSelectionService autoCourseSelectionService = new AutoCourseSelectionService();
                OptionalCourse optionalCourse = (OptionalCourse) all.get(selectedRow);
                //获取授课业务
                GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
                //可选课程 id
                int give_courses_id = optionalCourse.getGive_courses_id();
                //获取授课信息
                GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(give_courses_id);
                //自动选课
                 autoCourseSelectionService.insert(new AutoCourseSelection(LoginFrame.getStudent().getId(), giveCourses.getId()));

            }
        });
        return scsTable;
    }

    @Override
    public void updateDataList() {

    }

    @Override
    public void updateTablePanel() {

    }

}
