package cn.enncy.scs.mapper;

import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:25 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.COURSES_SELECTIONS, resultType = CourseSelection.class)
public interface CourseSelectionMapper extends SelectionMapper {
    @Override
    @SQL("SELECT * FROM #{TABLE_NAME};")
    List<BaseObject> findAll();


}
