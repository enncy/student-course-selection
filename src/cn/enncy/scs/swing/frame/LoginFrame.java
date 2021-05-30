package cn.enncy.scs.swing.frame;


import cn.enncy.scs.pojo.Account;
import cn.enncy.scs.pojo.Manager;
import cn.enncy.scs.pojo.Student;
import cn.enncy.scs.service.ManagerService;
import cn.enncy.scs.service.ServiceFactory;
import cn.enncy.scs.service.StudentService;
import cn.enncy.scs.swing.component.dialog.ScsDialog;
import cn.enncy.scs.swing.constant.NiceColors;
import cn.enncy.scs.utils.PropertiesUtils;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * //TODO
 * <br/>Created in 13:50 2021/5/1
 *
 * @author: KL-Skeleton
 */
public class LoginFrame {

    private static JButton confirmJbutton = new JButton("登录");
    private static JButton cancelJbutton = new JButton("退出");
    public static ScsDialog loginDialog = new ScsDialog("学生选课系统 - 登录");
    private static PropertiesUtils propertiesUtil = PropertiesUtils.getInstance("setting.properties");

    //用户对象
    public static  Student student;
    public static  Manager manager;

    //是否为管理员
    public static boolean isManager = true;

    //登录类型
    public static final int STUDENT_LOGIN = 0;
    public static final int MANAGER_LOGIN =1;
    public static int loginType = MANAGER_LOGIN;

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
        if (account != null) {
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
        if (password != null) {
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
            loginType = MANAGER_LOGIN;
        });
        studentLogin.addActionListener(e -> {
            loginType = STUDENT_LOGIN;
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


        JPanel container = loginDialog.getContainer();
        container.setLayout(new FlowLayout());
        container.add(loginPanel);

        loginDialog.pack();
        loginDialog.setLocationRelativeTo(null);

        loginDialog.setCanResize(false);
        initEvent(accountField, pwdField);
        loginDialog.setVisible(true);
    }


    private static void initEvent(JTextField accountField, JTextField pwdField) {
        confirmJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String pwd = pwdField.getText();
                if ("".equals(account) || "".equals(pwd)) {
                    JOptionPane.showMessageDialog(loginDialog, "请填写完整的信息！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (loginType==STUDENT_LOGIN) {
                    studentLogin(account,pwd);
                }else{
                    managerLogin(account,pwd);
                }

            }
        });


        cancelJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 学生登录
     *
     * @param number 学生编号
     * @param pwd    学生密码
     * @return: boolean
     */
    private static boolean studentLogin(String number, String pwd) {
        StudentService service = ServiceFactory.getService(StudentService.class);
        Student student = service.findByNumber(number);
        boolean valid = valid(student, pwd);
        if(valid){
            isManager = false;
            LoginFrame.student = student;
            LoginFrame.manager = null;
            MainFrame.init();
            loginDialog.setVisible(false);
            saveLoginInfo(student);
        }
        return valid;
    }

    /**
     * 管理员登录
     *
     * @param account 管理员账号
     * @param pwd     管理员密码
     * @return: boolean
     */
    private static boolean managerLogin(String account, String pwd) {
        ManagerService service = ServiceFactory.getService(ManagerService.class);
        Manager manager = service.findByAccount(account);
        boolean valid = valid(manager, pwd);
        if(valid){
            isManager = true;
            LoginFrame.student = null;
            LoginFrame.manager = manager;
            MainFrame.init();
            loginDialog.setVisible(false);
            saveLoginInfo(manager);
        }
        return  valid;
    }

    /**
     * 验证账号是否正确
     *
     * @param account 账号类
     * @param pwd     密码
     * @return: void
     */
    public static boolean valid(Account account, String pwd) {
        if (account != null && account.getPassword().equals(pwd)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(loginDialog, "账号或者密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //存入登录信息到本地
    public static void saveLoginInfo(Account account) {

        if (propertiesUtil.get("account") == null) {
            propertiesUtil.add("account", account.getAccount());
            propertiesUtil.add("password", account.getPassword());
        } else {
            propertiesUtil.set("account", account.getAccount());
            propertiesUtil.set("password", account.getPassword());
        }
    }

    public static Student getStudent() {
        return student;
    }
}
