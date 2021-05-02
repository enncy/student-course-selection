package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:18 2021/4/18
 *
 * @author: enncy
 */
public class AutoCourseSelection extends BaseObject {

    private int student_id;
    private int course_id;

    public AutoCourseSelection() { }

    public AutoCourseSelection(int student_id, int course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "AutoCourseSelection{" +
                "student_id=" + student_id +
                ", course_id=" + course_id +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
