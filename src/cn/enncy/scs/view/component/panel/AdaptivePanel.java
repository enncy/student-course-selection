package cn.enncy.scs.view.component.panel;


import cn.enncy.scs.view.index.IndexPanel;

/**
 * //TODO 信息管理自适应面板
 * <br/>Created in 14:23 2021/4/25
 *
 * @author: enncy
 */
public class AdaptivePanel extends GridPanel {

    public AdaptivePanel(float percent) {
        super(IndexPanel.CARD_LAYOUT_PANEL,percent);
        this.setMinWidth(600);
        this.setMinPercent(0.6f);
    }

}
