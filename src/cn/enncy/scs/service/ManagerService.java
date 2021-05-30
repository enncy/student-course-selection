package cn.enncy.scs.service;


import cn.enncy.scs.mapper.ManagerMapper;
import cn.enncy.scs.pojo.Manager;

import java.util.List;


/**
 * //TODO
 * <br/>Created in 14:31 2021/4/14
 *
 * @author: enncy
 */

public class ManagerService extends BaseService implements ManagerMapper {

    private ManagerMapper managerMapper = (ManagerMapper) baseMapper;

    public ManagerService( ) {
        super(ManagerMapper.class);
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
