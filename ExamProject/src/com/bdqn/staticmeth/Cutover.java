package com.bdqn.staticmeth;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.bdqn.mainfunction.MainFunction;
import com.bdqn.ui.MainBusinessPanel;
//��ť�л���
public final class Cutover{
	private Cutover() {}
	
	//����л�
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
		
		//�л���Ӧ������屳��ͼƬ
		MainFunction.mainPanelBackground.setIcon(new ImageIcon("src/com/bdqn/images/"+newpanel.getPanelName()+".jpg"));
		
		if(MainFunction.LastSelectedPanelName==null) {
			newpanel.setVisible(true);
			return;
		}
		
		//���ö�����ָ���������
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
