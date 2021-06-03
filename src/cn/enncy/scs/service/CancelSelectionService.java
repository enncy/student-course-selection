package cn.enncy.scs.service;


import cn.enncy.scs.factory.ServiceFactory;
import cn.enncy.scs.mapper.CancelSelectionMapper;
import cn.enncy.scs.pojo.*;
import cn.enncy.scs.swing.component.dialog.ScsAlert;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 10:14 2021/4/19
 *
 * @author: enncy
 */
public class CancelSelectionService extends SelectionService implements CancelSelectionMapper {

    CancelSelectionMapper cancelSelectionMapper = (CancelSelectionMapper) baseMapper;


    public CancelSelectionService() {
        super(CancelSelectionMapper.class);
    }


    /**
     * 撤销选课的同时，开始自动选课
     *
     * @param baseObject
     * @return: int
     */
    @Override
    public int insert(BaseObject baseObject) {
        CancelSelection cancelSelection = (CancelSelection) baseObject;

        CourseSelectionService courseSelectionService = ServiceFactory.getService(CourseSelectionService.class);
        //通过 id 查找到授课信息，然后通过数量判断是否满人
        GiveCoursesService giveCoursesService = ServiceFactory.getService(GiveCoursesService.class);
        GiveCourses giveCourses = (GiveCourses) giveCoursesService.findOneById(cancelSelection.getGive_courses_id());

        ///获取到选课信息，如果此选课信息不存在，则不能撤销
        CourseSelection courseSelection = (CourseSelection) courseSelectionService.findByStudentIdAndGiveCoursesId(cancelSelection.getStudent_id(), giveCourses.getId());
        if (courseSelection == null) {
            ScsAlert.warining("查询不到选课记录，撤销无效!");
            return -1;
        }

        int res;
        int deleteCourseCount = 0;

        //不在选课期间
        if (!isInSelectionTime()) {
            res = -1;
            deleteCourseCount = 0;
            ScsAlert.warining("未到选课阶段!撤销无效!");
        }
        //超出撤销次数
        else if (noCancelTimes(cancelSelection.getStudent_id())) {
            res = -1;
            deleteCourseCount = 0;
            ScsAlert.warining("你的撤销次数已经用完!");
        }
        // 如果课程没满，直接选课
        else {
            //插入撤销记录
            res = super.insert(cancelSelection);
            //撤销选课
            deleteCourseCount = courseSelectionService.deleteById(courseSelection.getId());
            if (deleteCourseCount > 0) {
                ScsAlert.autoAlert(res, "撤销选课失败！", "暂无此选课记录", "撤销选课成功");
            } else {
                ScsAlert.error("撤销选课失败，请重试");
            }

        }


        return res;
    }

    /**
     * 是否还有撤销次数
     *
     * @return: boolean
     */
    public boolean hasCancelTimes(Student student) {
        List<BaseObject> byStudentId = findByStudentId(student.getId());
        SettingService settingService = ServiceFactory.getService(SettingService.class);
        //如果学生撤销选课的次数已经超过最大次数，则不能撤销
        if (byStudentId.size() > Integer.parseInt(settingService.getCancelMaxNum()) - 1) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public int cancelSelection(int student_id, int give_courses_id) {
        return 0;
    }
}
