package com.bdqn.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.bdqn.mainfunction.MainFunction;

@SuppressWarnings("serial")
//�̳�Jbutton �����Ӧ��panel ���Ҵ�������������panel ��list<JPanel>
public class MainButton extends JButton{
	private String panelName;
	private MainButton() {
		super();
		this.setLayout(null);
		this.setContentAreaFilled(false);				//���ð�ť͸��
		this.setBorderPainted(false);  				    //���ñ߿�͸��
		this.setFocusPainted(false);  					//����text�߿�͸��
		this.setFont(new Font("΢���ź�",Font.ITALIC,25));//��������
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
