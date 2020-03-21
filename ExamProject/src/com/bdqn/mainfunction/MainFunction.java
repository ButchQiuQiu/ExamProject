package com.bdqn.mainfunction;
import java.util.List;

import javax.swing.JLabel;

import com.bdqn.ui.MainFrame;
import com.bdqn.ui.ExamForm;
import com.bdqn.ui.Login;
import com.bdqn.biz.LoginBiz;
import com.bdqn.staticmeth.Cutover;
import com.bdqn.ui.MainBusinessPanel;

public class MainFunction {
	public static String LastSelectedPanelName;				//��һ��ѡ��İ�ť�������ж���
	public static String SelectedPanelName;					//��ǰѡ��İ�ť(�����ж�)
	public static boolean Switch=false;						//�����л�����
	public static List<MainBusinessPanel> Panels;			//����ҵ�����
	public static JLabel mainPanelBackground;				//������
	public static int MainPanelType=0;					    //��������� 1Ϊ��ʦ 2Ϊѧ��
	
	//ѧ�����ԵĲ���
	public static ExamForm StudentExamForm;					//�������
	public static int StudentId;							//ѧ�����
		
	public static void main(String[] args) throws Exception {
		Login ml=new Login();
		ml.setVisible(true);
		//�ȴ��û���¼
		LoginBiz.WaitLogin();
		ml.setVisible(false);
		MainFrame mf=new MainFrame();
		mf.setVisible(true);
		//Swing���߳��ǲ���ȫ�� uiֻ�������߳���ˢ�� ���԰Ѷ����������߳� 
		//while ѭ���жϱ�ʶ�� ��ʶ���ı�
		while(true) {
			Thread.sleep(15);
			Thread.sleep(100);
			if(Switch) {
				
				Cutover.PanelCutover();
				if(SelectedPanelName.equals("6")&&MainPanelType==1||SelectedPanelName.equals("ѧ��3")&&MainPanelType==2) {
					//�˳�(���ڿ�������)
					System.exit(0);
				}
				Switch=false;
			}
				if(MainPanelType==2) {
					StudentExamForm.jPanelj(StudentId);
				}
		}
	}
}
