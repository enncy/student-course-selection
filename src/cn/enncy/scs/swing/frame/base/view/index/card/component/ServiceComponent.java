package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.panel.EmptyPanel;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;
import cn.enncy.scs.swing.component.scroll.ScsScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 14:39 2021/5/29
 *
 * @author: enncy
 */
public abstract class ServiceComponent extends ScsWhitePanel {

    //目标类
    private Class<? extends  BaseObject> baseObjectClass;
    //业务
    private BaseService baseService;

    //表格容器
    private JPanel tablePanel;
    //头部
    private JPanel headerPanel;
    //表格
    private JTable jTable;
    //表格数据
    private List<BaseObject> dataList;

    //滚动面板
    private  ScsScrollPanel scsScrollPanel;

    public ServiceComponent() {
         init();
    }

    public ServiceComponent(Class<? extends BaseObject> baseObjectClass, BaseService baseService) {
        this.baseObjectClass = baseObjectClass;
        this.baseService = baseService;
         init();
    }

    public void  init(){

        this.setLayout(new BorderLayout());

        tablePanel = new ScsWhitePanel(new BorderLayout());
        headerPanel = new ScsWhitePanel(new FlowLayout(FlowLayout.RIGHT));

        //初始化数据
        setDataList(createDataList());
        //初始化表头
        initHeadPanel(headerPanel);
        //初始化表格
        initTablePanel(tablePanel);

        scsScrollPanel = new ScsScrollPanel(tablePanel);

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(scsScrollPanel,BorderLayout.CENTER);
    }



    //初始化表格面板
    public void initTablePanel(JPanel tablePanel){
        //创建表格数据
        if(this.jTable==null){
            this.jTable = createTable();
            System.out.println("getDataList():"+getDataList());
            //空界面
            if(getDataList()==null || getDataList().size()==0){
                EmptyPanel emptyPanel = new EmptyPanel();
                System.out.println("emptyPanel:"+emptyPanel);
                tablePanel.add(emptyPanel, BorderLayout.CENTER);
            }else{
                tablePanel.add(this.jTable.getTableHeader(),BorderLayout.NORTH);
                tablePanel.add(this.jTable,BorderLayout.CENTER);
            }
        }else{
            updateTablePanel();
        }
    }

    //更新表格面板
    public void updateTablePanel(){
        JTable jTableWithOperate = createTable();
        JPanel tablePanel = getTablePanel();
        tablePanel.removeAll();
        if(getDataList()==null || getDataList().size()==0){
            tablePanel.add(new EmptyPanel(), BorderLayout.NORTH);
        }else{
            tablePanel.add(jTableWithOperate.getTableHeader(),BorderLayout.NORTH);
            tablePanel.add(jTableWithOperate,BorderLayout.CENTER);
        }

        tablePanel.repaint();
        tablePanel.updateUI();
        setjTable(jTableWithOperate);
    }

    //获取数据
    public abstract List<BaseObject> createDataList();


    //初始化表头
    public abstract void initHeadPanel(JPanel headerPanel);

    //获取表格
    public abstract JTable createTable();

    //更新数据
    public abstract void updateDataList();



    public Class<? extends BaseObject> getBaseObjectClass() {
        return baseObjectClass;
    }

    public void setBaseObjectClass(Class baseObjectClass) {
        this.baseObjectClass = baseObjectClass;
    }

    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JTable getjTable() {
        return jTable;
    }

    public List<BaseObject> getDataList() {
        return dataList;
    }

    public ScsScrollPanel getScsScrollPanel() {
        return scsScrollPanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void setScsScrollPanel(ScsScrollPanel scsScrollPanel) {
        this.scsScrollPanel = scsScrollPanel;
    }
}
