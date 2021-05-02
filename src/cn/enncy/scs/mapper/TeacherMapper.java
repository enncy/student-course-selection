package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Teacher;
import cn.enncy.scs.view.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:22 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.TEACHERS,resultType = Teacher.class)
public interface TeacherMapper extends BaseMapper{
    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();
}
