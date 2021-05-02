package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Manager;
import cn.enncy.scs.view.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 22:48 2021/4/14
 *
 * @author: enncy
 */


@Mapper(tableName = ScsTableName.MANAGERS,resultType = Manager.class)
public interface ManagerMapper extends BaseMapper{



    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();

    @SQL("SELECT * FROM #{TABLE_NAME} WHERE account=#{account}")
    Manager findByAccount(@Param("account") String account);


    @SQL( "SELECT * FROM #{TABLE_NAME} WHERE name=#{name}")
    List<Manager> findByName(@Param("name") String name);

}
