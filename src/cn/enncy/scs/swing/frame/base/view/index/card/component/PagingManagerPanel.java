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

    private final JButton previousPageButton = new JButton("<");
    private final JButton nextPageButton = new JButton(">");
    private final JButton lastPageButton = new JButton("最后一页");
    private final JComboBox jComboBox = new JComboBox();
    private final List<JButton> pageListButton = new ArrayList<>();


    public PagingManagerPanel(Class baseObjectClass, BaseService baseService) {
        super(baseObjectClass, baseService);
        int size = this.getDataList().size();
        //根据数量调整页数
        double page = (double) size / (double)dataCount;
        this.totalPage = page == (size/dataCount)?(int)page:(int)page+1;

        //增加分页组件

        JPanel headerPanel = this.getHeaderPanel();

        initPageDataCountSelector();

        headerPanel.add(previousPageButton);
        //添加页数 0 按钮
        addPageButton(headerPanel, createPageButton(0));
        //添加页数按钮
        for (int i = 1; i < totalPage; addPageButton(headerPanel,createPageButton(i++)));

        headerPanel.add(nextPageButton);
        headerPanel.add(lastPageButton);
        headerPanel.add(jComboBox);

        setButtonAction();
        updatePage(0);
    }

    public void updatePage(int currentPage) {
        this.currentPage = currentPage;
        //选中指定的页面按钮
        for (JButton jButton : pageListButton) {
            jButton.setSelected(false);
        }
        pageListButton.get(currentPage).setSelected(true);
        //设置按钮在到达限制范围时可以点击
        previousPageButton.setEnabled(currentPage > 0);
        nextPageButton.setEnabled(currentPage < totalPage - 1 && totalPage>0);
        lastPageButton.setEnabled(currentPage != totalPage - 1 &&  totalPage>0);
    }


    //设置按钮点击事件
    public void setButtonAction() {
        previousPageButton.addActionListener(e -> {
            this.updatePage(this.currentPage - 1);
            this.updateDataList();
        });
        nextPageButton.addActionListener(e -> {
            this.updatePage(this.currentPage + 1);
            this.updateDataList();
        });
        lastPageButton.addActionListener(e -> {
            this.updatePage(this.totalPage - 1);
            this.updateDataList();
        });
    }

    /**
     * 初始化分页数量选择器
     *
     * @return: void
     */
    public void initPageDataCountSelector() {
        jComboBox.addItem(dataCount + "/页");
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

    /**
     *  创建分页页数按钮
     *
     * @param index  页数
     * @return: javax.swing.JButton
     */
    public JButton createPageButton(int index){
        JButton jButton = new JButton(String.valueOf(index));
        jButton.addActionListener(e -> {
            this.updatePage(index);
            this.updateDataList();
        });

        return jButton;
    }

    public void addPageButton(JPanel headerPanel, JButton jButton){
        pageListButton.add(jButton);
        headerPanel.add(jButton);
    }

    @Override
    public void updateDataList() {
        this.setDataList(this.getBaseService().findByPages(currentPage * dataCount, dataCount));
        this.updateTablePanel();
    }
}
