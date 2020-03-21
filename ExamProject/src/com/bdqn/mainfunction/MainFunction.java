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
	public static String LastSelectedPanelName;				//上一个选择的按钮（动画判定）
	public static String SelectedPanelName;					//当前选择的按钮(动画判定)
	public static boolean Switch=false;						//动画切换开关
	public static List<MainBusinessPanel> Panels;			//所有业务面板
	public static JLabel mainPanelBackground;				//主界面
	public static int MainPanelType=0;					    //主面板类型 1为老师 2为学生
	
	//学生考试的参数
	public static ExamForm StudentExamForm;					//考试面板
	public static int StudentId;							//学生编号
		
	public static void main(String[] args) throws Exception {
		Login ml=new Login();
		ml.setVisible(true);
		//等待用户登录
		LoginBiz.WaitLogin();
		ml.setVisible(false);
		MainFrame mf=new MainFrame();
		mf.setVisible(true);
		//Swing的线程是不安全的 ui只能在主线程中刷新 所以把动画放入主线程 
		//while 循环判断辨识符 标识符改变
		while(true) {
			Thread.sleep(15);
			Thread.sleep(100);
			if(Switch) {
				
				Cutover.PanelCutover();
				if(SelectedPanelName.equals("6")&&MainPanelType==1||SelectedPanelName.equals("学生3")&&MainPanelType==2) {
					//退出(后期可做动画)
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
