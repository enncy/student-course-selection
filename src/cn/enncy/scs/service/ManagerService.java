package cn.enncy.scs.service;


import cn.enncy.scs.mapper.ManagerMapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Manager;
import cn.enncy.mybatis.SqlSession;

import java.util.List;


/**
 * //TODO
 * <br/>Created in 14:31 2021/4/14
 *
 * @author: enncy
 */

public class ManagerService  implements ManagerMapper {
    public ManagerMapper  managerMapper = SqlSession.getMapper(ManagerMapper.class);

    @Override
    public int insert(BaseObject manager) {
        return managerMapper.insert(manager);
    }

    @Override
    public int update(BaseObject manager) {
        return managerMapper.update(manager);
    }

    @Override
    public int deleteById(int id) {
        return managerMapper.deleteById(id);
    }

    @Override
    public Manager findOneById(int id) {
        return managerMapper.findOneById(id);
    }


    @Override
    public Manager findByAccount(String account) {
        return managerMapper.findByAccount(account);
    }

    @Override
    public List<Manager> findByName(String name) {
        return managerMapper.findByName(name);
    }
}
