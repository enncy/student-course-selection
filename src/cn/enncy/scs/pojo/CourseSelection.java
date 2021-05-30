package cn.enncy.scs.pojo;


import cn.enncy.scs.service.GiveCoursesService;
import cn.enncy.scs.service.StudentService;

/**
 * //TODO
 * <br/>Created in 21:18 2021/4/18
 *
 * @author: enncy
 */
public class CourseSelection  extends  BaseObject{

    @Info("学生id")
    @ForeignInfo(pojo = Student.class ,fieldName = "name",service = StudentService.class)
    private int student_id;

    @Info("授课id")
    @ForeignInfo(pojo = GiveCourses.class,fieldName = "id" ,service = GiveCoursesService.class)
    private int give_courses_id;

    public CourseSelection() { }

    public CourseSelection(int student_id, int give_courses_id) {
        this.student_id = student_id;
        this.give_courses_id = give_courses_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getGive_courses_id() {
        return give_courses_id;
    }

    public void setGive_courses_id(int give_courses_id) {
        this.give_courses_id = give_courses_id;
    }

    @Override
    public String toString() {
        return "CourseSelection{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", student_id=" + student_id +
                ", give_courses_id=" + give_courses_id +
                '}';
    }
}
