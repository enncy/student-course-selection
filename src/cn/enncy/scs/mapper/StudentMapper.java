package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Body;
import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 20:30 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = "students")
public interface StudentMapper extends BaseMapper{

    @Override
    @SQL("INSERT IGNORE INTO #{TABLE_NAME}(#{"+KEY_ARRAY+"}) value(#{"+VALUE_ARRAY+"});")
    int insert(@Body()BaseObject baseObject);

    @Override
    @SQL("UPDATE TABLE #{TABLE_NAME} SET name=#{name} ,pwd=#{pwd},class_id=#{class_id} WHERE id=#{id};")
    int update(@Body()BaseObject baseObject);

    @Override
    @SQL("DELETE FROM #{TABLE_NAME} WHERE id=#{id};")
    int deleteById(@Param("id") int id);

    @Override
    @SQL("SELECT * FROM #{TABLE_NAME} WHERE id=#{id};")
    BaseObject findOneById(@Param("id") int id);
}
