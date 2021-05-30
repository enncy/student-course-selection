package cn.enncy.scs.pojo;


import cn.enncy.scs.service.CourseSelectionService;

/**
 * //TODO
 * <br/>Created in 21:19 2021/4/18
 *
 * @author: enncy
 */
public class CancelSelection  extends BaseObject{

    @Info("选课id")
    @ForeignInfo(pojo = CourseSelection.class, fieldName = "id",service = CourseSelectionService.class)
    private int selection_id;

    public CancelSelection() { }

    public CancelSelection(int selection_id) {
        this.selection_id = selection_id;
    }

    public int getSelection_id() {
        return selection_id;
    }

    public void setSelection_id(int selection_id) {
        this.selection_id = selection_id;
    }

    @Override
    public String toString() {
        return "CancelSelection{" +
                "selection_id=" + selection_id +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
