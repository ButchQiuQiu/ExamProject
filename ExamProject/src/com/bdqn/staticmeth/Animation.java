package com.bdqn.staticmeth;
import com.bdqn.ui.MainBusinessPanel;

//动画效果集
public final class Animation{
	private Animation() {};
	//切换panel动画 默认old的visible为true new的为false
	public static void UpOrDownMovePanel(MainBusinessPanel oldpanel,MainBusinessPanel newpanel) {
		int oneframetime=8;	//一帧所需要跑的时间
		int animationtime=100;  //动画时间	物理上
		int framedistance=(oldpanel.getHeight()/(animationtime/oneframetime));
		newpanel.setVisible(true);
		if(newpanel.getLayers()>oldpanel.getLayers()) {
			newpanel.setLocation(newpanel.getX(), newpanel.getY()-newpanel.getHeight());
		}else {
			newpanel.setLocation(newpanel.getX(), newpanel.getY()+newpanel.getHeight());
		}
		
		for(int i=0;i<(animationtime/oneframetime);i++) {
			try {
				Thread.sleep(oneframetime);
				if(newpanel.getLayers()>oldpanel.getLayers()) {
					oldpanel.setLocation(oldpanel.getX(), oldpanel.getY()+framedistance);
					newpanel.setLocation(newpanel.getX(), newpanel.getY()+framedistance);
				}else {
					oldpanel.setLocation(oldpanel.getX(), oldpanel.getY()-framedistance);
					newpanel.setLocation(newpanel.getX(), newpanel.getY()-framedistance);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}
}
