package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Major;

/**
 * //TODO
 * <br/>Created in 21:24 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "majors")
public interface MajorMapper extends BaseMapper {

    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    Major findOneById(int id);
}
