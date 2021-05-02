package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:10 2021/4/18
 *
 * @author: enncy
 */
public class Major extends  BaseObject{

    @Info("专业名")
    private String name;

    @Info("专业描述")
    private String description;

    public Major() { }

    public Major(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Major{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
