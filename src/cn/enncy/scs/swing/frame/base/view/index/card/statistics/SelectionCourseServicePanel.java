package cn.enncy.scs.swing.frame.base.view.index.card.statistics;


import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.pojo.*;
import cn.enncy.scs.service.*;
import cn.enncy.scs.swing.component.table.ScsSelectionResultTableCellEditor;
import cn.enncy.scs.swing.component.table.ScsTable;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.base.view.index.card.component.PagingManagerPanel;

import javax.swing.*;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:51 2021/6/2
 *
 * @author: enncy
 */
public class SelectionCourseServicePanel extends PagingManagerPanel {

    public static GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
    public static TeacherService teacherService = ServiceFactory.getService(TeacherService.class);
    public static CourseService courseService = ServiceFactory.getService(CourseService.class);
    public static StudentService studentService = ServiceFactory.getService(StudentService.class);


    public SelectionCourseServicePanel(Class<? extends BaseObject> baseObjectClass, BaseService baseService) {
        super(baseObjectClass, baseService);
    }


    //重装分页数据获取方法
    @Override
    public List<BaseObject> createDataList(int skip, int limit) {
        System.out.println("createDataList:" + skip + ":" + limit);
        if (LoginFrame.student != null) {
            Student student = LoginFrame.student;
            CourseSelectionService courseSelectionService = (CourseSelectionService) getBaseService();
            List<BaseObject> byStudentId = courseSelectionService.findByStudentId(student.getId());
            if ((skip + limit) > byStudentId.size()) {
                byStudentId = byStudentId.subList(skip, byStudentId.size());
            } else {
                byStudentId = byStudentId.subList(skip, (skip + limit));
            }

            return byStudentId;
        } else {
            return super.createDataList(skip, limit);
        }
    }


    //重载创建组件方法
    @Override
    public List<BaseObject> createDataList() {
        //如果未初始化则传入初始化数据，否则直接返回分页数据
        if (getDataList() != null) {
            return createDataList(getDataCount() * getCurrentPage(), getDataCount());
        } else {
            CourseSelectionService courseSelectionService = (CourseSelectionService) getBaseService();
            if (LoginFrame.student != null) {
                Student student = LoginFrame.student;
                return courseSelectionService.findByStudentId(student.getId());
            } else {
                return courseSelectionService.findAll();
            }
        }
    }

    @Override
    public void initHeadPanel(JPanel headerPanel) {

    }

    @Override
    public JTable createTable() {
        List<BaseObject> baseObjects = getDataList();
        Object[] column = new Object[]{"学生名", "课程名", "教师名", "已选人数/最大人数", "选课时间", "操作"};
        Object[][] row = new Object[baseObjects.size()][column.length];

        List<Infos> infosList = new ArrayList<>();

        for (int i = 0; i < baseObjects.size(); i++) {
            CourseSelection courseSelection = (CourseSelection) baseObjects.get(i);
            GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(courseSelection.getGive_courses_id());
            Student student = (Student) studentService.findOneById(courseSelection.getStudent_id());
            Teacher teacher = (Teacher) teacherService.findOneById(giveCourses.getTeacher_id());
            Course course = (Course) courseService.findOneById(giveCourses.getCourse_id());

            CourseSelectionService courseSelectionService = (CourseSelectionService) getBaseService();

            int selectionNum = courseSelectionService.findByGiveCoursesId(giveCourses.getId()).size();
            row[i][0] = student.getName();
            row[i][1] = course.getName();
            row[i][2] = teacher.getName();
            row[i][3] = selectionNum + "/" + giveCourses.getMax_num();
            row[i][4] = courseSelection.getFormatCreateTime();
            row[i][5] = "null";

            //信息弹窗
            Infos infos = new Infos(courseSelection,giveCourses,course, teacher, student);
            infosList.add(infos);
        }

        ScsTable scsTable = new ScsTable(row, column);
        ScsSelectionResultTableCellEditor scsSelectionResultTableCellEditor = new ScsSelectionResultTableCellEditor();
        scsTable.setTableCellEditor(scsSelectionResultTableCellEditor);

        JButton showInfoButton = scsSelectionResultTableCellEditor.getShowInfoButton();

        showInfoButton.addActionListener(e -> {
            int selectedRow = scsSelectionResultTableCellEditor.getSelectedRow();
            if (selectedRow != -1) {
                Infos infos = infosList.get(selectedRow);
                SelectionInfoDialog selectionInfoDialog = new SelectionInfoDialog(infos.course, infos.teacher, infos.student);
                selectionInfoDialog.setVisible(true);
            }
        });

        JButton cancelSelectionButton = scsSelectionResultTableCellEditor.getCancelSelectionButton();

        cancelSelectionButton.addActionListener(e->{
            int selectedRow = scsSelectionResultTableCellEditor.getSelectedRow();
            if (selectedRow != -1) {

                CancelSelectionService cancelSelectionService = ServiceFactory.getService(CancelSelectionService.class);
                Infos infos = infosList.get(selectedRow);
                CancelSelection cancelSelection = new CancelSelection(infos.student.getId(),infos.giveCourses.getId());
                cancelSelectionService.insert(cancelSelection);
            }
        });

        return scsTable;
    }

    @Override
    public void updateDataList() {
        setDataList(createDataList());
        updateTablePanel();
    }
}

//弹窗数据类
class Infos {
    public CourseSelection courseSelection;
    public GiveCourses giveCourses;
    public Course course;
    public Teacher teacher;
    public Student student;

    public Infos(CourseSelection courseSelection, GiveCourses giveCourses, Course course, Teacher teacher, Student student) {
        this.courseSelection = courseSelection;
        this.giveCourses = giveCourses;
        this.course = course;
        this.teacher = teacher;
        this.student = student;
    }
}
