package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CancelSelection;
import cn.enncy.scs.swing.constant.ScsTableName;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 21:25 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = ScsTableName.CANCEL_SELECTIONS,resultType = CancelSelection.class)
public interface CancelSelectionMapper extends  SelectionMapper{

    @Override
    @SQL(  "SELECT * FROM #{TABLE_NAME};" )
    List<BaseObject> findAll();

    @SQL(  "SELECT * FROM #{TABLE_NAME} WHERE student_id=#{student_id} AND give_course_id=#{give_courses_id};" )
    int cancelSelection(int student_id,int give_courses_id);

}
