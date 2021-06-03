package cn.enncy.scs.pojo;


import cn.enncy.scs.pojo.annotation.Info;

/**
 * //TODO
 * <br/>Created in 21:07 2021/4/18
 *
 * @author: enncy
 */
public class Teacher extends BaseObject  {
    @Info("教师名")
    private String name;

    @Info("教师编号")
    private String number;
    @Info("教师简介")
    private String description;

    public Teacher() { }

    public Teacher(String name, String number, String description) {
        this.name = name;
        this.number = number;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
