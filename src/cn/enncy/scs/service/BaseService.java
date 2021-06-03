package cn.enncy.scs.service;


import cn.enncy.mybatis.SqlSession;
import cn.enncy.scs.factory.ServiceComponentFactory;
import cn.enncy.scs.mapper.BaseMapper;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.swing.frame.base.view.index.card.component.ServiceComponent;

import java.util.List;

/**
 * //TODO
 * <br/>Created in 12:41 2021/4/19
 *
 * @author: enncy
 */
public class BaseService implements BaseMapper {

    public BaseMapper baseMapper;
    public List<ServiceComponent> serviceComponents;

    public BaseService(Class<? extends  BaseMapper> baseMapper ) {
        this.baseMapper = SqlSession.getMapper(baseMapper);
        serviceComponents = ServiceComponentFactory.getServiceComponent(this.getClass());

    }

    @Override
    public int insert(BaseObject baseObject) {
        int insert = baseMapper.insert(baseObject);
        updateServiceComponent();
        System.out.println("insert:"+serviceComponents);
        return insert;
    }

    @Override
    public int update(BaseObject baseObject) {
        int update = baseMapper.update(baseObject);
        updateServiceComponent();
        System.out.println("update:"+serviceComponents);
        return update;
    }

    @Override
    public int deleteById(int id) {
        int delete = baseMapper.deleteById(id);
        updateServiceComponent();
        System.out.println("delete:"+serviceComponents);
        return delete;
    }

    @Override
    public BaseObject findOneById(int id) {
        return baseMapper.findOneById(id);
    }

    @Override
    public List<BaseObject> findByPages(int skip, int size) {
        return baseMapper.findByPages(skip, size);
    }

    @Override
    public List<BaseObject> findAll() {
        return baseMapper.findAll();
    }

    public BaseMapper  getMapper(){
        return baseMapper;
    }

    public void updateServiceComponent(){
        if(serviceComponents==null) {
            serviceComponents = ServiceComponentFactory.getServiceComponent(this.getClass());
        }
        for (ServiceComponent serviceComponent : serviceComponents) {
            serviceComponent.updateDataList();
        }
    }
}
