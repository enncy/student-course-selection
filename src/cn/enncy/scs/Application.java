package cn.enncy.scs;


import cn.enncy.mybatis.MybatisApplication;
import cn.enncy.scs.swing.frame.LoginFrame;
import cn.enncy.scs.swing.frame.MainFrame;

/**
 * //TODO
 * <br/>Created in 14:16 2021/4/14
 *
 * @author: enncy
 */
public class Application {


    public static void main(String[] args) {
        //初始化数据库
        MybatisApplication.initSql("cn.enncy.scs.mapper");
        //初始化设置
        MybatisApplication.initSettings();

        //初始化主题
        MainFrame.initTheme();
        //登录界面
        LoginFrame.init();


    }


}

