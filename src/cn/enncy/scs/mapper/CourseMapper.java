package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Course;

/**
 * //TODO
 * <br/>Created in 21:30 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = "courses")
public interface CourseMapper  extends  BaseMapper{
    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    Course findOneById(int id);
}
