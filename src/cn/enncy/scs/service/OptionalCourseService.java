package cn.enncy.scs.service;


import cn.enncy.scs.mapper.OptionalCourseMapper;
import cn.enncy.scs.pojo.BaseObject;

/**
 * //TODO
 * <br/>Created in 10:12 2021/4/19
 *
 * @author: enncy
 */
public class OptionalCourseService extends BaseService implements OptionalCourseMapper{

    OptionalCourseMapper optionalCourseMapper = (OptionalCourseMapper) baseMapper;

    public OptionalCourseService( ) {
        super(OptionalCourseMapper.class);
    }

    @Override
    public BaseObject findByGiveCoursesId(int give_courses_id) {
        return optionalCourseMapper.findByGiveCoursesId(give_courses_id);
    }
}
