package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:07 2021/4/18
 *
 * @author: enncy
 */
public class Teacher extends BaseObject{

    String name;
    String number;
    String  pwd;
    String description;

    public Teacher(String name, String number, String pwd) {
        this.name = name;
        this.number = number;
        this.pwd = pwd;
    }

    public Teacher(String name, String number, String pwd, String description) {
        this.name = name;
        this.number = number;
        this.pwd = pwd;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", pwd='" + pwd + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
