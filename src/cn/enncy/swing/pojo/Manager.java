package cn.enncy.swing.pojo;


import java.math.BigInteger;
import java.util.UUID;

/**
 * //TODO
 * <br/>Created in 14:34 2021/4/14
 *
 * @author: enncy
 */
public class Manager {
    private String uid;
    private int id;
    private String name;
    private String account;
    private String pwd;
    private long create_time;

    public Manager() {}

    public Manager(String name, String account, String pwd ) {
        this.uid = UUID.randomUUID().toString().replaceAll("-","");
        this.name = name;
        this.account = account;
        this.pwd = pwd;
        this.create_time = System.currentTimeMillis();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "uid='" + uid + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
