package com.bdqn.biz;

import com.bdqn.data.bean.Student;
import com.bdqn.data.bean.Teacher;
import com.bdqn.data.impl.StudentDaoImpl;
import com.bdqn.data.impl.TeacherDaoImpl;
import com.bdqn.mainfunction.MainFunction;
import com.bdqn.ui.MainDialog;

public final class LoginBiz {
	private LoginBiz() {};
	
	//登录
	@SuppressWarnings("static-access")
	public static void Login(String id,String passWord,boolean teacherSelectStutus) {
		if(id.equals("")||passWord.equals("")) {
			MainDialog.DefaultMessage("账号密码不能为空");
			return ;
		}
		if(teacherSelectStutus) {
			if(new TeacherDaoImpl().<Teacher>ExecuteQueryBySql("select * from "+Teacher.tablename+" where id="+id+" and password='"+passWord+"'").size()>0) {
				MainFunction.MainPanelType=1; 
			}else{
				MainDialog.DefaultMessage( "账号或者密码错误!!");
			};
		}else {
			if(new StudentDaoImpl().<Student>ExecuteQueryBySql("select * from "+Student.tablename+" where id="+id+" and password='"+passWord+"'").size()>0) {
				MainFunction.MainPanelType=2;
				MainFunction.StudentId=Integer.parseInt(id);
			}else{
				MainDialog.DefaultMessage("账号或者密码错误!!");
			};
		}
	}
	
	//等待用户进入程序 
	public static void WaitLogin() {
		while(MainFunction.MainPanelType==0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
