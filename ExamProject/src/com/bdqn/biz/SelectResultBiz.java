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
//查询成绩逻辑
public class SelectResultBiz {

	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	DataBase bs=new DataBase();
	
	public List<Result> findResult(String name){//查询所有的成绩
		List<Result>lResult=new ArrayList<Result>();//
		String sql="select * from "+Result.tablename;//查询所有的成绩
		if(!(name.equals(""))) {
			int id=Integer.parseInt(name);
			sql+=" where stuid="+id;
		}
		lResult=new ResultDaoImpl().ExecuteQueryBySql(sql);
		return lResult;
	}
	
	public Student findStudent(int id) {//根据学号查询学生
		Student student=new Student();
		String sql="select * from "+Student.tablename+" where id="+id;
		student=new StudentDaoImpl().<Student>ExecuteQueryBySql(sql).get(0);
		return student;
	}
	
	
	//根据试卷编号查询试卷信息
	public Exampaper findExampaper(int id){
		Exampaper exam=new Exampaper();
		String sql="Select * from "+Exampaper.tablename+" where id="+id;
		exam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);
		return exam;
	}
	
	//查看年级的信息
	public Classes findClasses(int id) {
		Classes classes=new Classes();
		String sql="select * from "+Classes.tablename+" where id="+id;
		classes=new ClassesDaoImpl().<Classes>ExecuteQueryBySql(sql).get(0);
		return classes;
	}
	
}
