package cn.enncy.scs.service;


import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.mapper.CourseSelectionMapper;
import cn.enncy.scs.pojo.AutoCourseSelection;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.pojo.GiveCourses;
import cn.enncy.scs.swing.component.dialog.ScsAlert;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 10:13 2021/4/19
 *
 * @author: enncy
 */
public class CourseSelectionService extends SelectionService implements CourseSelectionMapper {


    public CourseSelectionService() {
        super(CourseSelectionMapper.class);
    }


    @Override
    public int insert(BaseObject baseObject) {
        CourseSelection courseSelection = (CourseSelection) baseObject;
        //通过 id 查找到授课信息，然后通过数量判断是否满人
        GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
        GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(courseSelection.getGive_courses_id());
        int res;

        //课程是否可选
        if(isNotOptional(giveCourses.getId())){
            res = -1;
            ScsAlert.warining("课程不能选择!");
        }
        //不在选课期间
        else if (!isInSelectionTime()) {
            res = -1;
            ScsAlert.warining("选课暂未开放!");
        }
        ///课程已经满人
        else if (isGiveCourseFull(giveCourses)) {
            res = -1;
            ScsAlert.warining("课程已经满人!");
        }
        //超出选课次数
        else if (noSelectionTimes(courseSelection.getStudent_id())) {
            res = -1;
            ScsAlert.warining("超出选课次数!");
        } else {
            res = super.insert(baseObject);
            ScsAlert.autoAlert(res, "选课失败！", "你已经选过这门课了", "选课成功");
        }
        return res;
    }


    @Override
    public int deleteById(int id) {
        int i = super.deleteById(id);
        //如果删除成功，则执行自动选课
        if(i>0){
            AutoCourseSelectionService autoCourseSelectionService = ServiceFactory.getService(AutoCourseSelectionService.class);
            List<BaseObject> autoCourseSelections = autoCourseSelectionService.orderByUpdateTime();
            for (BaseObject baseObject : autoCourseSelections) {
                AutoCourseSelection autoCourseSelection = (AutoCourseSelection) baseObject;
                CourseSelection courseSelection = new CourseSelection(autoCourseSelection.getStudent_id(),autoCourseSelection.getGive_courses_id());

                int insert = insert(courseSelection);
                if(insert>0){
                    System.out.println("自动选课成功! : "+autoCourseSelection);
                    //删除自动选课记录
                    autoCourseSelectionService.deleteById(autoCourseSelection.getId());
                }else{
                    System.err.println("自动选课失败! : "+autoCourseSelection);
                }

            }
        }

        return i;
    }
}
