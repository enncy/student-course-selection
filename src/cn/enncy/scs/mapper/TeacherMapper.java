package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 21:22 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "teachers")
public interface TeacherMapper extends BaseMapper{
    @Override
    int insert(BaseObject manager);

    @Override
    int update(BaseObject manager);

    @Override
    int deleteById(int id);

    @Override
    Object findOneById(int id);
}
