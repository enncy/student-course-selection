package cn.enncy.scs.swing.component;


import cn.enncy.scs.swing.constant.NiceColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 13:31 2021/4/22
 *
 * @author: enncy
 */
public class ScsIconLabel extends ScsLabel {

    //图片路径
    private String imgUrl ;
    //是否被选中
    private boolean selected;
    //监听器
    private ScsLabelSelectedListener scsLabelSelectedListener;


    public ScsIconLabel(String text, String imgUri, int w) {
        super(text, w);
        this.imgUrl = Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgUri)).getPath();
        this.setImg(imgUrl);
        this.init();
    }

    private void init(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover(e.getComponent());
                super.mouseEntered(e);
            }


            @Override
            public void mouseExited(MouseEvent e) {

                if(!selected){
                    cancelSelected(e.getComponent());
                }
                super.mouseExited(e);
            }
        });
    }

    /**
     * 取消选中
     * @param component 组件
     * @return: void
     */
    public void cancelSelected(Component component){
        component.setForeground(NiceColors.GRAY);
        component.setBackground(NiceColors.SIDE_PANEL_COLOR);
        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setImg(imgUrl);
    }

    /**
     * 取消选中
     * @return: void
     */
    public void cancelSelected( ){
        this.selected  = false;
        cancelSelected(this);
    }

    /**
     * 选中此组件
     * @return: void
     */
    public void selected(){
        if(scsLabelSelectedListener !=null){
            scsLabelSelectedListener.onSelected(this);
        }
        this.selected  = true;
    }

    /**
     * 悬浮在组件上的样式
     * @param component 组件
     * @return: void
     */
    public void hover(Component component){
        component.setForeground(NiceColors.WHITE);
        component.setBackground(NiceColors.BLUE);
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setImg(imgUrl.replaceAll("\\.png","_white.png"));
    }
    /**
     * 悬浮在组件上的样式
     * @return: void
     */
    public void hover(){
        hover(this);
    }

    /**
     * 设置图片
     * @param imgUrl 图片路径
     * @return: void
     */
    public void setImg(String imgUrl){
        ImageIcon icon = new ImageIcon(imgUrl);
        icon.setImage(icon.getImage().getScaledInstance(24, 24 ,Image.SCALE_AREA_AVERAGING ));
        this.setIcon(icon);
    }

    public void setImg(){
        setImg(this.imgUrl);
    }

    public void addSCSLableSelectedListener(ScsLabelSelectedListener scsLabelSelectedListener){
        this.scsLabelSelectedListener = scsLabelSelectedListener;
    }


}
