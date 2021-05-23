package cn.enncy.scs.service;


import cn.enncy.mybatis.SqlSession;
import cn.enncy.scs.mapper.BaseMapper;
import cn.enncy.scs.pojo.BaseObject;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 12:41 2021/4/19
 *
 * @author: enncy
 */
public class BaseService implements BaseMapper {

    public BaseMapper baseMapper;

    public BaseService(Class mapper){
        baseMapper = SqlSession.getMapper(mapper);
    }

    @Override
    public int insert(BaseObject baseObject) {
        return baseMapper.insert(baseObject);
    }

    @Override
    public int update(BaseObject baseObject) {
        return baseMapper.update(baseObject);
    }

    @Override
    public int deleteById(int id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public BaseObject findOneById(int id) {
        return baseMapper.findOneById(id);
    }

    @Override
    public List<BaseObject> findByPages(int skip, int size) {
        return baseMapper.findByPages(skip,size);
    }

    @Override
    public List<BaseObject> findAll() {
        return  baseMapper.findAll();
    }
}
