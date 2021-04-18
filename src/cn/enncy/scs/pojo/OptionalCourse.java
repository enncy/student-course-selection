package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:13 2021/4/18
 *
 * @author: enncy
 */
public class OptionalCourse  extends BaseObject {

    int course_id;
    int teacher_id;
    int max_num;

    public OptionalCourse(int course_id, int teacher_id, int max_num) {
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        this.max_num = max_num;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    @Override
    public String toString() {
        return "OptionalCourse{" +
                "course_id=" + course_id +
                ", teacher_id=" + teacher_id +
                ", max_num=" + max_num +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
