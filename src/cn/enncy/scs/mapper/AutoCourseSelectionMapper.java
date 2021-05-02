package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.view.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:26 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.AUTO_COURSES_SELECTIONS,resultType = AutoCourseSelectionMapper.class)
public interface AutoCourseSelectionMapper extends BaseMapper{
    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();
}
