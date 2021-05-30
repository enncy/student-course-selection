package cn.enncy.scs.swing.frame.base.view.index.card.component;


import cn.enncy.mybatis.ResultSetHandler;
import cn.enncy.reflect.ReflectUtils;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.BaseObjectUtils;
import cn.enncy.scs.pojo.ForeignInfo;
import cn.enncy.scs.pojo.Info;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * //TODO   双向数据模型
 * <br/>Created in 18:59 2021/4/28
 *
 * @author: enncy
 */
public class ScsValueModel {

    //数据对象
    private BaseObject baseObject;
    //输入控件
    private JTextField jTextField;
    //选择控件
    private JComboBox jComboBox;

    //控件标签
    private JLabel jLabel;
    //属性
    private Field field;
    //数据是否正确
    private boolean isValid = false;
    //有效数据
    private String validString;

    private static final String NULL_STRING = "无";

    public ScsValueModel(BaseObject baseObject, Field field) throws IllegalAccessException, NoSuchFieldException {
        this.baseObject = baseObject;
        this.field = field;
        initJlable();
        initJTextField();
        if (field.isAnnotationPresent(ForeignInfo.class)) {
            initJComboBox(this.field);
        }
        addDocumentListener();
    }

    private void initJlable() {
        if (this.field.isAnnotationPresent(Info.class)) {
            Info info = this.field.getAnnotation(Info.class);
            jLabel = new JLabel(info.value() + "(" + this.field.getName() + ")", JLabel.CENTER);
        } else {
            jLabel = new JLabel(this.field.getName(), JLabel.CENTER);
        }
    }

    private void initJTextField() {
        this.jTextField = new JTextField();
        //设置默认值
        Object object = getDefaultFieldValue();
        if (object != null) {
            jTextField.setText(String.valueOf(object));
            isValid = true;
        } else {

            Info info = field.getAnnotation(Info.class);
            //如果属性不能为空，表单则为无效值
            if (info!=null && info.notNull()) {
                isValid = false;
            }
            jTextField.setText("");
        }
    }

    private void initJComboBox(Field field) throws IllegalAccessException, NoSuchFieldException {
        this.jComboBox = new JComboBox();

        Map<Integer, Object> foreignInfos = BaseObjectUtils.getForeignInfo(field);
        assert foreignInfos != null;
        for (Map.Entry<Integer, Object> item : foreignInfos.entrySet()) {
            String str = item.getKey() + ":(" + item.getValue() + ")";
            jComboBox.addItem(str);
        }
        if (foreignInfos.isEmpty()) {
            jComboBox.addItem(NULL_STRING);
        }

        //如果有初始值，则绑定初始值,否则默认绑定第一个，
        if(baseObject.getId()!=0 && !foreignInfos.isEmpty()){
            Object defaultFieldValue = getDefaultFieldValue();
            //设置 默认值
            Object defaultItem = foreignInfos.get(defaultFieldValue);
            jComboBox.setSelectedItem(defaultItem);
            bind(String.valueOf(defaultFieldValue));
        }else if(!foreignInfos.isEmpty()){

            String id = String.valueOf(jComboBox.getItemAt(0)).split(":")[0];
            jComboBox.setSelectedItem(jComboBox.getItemAt(0));
            bind(id);
        } else {
            jComboBox.setSelectedItem(NULL_STRING);
            isValid = false;
        }

        //点击选项时，实时绑定到表单中
        jComboBox.addItemListener(e -> {
            String item = String.valueOf(e.getItem());
            if(!NULL_STRING.equals(item)){
                bind(item.split(":")[0]);
            }
        });

    }

    private void bind(String text) {
        //判空
        if (!"".equals(jTextField.getText()) && text!=null) {
            try {
                //绑定数据
                Object object = ResultSetHandler.stringToTarget(text, field.getType());
                field.set(baseObject, object);
                validString = text;
                isValid = true;
            } catch (Exception e1) {
                isValid = false;
                //如果错误，回溯有效值
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        jTextField.setText(validString);
                    }
                });
                e1.printStackTrace();
            }
        } else {
            validString = "";
        }

    }


    /**
     * 监听表单事件。并绑定， 检验表单正确性
     *
     * @return: void
     */
    private void addDocumentListener() {
        validString = jTextField.getText();
        jTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
//                System.out.println("validString: "+validString);
                try {
                    //获取文本框内容
                    Document document = e.getDocument();
                    String text = document.getText(0, document.getLength());
                    //绑定数据
                    bind(text);
                } catch (BadLocationException e1) {
                    isValid = false;
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public Object getDefaultFieldValue(){

        Object object = null;
        if (baseObject != null) {
            ReflectUtils.accessible(this.field);
            try{
                object = this.field.get(baseObject);
                return object;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return object;
    }

    public BaseObject getBaseObject() {
        return baseObject;
    }

    public void setBaseObject(BaseObject baseObject) {
        this.baseObject = baseObject;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean isValid() {
        return isValid;
    }

    public JComboBox getjComboBox() {
        return jComboBox;
    }

    public void setjComboBox(JComboBox jComboBox) {
        this.jComboBox = jComboBox;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
