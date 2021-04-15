package cn.enncy.swing.service;


import cn.enncy.swing.mapper.ManagerMapper;
import cn.enncy.swing.pojo.Manager;
import cn.enncy.swing.utils.database.SqlSession;

import java.util.List;


/**
 * //TODO
 * <br/>Created in 14:31 2021/4/14
 *
 * @author: enncy
 */

public class ManagerService implements ManagerMapper {
    public ManagerMapper  managerMapper = SqlSession.getMapper(ManagerMapper.class);



    @Override
    public int insert(Manager manager) {
        return managerMapper.insert(manager);
    }

    @Override
    public int update(Manager manager) {
        return managerMapper.update(manager);
    }

    @Override
    public int deleteByUid(String uid) {
        return managerMapper.deleteByUid(uid);
    }

    @Override
    public Manager findByUid(String uid) {
        return managerMapper.findByUid(uid);
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
