package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:12 2021/4/18
 *
 * @author: enncy
 */
public class Class extends BaseObject {

    String name;
    int major_id;

    public Class(String name, int major_id) {
        this.name = name;
        this.major_id = major_id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", major_id=" + major_id +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
