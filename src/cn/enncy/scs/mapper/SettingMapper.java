package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:24 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = ScsTableName.SETTINGS,resultType = Setting.class)
public interface SettingMapper extends BaseMapper{
    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();

    @SQL( "SELECT * FROM #{TABLE_NAME} WHERE name='#{name}';")
    BaseObject findByName(@Param("name") String name);
}
