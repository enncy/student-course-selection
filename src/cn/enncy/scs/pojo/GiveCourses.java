package cn.enncy.scs.pojo;


import cn.enncy.scs.pojo.annotation.ForeignInfo;
import cn.enncy.scs.pojo.annotation.Info;
import cn.enncy.scs.service.CourseService;
import cn.enncy.scs.service.TeacherService;

/**
 * //TODO   授课类
 * <br/>Created in 0:57 2021/5/30
 *
 * @author: enncy
 */
public class GiveCourses  extends BaseObject {

    @Info("课程id")
    @ForeignInfo(fieldName = "name"  ,service = CourseService.class)
    private int course_id;

    @Info("教师id")
    @ForeignInfo(fieldName = "name" ,service = TeacherService.class)
    private int teacher_id;


    @Info("课程最大人数")
    private int max_num;

    public GiveCourses(){};

    public GiveCourses(int course_id, int teacher_id, int max_num) {
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
        return "GiveCourses{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", course_id=" + course_id +
                ", teacher_id=" + teacher_id +
                ", max_num=" + max_num +
                '}';
    }

}
