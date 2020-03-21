package com.bdqn.staticmeth;
import com.bdqn.ui.MainBusinessPanel;

//����Ч����
public final class Animation{
	private Animation() {};
	//�л�panel���� Ĭ��old��visibleΪtrue new��Ϊfalse
	public static void UpOrDownMovePanel(MainBusinessPanel oldpanel,MainBusinessPanel newpanel) {
		int oneframetime=8;	//һ֡����Ҫ�ܵ�ʱ��
		int animationtime=100;  //����ʱ��	������
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
