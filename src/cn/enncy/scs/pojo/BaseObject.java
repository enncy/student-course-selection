package cn.enncy.scs.pojo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    public String getFormatCreateTime(){
        return SimpleDateFormat.getInstance().format(new Date(create_time));
    }
    public String getFormatUpdateTime(){

        return SimpleDateFormat.getInstance().format(new Date(update_time));
    }

    @Override
    public String toString() {
        return "BaseObject{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseObject object = (BaseObject) o;
        return id == object.id && create_time == object.create_time && update_time == object.update_time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, create_time, update_time);
    }
}
