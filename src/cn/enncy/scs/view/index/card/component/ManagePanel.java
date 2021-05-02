package cn.enncy.scs.view.index.card.component;


import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.view.component.panel.EmptyPanel;
import cn.enncy.scs.view.component.panel.ScsWhitePanel;
import cn.enncy.scs.view.component.scroll.ScsScrollPanel;
import cn.enncy.scs.view.component.table.ScsTableFactory;
import cn.enncy.scs.view.constant.NiceColors;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 13:49 2021/4/27
 *
 * @author: enncy
 */
public class ManagePanel extends ScsWhitePanel {

    //表格容器
    private JPanel tablePanel;
    //头部
    private JPanel headerPanel;
    //表格
    private JTable jTable;
    //表格数据
    private List dataList;
    //目标类
    private  Class clazz;
    //业务
    private BaseService baseService;

    //滚动面板
    private  ScsScrollPanel scsScrollPanel;

    public ManagePanel(Class clazz, BaseService baseService) {
        this.baseService = baseService;
        this.clazz = clazz;
        this.setLayout(new BorderLayout());
        initHeadPanel(clazz);
        initTablePanel();

        scsScrollPanel = new ScsScrollPanel(tablePanel);
        scsScrollPanel.setBorder(new LineBorder(NiceColors.RED));
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(scsScrollPanel,BorderLayout.CENTER);

    }

    private void initHeadPanel(Class clazz){
        headerPanel = new ScsWhitePanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jButton = new JButton("添加信息");
        jButton.setForeground(NiceColors.WHITE);
        jButton.setFont(new Font("微软雅黑",1,12));
        jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        ManagePanel managePanel = this;
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageDialog("添加信息", managePanel,null,ManageType.INSERT);
            }
        });
        headerPanel.add(jButton);

    }

    public void initTablePanel(){


        this.dataList = baseService.findAll();
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
            JTable jTableWithOperate = ScsTableFactory.createJTableWithOperate(dataList, this);
            tablePanel.removeAll();
            tablePanel.add(jTableWithOperate.getTableHeader(),BorderLayout.NORTH);
            tablePanel.add(jTableWithOperate,BorderLayout.CENTER);
            tablePanel.repaint();
            tablePanel.updateUI();
            this.jTable = jTableWithOperate;
        }
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
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
