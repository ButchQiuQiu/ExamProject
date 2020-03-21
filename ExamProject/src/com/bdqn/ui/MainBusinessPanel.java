package com.bdqn.ui;

import javax.swing.JPanel;

//业务窗口
@SuppressWarnings("serial")
public class MainBusinessPanel extends JPanel{
	private String panelName;	//面板名称 key值 切换用
	private int layers;			//面板对应的层数 动画用
	public MainBusinessPanel() {
		super();
		this.setLayout(null);
		this.setSize(1000, 720);
		this.setLocation(280, 0);
		this.setVisible(false);
		this.setOpaque(false);
	}

	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public int getLayers() {
		return layers;
	}
	public void setLayers(int layers) {
		this.layers = layers;
	}
}
