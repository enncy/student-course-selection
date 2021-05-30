package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.GiveCourses;
import cn.enncy.scs.swing.constant.ScsTableName;

/**
 * //TODO
 * <br/>Created in 1:55 2021/5/30
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.GIVE_COURSES,resultType = GiveCourses.class)
public interface GiveCoursesMapper extends BaseMapper{


}
