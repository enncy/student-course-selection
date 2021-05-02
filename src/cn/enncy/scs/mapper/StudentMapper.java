package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.view.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 20:30 2021/4/18
 *
 * @author: enncy
 */


@Mapper(tableName = ScsTableName.STUDENTS,resultType = Student.class)
public interface StudentMapper extends BaseMapper{

    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();


}
