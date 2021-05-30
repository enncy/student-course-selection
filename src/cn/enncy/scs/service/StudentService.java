package cn.enncy.scs.service;


import cn.enncy.scs.mapper.StudentMapper;
import cn.enncy.scs.pojo.Student;

/**
 * //TODO
 * <br/>Created in 10:06 2021/4/19
 *
 * @author: enncy
 */
public class StudentService  extends BaseService  implements StudentMapper{

    private StudentMapper studentMapper = (StudentMapper) baseMapper;

    public StudentService( ) {
        super(StudentMapper.class);
    }

    @Override
    public Student findByNumber(String number) {
        return studentMapper.findByNumber(number);
    }
}
