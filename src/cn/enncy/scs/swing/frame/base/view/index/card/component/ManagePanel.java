package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.table.ScsTableFactory;

import javax.swing.*;
import java.util.List;

/**
 * //    业务组件 信息管理控件
 * <br/>Created in 13:49 2021/4/27
 *
 * @author: enncy
 */
public abstract class ManagePanel extends ServiceComponent {


    public ManagePanel(Class<? extends BaseObject> baseObjectClass, BaseService baseService) {
        super(baseObjectClass,baseService);


    }
    //初始化数据
    @Override
    public List<BaseObject> createDataList(){
        return getBaseService().findAll();
    }

    //初始化头部面板
    @Override
    public void initHeadPanel(JPanel headerPanel){
        headerPanel.add(new InsertInfomationButton(this));
    }


    @Override
    public void updateDataList(){
        setDataList(getBaseService().findAll());
        //更新表格面板
         updateTablePanel();
    }

    @Override
    public JTable createTable() {
        return ScsTableFactory.createJTableWithOperate(getDataList(), this);
    }

}
