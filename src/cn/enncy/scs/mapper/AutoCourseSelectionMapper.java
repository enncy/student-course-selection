package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 21:26 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "auto_courses_selections")
public interface AutoCourseSelectionMapper extends BaseMapper{
    @Override
    int insert(BaseObject manager);

    @Override
    int update(BaseObject manager);

    @Override
    int deleteById(int id);

    @Override
    Object findOneById(int id);
}
