package com.bdqn.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Major;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;
import com.bdqn.ui.MainDialog;

public final class StartExamBiz {
	
	public static void SelectComboBox(JComboBox<String> jcbMajor, JComboBox<String> jcbClasses, JComboBox<String> jcbExampaper) {
		if(jcbMajor!=null) {
			String majorName = jcbMajor.getSelectedItem().toString();
			int MajorId = (new MajorDaoImpl().<Major>ExecuteQueryBySql("select * from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
			List<Classes> classes = new ClassesDaoImpl().ExecuteQueryBySql("select * from "+Classes.tablename+" where mid="+MajorId);
			List<String> classesnames = new ArrayList<String>();
			for(Classes tempclasses:classes) {
				classesnames.add(tempclasses.getName());
			}
			jcbClasses.setModel(new DefaultComboBoxModel<String>(classesnames.toArray(new String[classesnames.size()])));
		}
		
		
		String classesName = jcbClasses.getSelectedItem().toString();
		int ClassesId = (new ClassesDaoImpl().<Classes>ExecuteQueryBySql("select * from "+Classes.tablename+" where name='"+classesName+"'")).get(0).getId();
		List<Exampaper> exampaper = new ExampaperDaoImpl().ExecuteQueryBySql("select * from "+Exampaper.tablename+" where classid="+ClassesId);
		List<String> exampapernames = new ArrayList<String>();
		for(Exampaper tempexampaper:exampaper) {
			exampapernames.add(tempexampaper.getName());
		}
			jcbExampaper.setModel(new DefaultComboBoxModel<String>(exampapernames.toArray(new String[exampapernames.size()])));
	}
	
	@SuppressWarnings("static-access")
	public static void addEaxmpaperTime(String dataTime,String classes,String exampaperName,String time) {
		String findSql="select * from "+Exampaper.tablename+" where name='"+exampaperName+"'";
		int examprId=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(findSql).get(0).getId();
		String upSql="update "+Exampaper.tablename+" set time="+time+",date='"+dataTime+"' where id="+examprId;
		boolean isUpdate=new ExampaperDaoImpl().UpdateBySql(upSql);
		if(isUpdate) {
			new MainDialog().showMessageDialog(null, "时间已添加，请通知考试班级！");
		}else {
			new MainDialog().showMessageDialog(null, "考试时间添加失败！");
		}
	}
}
