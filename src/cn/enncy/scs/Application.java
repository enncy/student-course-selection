package cn.enncy.scs;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * //TODO
 * <br/>Created in 14:16 2021/4/14
 *
 * @author: enncy
 */
public class Application {

    public static void main(String[] args) {
//        SqlSession.initSql("cn.enncy.scs.mapper");
//        MainFrame.init();

//        StudentMapper studentMapper = SqlSession.getMapper(StudentMapper.class);
//        studentMapper.insert(new Student("言小溪","4123456","123456",132));
//        Student student = studentMapper.findOneById(2);
//        System.out.println(student.getClass_id());
//        System.out.println(studentMapper.deleteById(2));

        System.out.println(humpToUnderline("humpToUnderline"));
        System.out.println(underlineToHump("hump_to_underline"));


    }

    /**
     * 驼峰转下划线
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String humpToUnderline(String str) {
        String regex = "([A-Z])";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group();
            str = str.replaceAll(target, "_"+target.toLowerCase());
        }
        return str;
    }

    /**
     * 下划线转驼峰
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String underlineToHump(String str) {
        String regex = "_(.)";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group(1);
            str = str.replaceAll("_"+target, target.toUpperCase());
        }
        return str;
    }

}

