package cn.enncy.swing.mapper;


import cn.enncy.swing.pojo.Manager;
import cn.enncy.swing.utils.database.annotation.*;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 22:48 2021/4/14
 *
 * @author: enncy
 */


@Mapper(tableName = "manager")
public interface ManagerMapper {


    @SQL("INSERT INTO #{table_name}(id,uid,name,account,pwd,create_time) value(null,#{uid},#{name},#{account},#{pwd},#{create_time});")
    int insert(@Body() Manager manager);

    @SQL("UPDATE TABLE #{table_name} SET name=#{name} ,account=#{account},pwd=#{pwd} WHERE uid=#{uid};")
    int update(@Body() Manager manager);

    @SQL("DELETE FROM #{table_name} WHERE uid=#{uid}")
    int deleteByUid(@Param("uid") String uid);

    @SQL("SELECT * FROM #{table_name} WHERE uid=#{uid}")
    Manager findByUid(@Param("uid") String uid);

    @SQL("SELECT * FROM #{table_name} WHERE account=#{account}")
    Manager findByAccount(@Param("account") String account);

    @SQL(value = "SELECT * FROM #{table_name} WHERE name=#{name}",resultType = Manager.class)
    List<Manager> findByName(@Param("name") String name);

}
