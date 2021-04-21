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
    @SQL("INSERT IGNORE  INTO #{TABLE_NAME}(#{"+KEY_ARRAY+"}) value(#{"+VALUE_ARRAY+"});")
    int insert(@Body() BaseObject baseObject);


    @Override
    @SQL("UPDATE TABLE #{TABLE_NAME} SET name=#{name} ,account=#{account},pwd=#{pwd} WHERE id=#{id};")
    int update(@Body() BaseObject baseObject);

    @Override
    @SQL("DELETE FROM #{TABLE_NAME} WHERE id=#{id}")
    int deleteById(@Param("id") int id);


    @Override
    @SQL("SELECT * FROM #{TABLE_NAME} WHERE id=#{id}")
    BaseObject findOneById(@Param("id") int id);

    @SQL("SELECT * FROM #{TABLE_NAME} WHERE account=#{account}")
    Manager findByAccount(@Param("account") String account);


    @SQL(value = "SELECT * FROM #{TABLE_NAME} WHERE name=#{name}",resultType = Manager.class)
    List<Manager> findByName(@Param("name") String name);

}
