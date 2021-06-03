package cn.enncy.scs.service;


import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.mapper.AutoCourseSelectionMapper;
import cn.enncy.scs.pojo.AutoCourseSelection;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.CourseSelection;
import cn.enncy.scs.pojo.GiveCourses;
import cn.enncy.scs.swing.component.dialog.ScsAlert;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 10:14 2021/4/19
 *
 * @author: enncy
 */
public class AutoCourseSelectionService extends SelectionService implements AutoCourseSelectionMapper {

    AutoCourseSelectionMapper autoCourseSelectionMapper = (AutoCourseSelectionMapper) baseMapper;

    public AutoCourseSelectionService() {
        super(AutoCourseSelectionMapper.class);
    }

    @Override
    public int insert(BaseObject baseObject) {
        AutoCourseSelection autoCourseSelection = (AutoCourseSelection) baseObject;
        //通过 id 查找到授课信息，然后通过数量判断是否满人
        CourseSelectionService courseSelectionService = ServiceFactory.getService(CourseSelectionService.class);
        GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
        GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(autoCourseSelection.getGive_courses_id());
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
        //超出选课次数
        else if (noSelectionTimes(autoCourseSelection.getStudent_id())) {
            res = -1;
            ScsAlert.warining("超出自动选课次数!");
        }
        ///课程已经满人
        else if (courseSelectionService.isGiveCourseFull(giveCourses)) {
            CourseSelection courseSelection = (CourseSelection) courseSelectionService.findByStudentIdAndGiveCoursesId(autoCourseSelection.getStudent_id(), autoCourseSelection.getGive_courses_id());
            if(courseSelection!=null){
                res = -1;
                ScsAlert.warining("你已经选过该门课！!");
            }else{
                res = super.insert(baseObject);
            }
        }
        // 如果课程没满，直接选课
        else {

            CourseSelection courseSelection = new CourseSelection(autoCourseSelection.getStudent_id(),autoCourseSelection.getGive_courses_id());
            res = courseSelectionService.insert(courseSelection);

        }
        return res;
    }

    @Override
    public List<BaseObject> orderByUpdateTime() {
        return autoCourseSelectionMapper.orderByUpdateTime();
    }
}
