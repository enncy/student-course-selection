package cn.enncy.scs.service;


import cn.enncy.scs.mapper.SettingMapper;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 10:11 2021/4/19
 *
 * @author: enncy
 */
public class SettingService extends  BaseService implements SettingMapper{

    SettingMapper settingMapper = (SettingMapper) baseMapper;

    public SettingService( ) {
        super(SettingMapper.class);
    }

    @Override
    public BaseObject findByName(String name) {
        return settingMapper.findByName(name);
    }
}
