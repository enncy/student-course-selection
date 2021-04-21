package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Teacher;

/**
 * //TODO
 * <br/>Created in 21:22 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "teachers",bean = Teacher.class)
public interface TeacherMapper extends BaseMapper{
    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    Teacher findOneById(int id);
}
