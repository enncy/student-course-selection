package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Body;
import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Student;

/**
 * //TODO
 * <br/>Created in 20:30 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = "students")
public interface StudentMapper extends BaseMapper{

    @Override
    @SQL("INSERT IGNORE INTO #{"+TABLE_NAME +"}(#{"+KEY_ARRAY+"}) value(#{"+VALUE_ARRAY+"});")
    int insert(@Body()BaseObject manager);

    @Override
    @SQL("UPDATE TABLE #{"+TABLE_NAME+"} SET name=#{name} ,pwd=#{pwd},class_id=#{class_id} WHERE id=#{id};")
    int update(@Body()BaseObject manager);

    @Override
    @SQL("DELETE FROM #{"+TABLE_NAME+"} WHERE id=#{id};")
    int deleteById(@Param("id") int id);

    @Override
    @SQL("SELECT * FROM #{"+TABLE_NAME+"} WHERE id=#{id};")
    Student findOneById(@Param("id") int id);
}
