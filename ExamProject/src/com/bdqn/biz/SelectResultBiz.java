package com.bdqn.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bdqn.data.bean.Result;
import com.bdqn.data.bean.Student;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.ResultDaoImpl;
import com.bdqn.data.impl.StudentDaoImpl;
import com.bdqn.staticmeth.DataBase;
//��ѯ�ɼ��߼�
public class SelectResultBiz {

	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	DataBase bs=new DataBase();
	
	public List<Result> findResult(String name){//��ѯ���еĳɼ�
		List<Result>lResult=new ArrayList<Result>();//
		String sql="select * from "+Result.tablename;//��ѯ���еĳɼ�
		if(!(name.equals(""))) {
			int id=Integer.parseInt(name);
			sql+=" where stuid="+id;
		}
		lResult=new ResultDaoImpl().ExecuteQueryBySql(sql);
		return lResult;
	}
	
	public Student findStudent(int id) {//����ѧ�Ų�ѯѧ��
		Student student=new Student();
		String sql="select * from "+Student.tablename+" where id="+id;
		student=new StudentDaoImpl().<Student>ExecuteQueryBySql(sql).get(0);
		return student;
	}
	
	
	//�����Ծ��Ų�ѯ�Ծ���Ϣ
	public Exampaper findExampaper(int id){
		Exampaper exam=new Exampaper();
		String sql="Select * from "+Exampaper.tablename+" where id="+id;
		exam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);
		return exam;
	}
	
	//�鿴�꼶����Ϣ
	public Classes findClasses(int id) {
		Classes classes=new Classes();
		String sql="select * from "+Classes.tablename+" where id="+id;
		classes=new ClassesDaoImpl().<Classes>ExecuteQueryBySql(sql).get(0);
		return classes;
	}
	
}
