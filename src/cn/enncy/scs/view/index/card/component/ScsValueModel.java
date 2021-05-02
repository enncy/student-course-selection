package cn.enncy.scs.view.index.card.component;


import cn.enncy.mybatis.ResultSetHandler;
import cn.enncy.scs.pojo.BaseObject;
import cn.enncy.scs.pojo.Info;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.lang.reflect.Field;

/**
 * //TODO   双向数据模型
 * <br/>Created in 18:59 2021/4/28
 *
 * @author: enncy
 */
public class ScsValueModel {

    private BaseObject baseObject;
    private JTextField jTextField;
    private JLabel jLabel;
    private Field field;
    private boolean isValid = false;

    public ScsValueModel(BaseObject baseObject,  Field field) throws IllegalAccessException {
        this.baseObject = baseObject;
        this.field = field;
        initJlable();
        initjTextField();
        bind();
    }

    private void initJlable(){
        if (this.field.isAnnotationPresent(Info.class)) {
            Info info = this.field.getAnnotation(Info.class);
            jLabel = new JLabel(info.value() + "(" + this.field.getName() + ")", JLabel.CENTER);
        } else {
            jLabel = new JLabel(this.field.getName(), JLabel.CENTER);
        }
    }

    private void initjTextField()  {
        this.jTextField = new JTextField();
        //设置默认值
        if(baseObject!=null){
            try {

                Object object = this.field.get(baseObject);

                if(object!=null){
                    jTextField.setText(String.valueOf(object));
                    isValid = true;
                }else{
                    Info info = field.getAnnotation(Info.class);
                    //如果属性不能为空，表单则为无效值
                    if(info.notNull()){
                        isValid = false;
                    }
                    jTextField.setText("");
                }
            } catch (Exception e) {
                jTextField.setText("");
            }
        }
    }


    /**
     * 绑定，并且检验表单正确性
     * @return: void
     */
    private void bind(){
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            //有效数据
            private String validString = jTextField.getText();
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("validString: "+validString);
                try {
                    //获取文本框内容
                    Document document = e.getDocument();
                    String text = document.getText(0, document.getLength());
                    //判空
                    if (!"".equals(jTextField.getText())) {
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
                    }else{
                        validString = "";
                    }
                } catch (BadLocationException e1) {
                    isValid = false;
                    e1.printStackTrace();
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
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

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
