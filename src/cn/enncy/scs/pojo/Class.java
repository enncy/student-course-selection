package cn.enncy.scs.pojo;


import cn.enncy.scs.service.MajorService;

/**
 * //TODO
 * <br/>Created in 21:12 2021/4/18
 *
 * @author: enncy
 */
public class Class extends BaseObject {

    @Info("班级名")
    private String name;

    @Info("专业id")
    @ForeignInfo(pojo = Major.class,fieldName = "id",service = MajorService.class)
    private int major_id;

    public Class() {}

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
