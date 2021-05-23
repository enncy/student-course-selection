package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.service.BaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * // 分頁信息管理
 * <br/>Created in 21:46 2021/5/23
 *
 * @author: enncy
 */
public class PagingManagerPanel extends ManagePanel {

    //当前页
    private int currentPage = 0;
    //总页数
    private int totalPage;
    //页面数据数量
    private int dataCount = 5;

    private JButton previousPageButton = new JButton("上一页");
    private JButton nextPageButton = new JButton("下一页");
    private JButton lastPageButton = new JButton("最后一页");
    private JComboBox jComboBox = new JComboBox();
    private List<JButton> pageListButton = new ArrayList<>();


    public PagingManagerPanel(Class baseObjectClass, BaseService baseService) {
        super(baseObjectClass, baseService);
        int size = this.getDataList().size();
        //根据数量调整页数
        this.totalPage = size % dataCount > 0 ? (size / dataCount) + 1 : size / dataCount;
        //增加分页组件
        System.out.println("size:"+size);
        JPanel headerPanel = this.getHeaderPanel();

        initPageDataCountSelector();

        headerPanel.add(previousPageButton);

        for (int i=0;i<totalPage;i++){
            JButton jButton = new JButton(String.valueOf(i));
            int finalI = i;
            jButton.addActionListener(e->{
                this.updatePage(finalI);
                this.updateDataList();
            });
            pageListButton.add(jButton);
            headerPanel.add(jButton);
        }

        headerPanel.add(nextPageButton);
        headerPanel.add(lastPageButton);
        headerPanel.add(jComboBox);

        setButtonAction();
        updatePage(0);
    }
    public void updatePage(int currentPage){
        this.currentPage = currentPage;
        //选中指定的页面按钮
        for (JButton jButton : pageListButton) {
            jButton.setSelected(false);
        }
        pageListButton.get(currentPage).setSelected(true);
        //设置按钮在到达限制范围时不能点击
        previousPageButton.setEnabled(currentPage>0);
        nextPageButton.setEnabled(currentPage<totalPage-1);
        lastPageButton.setEnabled(currentPage!=totalPage-1);
    }



    //设置按钮点击事件
    public void setButtonAction(){
        previousPageButton.addActionListener(e->{
            this.updatePage(this.currentPage-1);
            this.updateDataList();
        });
        nextPageButton.addActionListener(e->{
            this.updatePage(this.currentPage+1);
            this.updateDataList();
        });
        lastPageButton.addActionListener(e->{
            this.updatePage(this.totalPage-1);
            this.updateDataList();
        });
    }
    
    /**
     *  初始化分页数量选择器
     *   
     * @return: void
     */
    public void initPageDataCountSelector(){
        jComboBox.addItem(dataCount+"/页");
        jComboBox.addItem("10/页");
        jComboBox.addItem("20/页");
        jComboBox.addItem("50/页");
        jComboBox.addItem("100/页");

        jComboBox.addItemListener(e -> {
            String item = String.valueOf(e.getItem());
            this.dataCount = Integer.parseInt(item.split("/")[0]);
            updateDataList();
        });

        jComboBox.setSelectedIndex(0);
    }

    @Override
    public void updateDataList() {
       this.setDataList(this.getBaseService().findByPages(currentPage*dataCount, dataCount));
       this.updateTablePanel();
    }
}
