package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:16 2021/4/18
 *
 * @author: enncy
 */
public class Setting extends BaseObject{

    @Info("键")
    private String name;
    @Info("值")
    private String value;
    @Info("设置描述")
    private String description;

    public Setting() { }


    public Setting(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
