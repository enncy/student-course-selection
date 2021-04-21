package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.annotation.Param;
import cn.enncy.mybatis.annotation.SQL;
import cn.enncy.scs.pojo.AutoCourseSelection;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 21:26 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "auto_courses_selections")
public interface AutoCourseSelectionMapper extends BaseMapper{
    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    @SQL("SELECT * FROM #{TABLE_NAME} WHERE id=#{id};")
    AutoCourseSelection findOneById(@Param("id") int id);
}
