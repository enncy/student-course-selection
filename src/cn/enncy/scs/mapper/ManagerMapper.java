package cn.enncy.scs.mapper;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Manager;
import cn.enncy.mybatis.annotation.*;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 22:48 2021/4/14
 *
 * @author: enncy
 */


@Mapper(tableName = "managers")
public interface ManagerMapper extends BaseMapper{


    @Override
    @SQL("INSERT IGNORE  INTO #{table_name}(id,name,account,pwd,create_time,update_time) value(null,#{name},#{account},#{pwd},#{create_time},#{update_time});")
    int insert(@Body() BaseObject manager);


    @Override
    @SQL("UPDATE TABLE #{table_name} SET name=#{name} ,account=#{account},pwd=#{pwd} WHERE id=#{id};")
    int update(@Body() BaseObject manager);

    @Override
    @SQL("DELETE FROM #{table_name} WHERE id=#{id}")
    int deleteById(@Param("id") int id);

    @Override
    @SQL("SELECT * FROM #{table_name} WHERE id=#{id}")
    Manager findOneById(@Param("id") int id);

    @SQL("SELECT * FROM #{table_name} WHERE account=#{account}")
    Manager findByAccount(@Param("account") String account);


    @SQL(value = "SELECT * FROM #{table_name} WHERE name=#{name}",resultType = Manager.class)
    List<Manager> findByName(@Param("name") String name);

}
