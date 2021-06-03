package cn.enncy.scs.service;


import cn.enncy.mybatis.MybatisApplication;
import cn.enncy.scs.mapper.SettingMapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Setting;

/**
 * //TODO
 * <br/>Created in 10:11 2021/4/19
 *
 * @author: enncy
 */
public class SettingService extends  BaseService implements SettingMapper{

    SettingMapper settingMapper = (SettingMapper) baseMapper;

    public static final String time_format = MybatisApplication.time_format.getValue();

    public SettingService( ) {
        super(SettingMapper.class);
    }

    @Override
    public BaseObject findByName(String name) {
        return settingMapper.findByName(name);
    }

    public String getSelectMaxNum(){
        return getSetting(MybatisApplication.select_max_num.getName());
    }

    public String getCancelMaxNum(){
        return getSetting(MybatisApplication.cancel_max_num.getName());
    }

    public String getSelectionStart(){
        return getSetting(MybatisApplication.selection_start.getName());
    }

    public String getSelectionEnd(){
        return getSetting(MybatisApplication.selection_end.getName());
    }
    public String getNotice(){
        return getSetting(MybatisApplication.notice.getName());
    }

    public String getTimeFormat(){
        return time_format;
    }

    private String  getSetting(String name){
        return  ((Setting) this.settingMapper.findByName(name)).getValue();
    }



}
