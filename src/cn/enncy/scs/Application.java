package cn.enncy.scs;


import cn.enncy.mybatis.SqlSession;
import cn.enncy.scs.view.frame.MainFrame;

/**
 * //TODO
 * <br/>Created in 14:16 2021/4/14
 *
 * @author: enncy
 */
public class Application {

    public static void main(String[] args) {
        //初始化数据库
        SqlSession.initSql("cn.enncy.scs.mapper");
        //初始化界面
        MainFrame.init();

    }

}

