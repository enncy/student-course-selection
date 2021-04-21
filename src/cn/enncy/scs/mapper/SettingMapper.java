package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Setting;

/**
 * //TODO
 * <br/>Created in 21:24 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = "settings")
public interface SettingMapper extends BaseMapper{
    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    Setting findOneById(int id);
}
