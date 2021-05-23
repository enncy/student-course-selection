package cn.enncy.scs.swing.frame;


import cn.enncy.mybatis.database.DBUtils;
import cn.enncy.mybatis.database.ExecuteCallback;
import cn.enncy.scs.exception.SqlAnnotationNotFoundException;
import cn.enncy.scs.swing.component.dialog.ScsDialog;
import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.swing.constant.ScsTableName;
import cn.enncy.scs.utils.PropertiesUtils;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //TODO
 * <br/>Created in 13:50 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class LoginFrame {

    private static JButton confirmJbutton = new JButton("登录");
    private static JButton cancelJbutton = new JButton("退出");
    private static ScsDialog loginDialog = new ScsDialog("学生选课系统 - 登录");
    private static PropertiesUtils propertiesUtil = PropertiesUtils.getInstance("setting.properties");



    public static boolean isManager = false;
    public static String defaultTable = ScsTableName.MANAGERS;

    public static void init() {

        loginDialog.setVisible(false);

        JPanel loginPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 1);

        gridLayout.setVgap(10);
        loginPanel.setLayout(gridLayout);
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //账号面板
        JPanel accountPanel = new JPanel();


        JTextField accountField = new JTextField(30);
        String account = propertiesUtil.get("account");
        if(account!=null){
            accountField.setText(account);
        }
        accountField.setText(account);
        JLabel accountJlabel = new JLabel("账号  ", JLabel.CENTER);
        accountPanel.add(accountJlabel);
        accountPanel.add(accountField);

        //密码面板
        JPanel pwdPanel = new JPanel();
        JTextField pwdField = new JTextField(30);
        String password = propertiesUtil.get("password");
        if(password!=null){
            pwdField.setText(password);
        }
        JLabel pwdJlabel = new JLabel("密码  ", JLabel.CENTER);
        pwdPanel.add(pwdJlabel);
        pwdPanel.add(pwdField);

        //选项面板
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton managerLogin = new JRadioButton("管理员登录");
        JRadioButton studentLogin = new JRadioButton("学生登录");
        managerLogin.addActionListener(e -> {
            defaultTable = ScsTableName.MANAGERS;
        });
        studentLogin.addActionListener(e -> {
            defaultTable = ScsTableName.STUDENTS;
        });
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(managerLogin);
        btnGroup.add(studentLogin);
        managerLogin.setSelected(true);
        checkPanel.add(managerLogin);
        checkPanel.add(studentLogin);


        //操作面板
        JPanel operatePanel = new JPanel(new FlowLayout());

        //取消面板
        JPanel cancelOperatePanel = new JPanel();
        cancelOperatePanel.add(cancelJbutton);
        //确认面板
        JPanel confirmOperatePanel = new JPanel();

        confirmJbutton.setForeground(NiceColors.WHITE);

        confirmJbutton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        confirmOperatePanel.add(confirmJbutton);

        operatePanel.add(cancelOperatePanel);
        operatePanel.add(confirmOperatePanel);


        //添加界面
        loginPanel.add(accountPanel);
        loginPanel.add(pwdPanel);
        loginPanel.add(checkPanel);
        loginPanel.add(operatePanel);


        JPanel container = loginDialog.getTitlePanel().getContainer();
        container.setLayout(new FlowLayout());
        container.add(loginPanel);

        loginDialog.pack();
        loginDialog.setLocationRelativeTo(null);

        loginDialog.setCanResize(false);
        initEvent(accountField, pwdField);
        loginDialog.setVisible(true);
    }


    private static void initEvent( JTextField accountField, JTextField pwdField) {
        confirmJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String pwd = pwdField.getText();
                if ("".equals(account) || "".equals(pwd)) {
                    JOptionPane.showMessageDialog(loginDialog, "请填写完整的信息！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                login(defaultTable,account,pwd);

            }
        });


        cancelJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static void login(String tableName,String account,String pwd){
        try {

            String selectSql = getSelectSql(tableName, account, pwd);

            DBUtils.execute(selectSql, new ExecuteCallback() {
                @Override
                public Object executeQuery(ResultSet resultSet) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SqlAnnotationNotFoundException {

                    if (resultSet.next()) {

                        //设置管理权限
                        if(ScsTableName.MANAGERS.equals(tableName)){
                            isManager = true;
                        }
                        MainFrame.init();
                        loginDialog.dispose();
                        //存入本地
                        if(propertiesUtil.get("account")==null){
                            propertiesUtil.add("account",account);
                            propertiesUtil.add("password",pwd);
                        }else{
                            propertiesUtil.set("account",account);
                            propertiesUtil.set("password",pwd);
                        }

                    } else {
                        JOptionPane.showMessageDialog(loginDialog, "账号或者密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    return null;
                }
            });

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(loginDialog, "未知错误! : " + throwables.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);

            throwables.printStackTrace();
        }
    }

    /**
     *
     * 获取查询的语句，因为学生和教师的字段不同
     * @param tableName
     * @param account
     * @param pwd
     * @return: java.lang.String
     */
    private static String getSelectSql(String tableName,String account,String pwd){
        if(tableName.equals(ScsTableName.MANAGERS)){
            return "SELECT * FROM " + tableName + " WHERE account='" + account + "' AND pwd='" + pwd + "' ;";
        }else{
            return "SELECT * FROM " + tableName + " WHERE number='" + account + "' AND pwd='" + pwd + "' ;";
        }
    }


}
