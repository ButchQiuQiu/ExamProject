package com.bdqn.staticmeth;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.bdqn.mainfunction.MainFunction;
import com.bdqn.ui.MainBusinessPanel;
//按钮切换类
public final class Cutover{
	private Cutover() {}
	
	//面板切换
	public static void PanelCutover() {
		if(MainFunction.SelectedPanelName==null||MainFunction.SelectedPanelName==MainFunction.LastSelectedPanelName) {
			return;
		}
		MainBusinessPanel oldpanel=null,newpanel = null;
		for(MainBusinessPanel testpanel:MainFunction.Panels) {
			if(testpanel.getPanelName().equals(MainFunction.SelectedPanelName)) {
				newpanel=testpanel;
			}else if(testpanel.getPanelName().equals(MainFunction.LastSelectedPanelName)){
				oldpanel=testpanel;
			}
		}
		
		//切换对应的主面板背景图片
		MainFunction.mainPanelBackground.setIcon(new ImageIcon("src/com/bdqn/images/"+newpanel.getPanelName()+".jpg"));
		
		if(MainFunction.LastSelectedPanelName==null) {
			newpanel.setVisible(true);
			return;
		}
		
		//调用动画后恢复面板的属性
		Rectangle oldBounds=oldpanel.getBounds();
		Rectangle newBounds=newpanel.getBounds();
		//
		Animation.UpOrDownMovePanel(oldpanel, newpanel);
		//
		oldpanel.setBounds(oldBounds);
		newpanel.setBounds(newBounds);
		oldpanel.setVisible(false);
		newpanel.setVisible(true);
	}
}
