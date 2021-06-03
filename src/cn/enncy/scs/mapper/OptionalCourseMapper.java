package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.OptionalCourse;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:26 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.OPTIONAL_COURSES,resultType = OptionalCourse.class)
public interface OptionalCourseMapper extends BaseMapper{
    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();

    @SQL(value = "SELECT * FROM #{TABLE_NAME} WHERE  give_courses_id=#{give_courses_id};")
    BaseObject findByGiveCoursesId(@Param("give_courses_id") int give_courses_id);
}
