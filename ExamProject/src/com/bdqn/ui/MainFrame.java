package com.bdqn.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
//���������ڷ�������� �����������ڲ���
public class MainFrame extends JFrame{
	public MainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(0, 0, 1286, 755);
		MainPanel mp=new MainPanel();
		this.setContentPane(mp);
		this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2), 
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2));
	}
}
