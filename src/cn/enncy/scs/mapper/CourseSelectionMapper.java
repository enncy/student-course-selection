package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:25 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.COURSES_SELECTIONS, resultType = CourseSelection.class)
public interface CourseSelectionMapper extends BaseMapper {
    @Override
    @SQL("SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();

    /**
     * 获取选课人数
     *
     * @param give_courses_id   授课id
     * @return: int
     */
    @SQL(value = "SELECT * FROM #{TABLE_NAME} WHERE  give_courses_id=#{give_courses_id};")
    List<BaseObject> findByGiveCoursesId(@Param("give_courses_id") int give_courses_id);
}
