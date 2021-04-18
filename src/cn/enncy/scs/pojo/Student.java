package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 20:14 2021/4/18
 *
 * @author: enncy
 */
public class Student extends BaseObject {

    private String name;
    private String number;
    private String pwd;
    private int class_id;

    public Student() {  }

    public Student(String name, String number, String pwd, int class_id) {
        this.name = name;
        this.number = number;
        this.pwd = pwd;
        this.class_id = class_id;
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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", pwd='" + pwd + '\'' +
                ", class_id=" + class_id +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
