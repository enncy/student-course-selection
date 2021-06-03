package cn.enncy.scs.service;

import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.mapper.BaseMapper;
import cn.enncy.scs.mapper.SelectionMapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.GiveCourses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * //TODO   可选课程业务
 * <br/>Created in 15:19 2021/6/1
 *
 * @author: enncy
 */
public abstract class SelectionService extends BaseService implements SelectionMapper {

    SelectionMapper selectionMapper = (SelectionMapper) baseMapper;

    public SelectionService(Class<? extends BaseMapper> mapper) {
        super(mapper);
    }

    /**
     * 判断授课课程是否满人
     *
     * @param giveCourses
     * @return: boolean
     */
    protected boolean isGiveCourseFull(GiveCourses giveCourses) {
        int size = this.findByGiveCoursesId(giveCourses.getId()).size();

        return size > giveCourses.getMax_num() - 1;
    }

    /**
     * 是否在选课时间段
     *
     * @return: boolean
     */
    public boolean isInSelectionTime() {
        try {
            SettingService settingService = ServiceFactory.getService(SettingService.class);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(settingService.getTimeFormat());
            String startTime = settingService.getSelectionStart();
            String endTime = settingService.getSelectionEnd();
            Date startDate = simpleDateFormat.parse(startTime);
            Date endDate = simpleDateFormat.parse(endTime);
            Date date = new Date();

            //  如果当前在开始选课之后，和结束选课之前
            return date.after(startDate) && date.before(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否还有选课次数, 每个人只能选 x 门课
     *
     * @return: boolean
     */
    public boolean noSelectionTimes(int student_id) {
        SettingService settingService = ServiceFactory.getService(SettingService.class);
        //如果学生选课的次数已经超过最大次数，则不能选课
        return noTimes(Integer.parseInt(settingService.getSelectMaxNum()), student_id);
    }

    /**
     * 是否还有撤销次数
     *
     * @param student_id
     * @return: boolean
     */
    public boolean noCancelTimes(int student_id) {
        SettingService settingService = ServiceFactory.getService(SettingService.class);
        //如果学生选课的次数已经超过最大次数，则不能选课
        return noTimes(Integer.parseInt(settingService.getCancelMaxNum()), student_id);
    }

    private boolean noTimes(int max_num, int student_id) {
        List<BaseObject> byStudentId = findByStudentId(student_id);
        //如果学生选课的次数已经超过最大次数，则不能选课
        return byStudentId.size() >= max_num;
    }


    /**
     * 课程是否可选
     *
     * @param give_courses_id
     * @return: boolean
     */
    public boolean isNotOptional(int give_courses_id) {
        OptionalCourseService optionalCourseService = ServiceFactory.getService(OptionalCourseService.class);
        BaseObject byGiveCoursesId = optionalCourseService.findByGiveCoursesId(give_courses_id);
        return byGiveCoursesId == null;
    }


    @Override
    public List<BaseObject> findByGiveCoursesId(int give_courses_id) {
        return selectionMapper.findByGiveCoursesId(give_courses_id);
    }

    @Override
    public List<BaseObject> findByStudentId(int student_id) {
        return selectionMapper.findByStudentId(student_id);
    }

    @Override
    public BaseObject findByStudentIdAndGiveCoursesId(int student_id, int give_courses_id) {
        return selectionMapper.findByStudentIdAndGiveCoursesId(student_id, give_courses_id);
    }

}
