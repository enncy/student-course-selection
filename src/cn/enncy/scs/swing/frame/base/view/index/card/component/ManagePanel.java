package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.panel.EmptyPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.component.scroll.ScsScrollPanel;
import cn.enncy.scs.swing.component.table.ScsTableFactory;
import cn.enncy.scs.swing.constant.NiceColors;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * //    信息管理控件
 * <br/>Created in 13:49 2021/4/27
 *
 * @author: enncy
 */
public abstract class ManagePanel extends ScsWhitePanel {

    //表格容器
    private JPanel tablePanel;
    //头部
    private JPanel headerPanel;
    //表格
    private JTable jTable;
    //表格数据
    private List dataList;
    //目标类
    private  Class baseObjectClass;
    //业务
    private BaseService baseService;

    //滚动面板
    private  ScsScrollPanel scsScrollPanel;

    public ManagePanel(Class baseObjectClass, BaseService baseService) {
        this.baseService = baseService;
        this.baseObjectClass = baseObjectClass;
        this.setLayout(new BorderLayout());
        //初始化数据
        initDataList();
        //初始化表头
        initHeadPanel();
        //初始化表格
        initTablePanel();

        scsScrollPanel = new ScsScrollPanel(tablePanel);

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(scsScrollPanel,BorderLayout.CENTER);

    }
    //初始化数据
    public void initDataList(){
        this.dataList = baseService.findAll() ;
    }

    //初始化头部面板
    private void initHeadPanel(){
        headerPanel = new ScsWhitePanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jButton = new JButton("添加信息");
        jButton.setForeground(NiceColors.WHITE);
        jButton.setFont(new Font("微软雅黑",1,12));
        jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        ManagePanel managePanel = this;
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ManageDialog("添加信息", managePanel,null,ManageType.INSERT);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                }
            }
        });
        headerPanel.add(jButton);

    }

    //初始化表格面板
    public void initTablePanel(){
        //创建表格数据
        if(this.jTable==null){
            tablePanel = new ScsWhitePanel(new BorderLayout());
            this.jTable = ScsTableFactory.createJTableWithOperate(dataList, this);
            //空界面
            if(dataList.size()==0){
                EmptyPanel emptyPanel = new EmptyPanel();
                tablePanel.add(emptyPanel,BorderLayout.NORTH);
            }else{
                tablePanel.add(this.jTable.getTableHeader(),BorderLayout.NORTH);
                tablePanel.add(this.jTable,BorderLayout.CENTER);
            }
        }else{
            updateTablePanel();
        }
    }

    //更新數據
    public void updateTablePanel(){
        JTable jTableWithOperate = ScsTableFactory.createJTableWithOperate(dataList, this);
        tablePanel.removeAll();
        tablePanel.add(jTableWithOperate.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(jTableWithOperate,BorderLayout.CENTER);
        tablePanel.repaint();
        tablePanel.updateUI();
        this.jTable = jTableWithOperate;
    }

    public void updateDataList(){
        this.dataList = baseService.findAll();
        //更新表格面板
        this.updateTablePanel();
    }


    public Class getBaseObjectClass() {
        return baseObjectClass;
    }

    public void setBaseObjectClass(Class baseObjectClass) {
        this.baseObjectClass = baseObjectClass;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }
}
