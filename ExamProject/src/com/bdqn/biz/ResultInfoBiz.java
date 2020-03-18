package com.bdqn.biz;

import java.util.List;

import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Question;
import com.bdqn.data.bean.Student;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.QuestionDaoImpl;
import com.bdqn.data.impl.StudentDaoImpl;

//查看成绩详细信息
public class ResultInfoBiz {

	public String findAnalysis(String title) {
		String analysis="";
		List<Question>list=null;
		String sql="select analysis from question where title='"+title+"'";
		list=new QuestionDaoImpl().<Question>ExecuteQueryBySql(sql);
		for(Question q:list) {
			if(q!=null) {
				analysis=q.getAnalysis();
			}
		}
		if(analysis.equalsIgnoreCase("")) {
			analysis="该题还没有解析";
		}
		return analysis;
	}
	
	//查询学生的信息
	public Student findStudent(int id) {
		Student stu=null;
		String sql="select * from student where id="+id+";";
		List<Student>listStudent=new StudentDaoImpl().<Student>ExecuteQueryBySql(sql);
		if(listStudent.size()>0) {
			stu=listStudent.get(0);
		}
		return stu;
	}
	
	//查看班级信息
	
	public Classes findClasses(int id) {
		Classes classes=null;
		String sql="select * from Classes where id="+id+";";
		List<Classes>listClasses=new ClassesDaoImpl().<Classes>ExecuteQueryBySql(sql);
		if(listClasses.size()>0) {
			classes=listClasses.get(0);
		}
		return classes;
	}
	
	public Exampaper findExampaper(String name) {
		Exampaper eaxm=null;
		String sql="select * from Exampaper where name='"+name+"';";
		List<Exampaper>listEaxm=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql);
		if(listEaxm.size()>0) {
			eaxm=listEaxm.get(0);
		}
		return eaxm;
	}
}
