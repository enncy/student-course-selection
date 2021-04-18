package cn.enncy.scs.pojo;


/**
 * //TODO
 * <br/>Created in 21:28 2021/4/18
 *
 * @author: enncy
 */
public class Course extends BaseObject {

    String name;
    String type;
    int hour;
    int theory_hour;
    int experiment_hour;
    int credit;
    int semester;
    String description;

    public Course(String name, String type, int hour, int theory_hour, int experiment_hour, int credit, int semester, String description) {
        this.name = name;
        this.type = type;
        this.hour = hour;
        this.theory_hour = theory_hour;
        this.experiment_hour = experiment_hour;
        this.credit = credit;
        this.semester = semester;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getTheory_hour() {
        return theory_hour;
    }

    public void setTheory_hour(int theory_hour) {
        this.theory_hour = theory_hour;
    }

    public int getExperiment_hour() {
        return experiment_hour;
    }

    public void setExperiment_hour(int experiment_hour) {
        this.experiment_hour = experiment_hour;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hour=" + hour +
                ", theory_hour=" + theory_hour +
                ", experiment_hour=" + experiment_hour +
                ", credit=" + credit +
                ", semester=" + semester +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
