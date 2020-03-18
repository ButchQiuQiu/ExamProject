package com.bdqn.ui;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainDialog extends JOptionPane{
	public  MainDialog() {
		super();
		
	}
	
	@SuppressWarnings("static-access")
	public static void DefaultMessage(String str) {
		new MainDialog().showMessageDialog(null, str);
	}
}
