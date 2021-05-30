package cn.enncy.scs.service;


import cn.enncy.scs.mapper.CourseSelectionMapper;
import cn.enncy.scs.pojo.BaseObject;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 10:13 2021/4/19
 *
 * @author: enncy
 */
public class CourseSelectionService extends BaseService implements CourseSelectionMapper {

    CourseSelectionMapper courseSelectionMapper = (CourseSelectionMapper) baseMapper;

    public CourseSelectionService( ) {
        super(CourseSelectionMapper.class);
    }

    @Override
    public List<BaseObject> findByGiveCoursesId(int give_courses_id) {
        return courseSelectionMapper.findByGiveCoursesId(give_courses_id);
    }
}
