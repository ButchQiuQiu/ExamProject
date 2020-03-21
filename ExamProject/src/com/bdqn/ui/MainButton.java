package com.bdqn.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.bdqn.mainfunction.MainFunction;

@SuppressWarnings("serial")
//继承Jbutton 传入对应的panel 并且传入所有其他的panel 用list<JPanel>
public class MainButton extends JButton{
	private String panelName;
	private MainButton() {
		super();
		this.setLayout(null);
		this.setContentAreaFilled(false);				//设置按钮透明
		this.setBorderPainted(false);  				    //设置边框透明
		this.setFocusPainted(false);  					//设置text边框透明
		this.setFont(new Font("微软雅黑",Font.ITALIC,25));//设置字体
		this.setForeground(Color.WHITE);			
	}
	
	public MainButton(String panelName,String buttonText) {
		this();
		this.setText(buttonText);
		this.panelName=panelName;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(MainFunction.Switch==false) {
					MainFunction.LastSelectedPanelName=MainFunction.SelectedPanelName;
					MainFunction.SelectedPanelName=getPanelName();
					MainFunction.Switch=true;
				}
			}});
		
	}
	

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
}
