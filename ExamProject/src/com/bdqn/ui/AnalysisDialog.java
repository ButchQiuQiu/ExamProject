package com.bdqn.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

//继承对话框 并改变里面的东西
public class AnalysisDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AnalysisDialog(JFrame j,String text) {
		super(j,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setSize(400,200);
		JTextArea textArea=new JTextArea(text);
		textArea.add(new JScrollPane());
		textArea.setBounds(0,0,370,150);
		textArea.setEditable(false);//不可编辑
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.white);
		textArea.setFont(new Font("微软雅黑",1,18));
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(textArea);
		//this.pack();
		this.setVisible(true);
	}
	
	public AnalysisDialog() {
		
	}
}
