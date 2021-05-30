package cn.enncy.scs.swing.frame.base.view.index.card.courses;


import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.*;
import cn.enncy.scs.service.*;
import cn.enncy.scs.swing.component.dialog.ScsAlert;
import cn.enncy.scs.swing.component.table.ScsCourseTableCellEditor;
import cn.enncy.scs.swing.component.table.ScsTable;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

import javax.swing.*;
import java.awt.*;
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
                        data[i][j] = getForeignInfo(giveCourses, declaredFields[j]);
                    } else {
                        data[i][j] = declaredFields[j].get(giveCourses);
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
            CourseInfoDialog courseInfoDialog = new CourseInfoDialog(course, teacher);
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

                //如果选则这个课程的人数，超过了指定的人数
                if (courseSelectionService.findByGiveCoursesId(giveCourses.getId()).size() > giveCourses.getMax_num()) {
                    ScsAlert.error("这门课已经满人！");
                } else {
                    // 选课
                    CourseSelection courseSelection = new CourseSelection();
                    courseSelection.setGive_courses_id(giveCourses.getId());
                    courseSelection.setStudent_id(LoginFrame.getStudent().getId());
                    int insert = courseSelectionService.insert(courseSelection);
                    ScsAlert.autoAlert(insert, "选课失败", "你已经选过这门课了", "选课成功");
                }

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
                int insert = autoCourseSelectionService.insert(new AutoCourseSelection(LoginFrame.getStudent().getId(), giveCourses.getId()));
                ScsAlert.autoAlert(insert, "自动选课失败", "自动选课已经生效", "自动选课成功");

            }
        });

        this.setLayout(new BorderLayout());
        this.add(scsTable.getTableHeader(), BorderLayout.NORTH);
        this.add(scsTable, BorderLayout.CENTER);

    }

    /**
     * 获取外键上的信息
     *
     * @param baseObject
     * @param field
     * @return: java.lang.Object
     */
    public Object getForeignInfo(BaseObject baseObject, Field field) throws IllegalAccessException, NoSuchFieldException {
        //获取外键注解
        ForeignInfo foreignInfo = field.getAnnotation(ForeignInfo.class);
        //通过工厂获取外键业务
        BaseService foreignService = ServiceFactory.getService(foreignInfo.service());
        //获取外键id
        int id = (int) field.get(baseObject);
        //通过外键 id 获取外键的对象
        BaseObject foreignObject = foreignService.findOneById(id);
        //获取指定的外键名
        Field declaredField = ReflectUtils.getObjectField(foreignObject.getClass(), foreignInfo.fieldName());
        assert declaredField != null;
        if (!declaredField.isAccessible()) {
            declaredField.setAccessible(true);
        }
        return declaredField.get(foreignObject);
    }

    @Override
    public void updateDataList() {

    }

    @Override
    public void updateTablePanel() {

    }
}
