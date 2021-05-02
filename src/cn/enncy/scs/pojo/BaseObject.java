package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 20:22 2021/4/18
 *
 * @author: enncy
 */
public class BaseObject {


    public int id;

    @Info("创建时间")
    public long create_time;
    @Info("更新时间")
    public long update_time;

    public BaseObject() {
        this.create_time = System.currentTimeMillis();
        this.update_time = this.create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }


    @Override
    public String toString() {
        return "BaseObject{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
