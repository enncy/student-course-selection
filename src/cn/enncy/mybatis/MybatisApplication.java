package cn.enncy.mybatis;


import cn.enncy.io.FileContentReader;
import cn.enncy.mybatis.annotation.Mapper;
import cn.enncy.mybatis.constant.SqlConstant;
import cn.enncy.mybatis.database.DBUtils;
import cn.enncy.mybatis.database.ExecuteCallback;
import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scanner.AnnotationScanner;
import cn.enncy.scs.exception.SqlAnnotationNotFoundException;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Manager;
import cn.enncy.scs.pojo.Setting;
import cn.enncy.scs.swing.constant.ScsTableName;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 13:46 2021/6/1
 *
 * @author: enncy
 */
public class MybatisApplication {

    private static Logger logger =  Logger.getLogger(SqlSession.class);
    public static Setting select_max_num = new Setting("select_max_num", "3", "学生最大选课数量");
    public static Setting cancel_max_num = new Setting("cancel_max_num", "5", "学生最大撤销选课数量");
    public static Setting selection_start = new Setting("selection_start","2021/5/1 00:00:00","选课开始时间");
    public static Setting selection_end = new Setting("selection_end","2021/12/1 00:00:00","选课结束时间");
    public static Setting notice =new Setting("notice","无","公告信息");
    public static Setting time_format = new Setting("time_format","yyyy/MM/dd HH:mm:ss","时间格式化转换字符串");




    /**
     * 初始化 sql 建表文件, 先查询是否有表，如果报错则创建新表
     * @param packageName
     * @return: void
     */
    public static void initSql(String packageName){
        AnnotationScanner.readClass(packageName, clazz -> {
            if(clazz.isAnnotationPresent(Mapper.class)){
                Mapper mapper = (Mapper) clazz.getAnnotation(Mapper.class);
                String tableName = mapper.tableName();
                if("".equals(tableName)){
                    throw new Exception("Mapper.tableName is not can be empty");
                }
                // 先查询，如果报错说明未键表
                try {
                    DBUtils.execute("select * from " + tableName);
                }catch (SQLException e) {
                    if(e instanceof SQLSyntaxErrorException){
                        try {
                            //建表
                            String fileContent = FileContentReader.getFileContent(
                                    Objects.requireNonNull(
                                            SqlSession.class.getClassLoader().getResource("sql/"+tableName + ".sql")).toURI());
                            DBUtils.execute(fileContent);
                            logger.info("[mybatis] : 创建新表 "+tableName);
                        } catch (IOException | SQLException e1) {
                            e1.printStackTrace();
                        }
                    }else{
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    /**
     * 初始化系统设置和管理员账号
     * @return: void
     */
    public static void initSettings(){
        try {

            //初始化系统设置
            DBUtils.execute("SELECT * FROM "+ ScsTableName.SETTINGS+";", new ExecuteCallback() {
                @Override
                public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {

                    if(!resultSet.next()){
                        logger.info("初始化系统设置");
                        insertSetting(select_max_num);
                        insertSetting(cancel_max_num);
                        insertSetting(selection_start);
                        insertSetting(selection_end);
                        insertSetting(notice);
                        insertSetting(time_format);
                    }

                    return null;
                }
            });

            //初始化管理员
            DBUtils.execute("SELECT * FROM "+ ScsTableName.MANAGERS +";", new ExecuteCallback() {
                @Override
                public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {
                    if(!resultSet.next()){
                        logger.info("初始化管理员账号");
                        insertData(ScsTableName.MANAGERS,new Manager("管理员","manager","12345"));
                    }

                    return null;
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     *  插入数据
     * @param tableName 数据库名
     * @param baseObject    基本数据
     * @return: void
     */
    private static void insertData(String tableName, BaseObject baseObject) throws SQLException {
        String sql = SqlStringHandler.replaceInsertFields("INSERT IGNORE INTO "+tableName+"(#{" + SqlConstant.KEY_ARRAY + "}) value(#{" + SqlConstant.VALUE_ARRAY + "});", ReflectUtils.getObjectValueMap(baseObject));
        sql = SqlStringHandler.replaceParams(sql, baseObject);
        DBUtils.execute(sql);
    }

    private static void insertSetting(BaseObject baseObject) throws SQLException {
        insertData(ScsTableName.SETTINGS, baseObject);
    }


}
