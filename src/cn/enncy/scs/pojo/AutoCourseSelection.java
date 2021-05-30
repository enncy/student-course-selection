package cn.enncy.scs.pojo;


import cn.enncy.scs.service.GiveCoursesService;

/**
 * //TODO
 * <br/>Created in 21:18 2021/4/18
 *
 * @author: enncy
 */
public class AutoCourseSelection extends BaseObject {

    @Info("学生id")
    private int student_id;

    @Info("授课id")
    @ForeignInfo(pojo = GiveCourses.class,fieldName = "id" ,service = GiveCoursesService.class)
    private int give_courses_id;

    public AutoCourseSelection() { }

    public AutoCourseSelection(int student_id, int give_courses_id) {
        this.student_id = student_id;
        this.give_courses_id = give_courses_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getGive_courses_id() {
        return give_courses_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setGive_courses_id(int give_courses_id) {
        this.give_courses_id = give_courses_id;
    }

    @Override
    public String toString() {
        return "AutoCourseSelection{" +
                "student_id=" + student_id +
                ", give_courses_id=" + give_courses_id +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
