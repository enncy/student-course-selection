package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.AutoCourseSelection;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:26 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.AUTO_COURSES_SELECTIONS,resultType = AutoCourseSelection.class)
public interface AutoCourseSelectionMapper extends SelectionMapper{
    @Override
    @SQL( "SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();

    @SQL("SELECT * FROM #{TABLE_NAME} ORDER BY update_time ASC;")
    List<BaseObject> orderByUpdateTime();
}
