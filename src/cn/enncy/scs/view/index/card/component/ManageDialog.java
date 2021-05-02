package cn.enncy.scs.view.index.card.component;


import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.service.BaseService;
import cn.enncy.scs.view.component.dialog.ScsAlert;
import cn.enncy.scs.view.component.dialog.ScsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 23:33 2021/4/27
 *
 * @author: enncy
 */
public class ManageDialog extends ScsDialog {

    private Class clazz;
    private BaseService baseService;
    //数据对象
    private BaseObject baseObject;
    private List<ScsValueModel> scsValueModels = new ArrayList<>();


    public ManageDialog(String title,ManagePanel managePanel,BaseObject defaultBaseObject,ManageType manageType) {
        super(title);
        this.clazz   =managePanel.getClazz();
        this.baseService = managePanel.getBaseService();
        //不可縮放
        this.setCanResize(false);
        ManageDialog manageDialog = this;

        //获取窗体组件的容器
        JPanel jPanel = manageDialog.getTitlePanel().getContainer();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        //创建表单
        JPanel form = createValueModelForm(defaultBaseObject);

        JPanel operate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jButton1 = new JButton("取消");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageDialog.dispose();
            }
        });
        JButton jButton2 = new JButton("确定");

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(baseObject!=null ){
                    if(!isAllValid()){
                        ScsAlert.error("字段不能为空！");
                        return;
                    }
                    //如果是插入操作
                    if(manageType==ManageType.INSERT){
                        int insert = baseService.insert(baseObject);
                        if (insert == 0) {
                            ScsAlert.error("添加数据失败！请检查是否有重复数据！");
                        }else{
                            managePanel.initTablePanel();
                            manageDialog.dispose();
                        }
                    }
                    //更新操作
                    else if(manageType==ManageType.UPDATE){
                        baseService.update(baseObject);
                        managePanel.initTablePanel();
                        manageDialog.dispose();

                    }
                }else{
                    ScsAlert.error("添加数据失败！请检查是否有重复数据！");
                }

            }
        });
        operate.add(jButton1);
        operate.add(jButton2);

        JPanel container = new JPanel(new BorderLayout());

        container.add(form, BorderLayout.CENTER);
        container.add(operate, BorderLayout.SOUTH);

        jPanel.add(container);
        this.pack();
    }

    /**
     * 创建数据绑定表单
     * @param defaultBaseObject 默认数据
     * @return: JPanel  表单对象
     */
    private JPanel createValueModelForm(BaseObject defaultBaseObject) {
        // 数据绑定
        Field[] declaredFields = clazz.getDeclaredFields();
        GridLayout gridLayout = new GridLayout(declaredFields.length, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel form = new JPanel(gridLayout);

        try {
            //new对象，如果已经有默认对象，则不用new
            baseObject = defaultBaseObject==null? (BaseObject) clazz.getConstructor().newInstance() : defaultBaseObject;
            for (Field declaredField : declaredFields) {
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                //双向数据绑定
                ScsValueModel scsValueModel = new ScsValueModel(baseObject, declaredField);
                scsValueModels.add(scsValueModel);
                //生成表单
                form.add(scsValueModel.getjLabel());
                form.add(scsValueModel.getjTextField());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return form;
    }

    //检验表单正确性
    private boolean isAllValid(){
        if(scsValueModels.size()==0){
            return false;
        }else{
            boolean valid = true;
            for (ScsValueModel scsValueModel : scsValueModels) {
                if(!scsValueModel.isValid()){
                    valid =false;
                }
            }
            return valid;
        }
    }
}
