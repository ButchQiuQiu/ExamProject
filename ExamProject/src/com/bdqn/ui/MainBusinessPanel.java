package com.bdqn.ui;

import javax.swing.JPanel;

//ҵ�񴰿�
@SuppressWarnings("serial")
public class MainBusinessPanel extends JPanel{
	private String panelName;	//������� keyֵ �л���
	private int layers;			//����Ӧ�Ĳ��� ������
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
