package cn.enncy.scs.mapper;


import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CancelSelection;

/**
 * //TODO
 * <br/>Created in 21:25 2021/4/18
 *
 * @author: enncy
 */

@Mapper(tableName = "cancel_selections")
public interface CancelSelectionMapper extends  BaseMapper{

    @Override
    int insert(BaseObject baseObject);

    @Override
    int update(BaseObject baseObject);

    @Override
    int deleteById(int id);

    @Override
    CancelSelection findOneById(int id);
}
