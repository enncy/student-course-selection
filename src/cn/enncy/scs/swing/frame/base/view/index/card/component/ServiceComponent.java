package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.swing.component.panel.ScsWhitePanel;

/**
 * //TODO
 * <br/>Created in 14:39 2021/5/29
 *
 * @author: enncy
 */
public abstract class ServiceComponent  extends ScsWhitePanel {

    //目标类
    private  Class baseObjectClass;
    //业务
    private BaseService baseService;

    public abstract void updateDataList();
    public abstract void updateTablePanel();

    public ServiceComponent(Class baseObjectClass, BaseService baseService) {
        this.baseObjectClass = baseObjectClass;
        this.baseService = baseService;
    }


    public Class getBaseObjectClass() {
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
}
