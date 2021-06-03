package cn.enncy.scs.pojo;


import cn.enncy.scs.pojo.annotation.ForeignInfo;
import cn.enncy.scs.pojo.annotation.Info;
import cn.enncy.scs.service.GiveCoursesService;

/**
 * //TODO
 * <br/>Created in 21:13 2021/4/18
 *
 * @author: enncy
 */
public class OptionalCourse  extends BaseObject{

    @Info("授课id")
    @ForeignInfo(fieldName = "course_id,teacher_id"  ,service = GiveCoursesService.class)
    private int give_courses_id;

    public OptionalCourse() { }

    public OptionalCourse(int give_courses_id) {
        this.give_courses_id = give_courses_id;
    }

    public int getGive_courses_id() {
        return give_courses_id;
    }

    public void setGive_courses_id(int give_courses_id) {
        this.give_courses_id = give_courses_id;
    }

    @Override
    public String toString() {
        return "OptionalCourse{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", give_courses_id=" + give_courses_id +
                '}';
    }

}
