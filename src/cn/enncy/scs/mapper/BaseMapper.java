package cn.enncy.scs.mapper;


import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO 基础通用 mapper
 * <br/>Created in 20:30 2021/4/18
 *
 * @author: enncy
 */
public interface BaseMapper{
    String TABLE_NAME = SqlConstant.TABLE_NAME;
    String KEY_ARRAY = SqlConstant.KEY_ARRAY;
    String VALUE_ARRAY = SqlConstant.VALUE_ARRAY;
    int insert( BaseObject manager);
    int update(BaseObject manager);
    int deleteById(int id);
    Object findOneById(int id);

}
