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
import com.bdqn.data.bean.Question;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.QuestionDaoImpl;
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
		List<Exampaper>listExam=new ArrayList<Exampaper>();
		listExam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql);
		if(listExam.size()>0) {
			exam=listExam.get(0);
		}
		return exam;
	}
	
	//根据试卷名称查询试卷信息
	public Exampaper findExampaper(String name){
		Exampaper exam=new Exampaper();
		String sql="Select * from "+Exampaper.tablename+" where name='"+name+"'";
		List<Exampaper>listExam=new ArrayList<Exampaper>();
		
		listExam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql);
		if(listExam.size()>0) {
			exam=listExam.get(0);
		}
		return exam;
	}
	
	
	//查看年级的信息
	public Classes findClasses(int id) {
		Classes classes=new Classes();
		String sql="select * from "+Classes.tablename+" where id="+id;
		classes=new ClassesDaoImpl().<Classes>ExecuteQueryBySql(sql).get(0);
		return classes;
	}
	
	//查看详细信息
	public Result findResultInfo(String string) {
		Result result=null;
		String sql="select * from Result where 1=1";
		if(!(string.equalsIgnoreCase(""))){
			sql+=string;
		}
		List<Result>listResult=new ResultDaoImpl().<Result>ExecuteQueryBySql(sql);
		if(listResult.size()>0) {
			result=listResult.get(0);
		}
		return result;
	}
	
	public List<Question> findQuestion(String id){//根据试题编号查询指定的信息
		List<Question>listQuestion=new ArrayList<Question>();
		String sql="select * from question";
		
		String sql1=id.replaceAll("\\*","=");//替换
		
		String []queid=sql1.split("=");//分割
		if(!(id.equals(""))) {
			sql="select * from question where";
			for(int i=0;i<queid.length;i++) {
				if(queid.length-1==i) {
					sql+=" id="+Integer.parseInt(queid[i]);
				}else {
					sql+=" id="+Integer.parseInt(queid[i])+" or";
				}
			}
		}
		listQuestion=new QuestionDaoImpl().<Question>ExecuteQueryBySql(sql);
		return listQuestion;
	}
	
	
}
