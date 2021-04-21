package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Body;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO 基础通用 mapper
 * <br/>Created in 20:30 2021/4/18
 *
 * @author: enncy
 */


public interface BaseMapper{
    String TABLE_NAME = SqlConstant.TABLE_NAME;
    String KEY_ARRAY = SqlConstant.KEY_ARRAY;
    String VALUE_ARRAY = SqlConstant.VALUE_ARRAY;
    String SET_ARRAY = SqlConstant.SET_ARRAY;

    @SQL("INSERT IGNORE INTO #{TABLE_NAME}(#{"+KEY_ARRAY+"}) value(#{"+VALUE_ARRAY+"});")
    int insert(@Body() BaseObject baseObject);

    @SQL("DELETE FROM #{TABLE_NAME} WHERE id=#{id};")
    int deleteById(@Param("id") int id);

    @SQL("SELECT * FROM #{TABLE_NAME} WHERE id=#{id}")
    BaseObject findOneById(@Param("id") int id);

    @SQL("UPDATE TABLE #{TABLE_NAME} SET #{"+SET_ARRAY+"} WHERE id=#{id};")
    int update(@Body() BaseObject baseObject);

}
