package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 15:23 2021/6/1
 *
 * @author: enncy
 */
public interface SelectionMapper extends  BaseMapper{

    /**
     * 获取选课人数
     *
     * @param give_courses_id   授课id
     * @return: int
     */
    @SQL(value = "SELECT * FROM #{TABLE_NAME} WHERE  give_courses_id=#{give_courses_id};")
    List<BaseObject> findByGiveCoursesId(@Param("give_courses_id") int give_courses_id);

    @SQL(value = "SELECT * FROM #{TABLE_NAME} WHERE  student_id=#{student_id};")
    List<BaseObject> findByStudentId(@Param("student_id") int student_id);


    @SQL("SELECT * FROM #{TABLE_NAME} WHERE give_courses_id=#{give_courses_id} AND student_id=#{student_id};")
    BaseObject findByStudentIdAndGiveCoursesId(@Param("student_id") int student_id,@Param("give_courses_id") int give_courses_id);
}
