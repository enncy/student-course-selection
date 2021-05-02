package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 14:34 2021/4/14
 *
 * @author: enncy
 */

public class Manager extends BaseObject{

    private String name;
    private String account;
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

    public String getAccount() {
        return account;
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
