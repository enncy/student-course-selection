package cn.enncy.scs.pojo;


import cn.enncy.scs.pojo.annotation.Account;
import cn.enncy.scs.pojo.annotation.Info;

/**
 * //TODO
 * <br/>Created in 14:34 2021/4/14
 *
 * @author: enncy
 */

public class Manager extends BaseObject implements Account {

    @Info("管理员名")
    private String name;
    @Info("账号")
    private String account;
    @Info("密码")
    private String pwd;

    public Manager() { }

    public Manager(String name, String account, String pwd ) {
        this.name = name;
        this.account = account;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
