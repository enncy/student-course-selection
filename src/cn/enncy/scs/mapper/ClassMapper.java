package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Class;

/**
 * //TODO
 * <br/>Created in 21:23 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "classes")
public interface ClassMapper extends BaseMapper{
    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    Class findOneById(int id);
}
