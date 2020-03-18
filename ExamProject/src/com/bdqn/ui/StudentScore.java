package com.bdqn.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Result;
import com.bdqn.data.bean.Student;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ResultDaoImpl;
import com.bdqn.data.impl.StudentDaoImpl;
import com.bdqn.mainfunction.MainFunction;

@SuppressWarnings("serial")
public class StudentScore extends MainBusinessPanel{
	public static StudentScore STUDENTSCORE;
	public StudentScore() {
		init();
		STUDENTSCORE=this;
	}
	public void init() {
		
		
		//标签文本
		JLabel stuId=new JLabel("学 号:"),
				name=new JLabel("姓 名:"),
				classes=new JLabel("班 级:"),
				examName=new JLabel("试 卷 名:"),
				score=new JLabel("成 绩:"),
				dataTime=new JLabel("时 间:");
		
		stuId.setFont(new Font("微软雅黑",Font.BOLD,20));
		name.setFont(new Font("微软雅黑",Font.BOLD,20));
		classes.setFont(new Font("微软雅黑",Font.BOLD,20));
		examName.setFont(new Font("微软雅黑",Font.BOLD,20));
		score.setFont(new Font("微软雅黑",Font.BOLD,20));
		dataTime.setFont(new Font("微软雅黑",Font.BOLD,20));
		
		stuId.setBounds(350, 80, 100, 40);
		name.setBounds(350, 150, 100, 40);
		classes.setBounds(350, 220, 100, 40);
		examName.setBounds(323, 290, 100, 40);
		score.setBounds(350, 360, 100, 40);
		dataTime.setBounds(350, 430, 100, 40);
		
		//查询结果标签
		JLabel findStuId=new JLabel(),
				findName=new JLabel(),
				findClasses=new JLabel(),
				findExamName=new JLabel(),
				findScore=new JLabel(),
				findDataTime=new JLabel();
		
		findStuId.setFont(new Font("微软雅黑",Font.PLAIN,20));
		findName.setFont(new Font("微软雅黑",Font.PLAIN,20));
		findClasses.setFont(new Font("微软雅黑",Font.PLAIN,20));
		findExamName.setFont(new Font("微软雅黑",Font.PLAIN,20));
		findScore.setFont(new Font("微软雅黑",Font.PLAIN,20));
		findDataTime.setFont(new Font("微软雅黑",Font.PLAIN,20));
		
		findStuId.setBounds(450, 80, 500, 40);
		findName.setBounds(450, 150, 500, 40);
		findClasses.setBounds(450, 220, 500, 40);
		findExamName.setBounds(450, 290, 500, 40);
		findScore.setBounds(450, 360, 500, 40);
		findDataTime.setBounds(450, 430, 500, 40);
		
		String stuSql="select * from "+Student.tablename+" where id="+MainFunction.StudentId;
		String clastuSql="select * from "+Classes.tablename;
		
		List<Student> students=new ArrayList<Student>();
		List<Classes> classess=new ArrayList<Classes>();
		List<Result> scores=new ArrayList<Result>();
		
		students=new StudentDaoImpl().ExecuteQueryBySql(stuSql);
		classess=new ClassesDaoImpl().ExecuteQueryBySql(clastuSql);
		
		int studentId=0;
		for(Student stu:students) {
			findStuId.setText(String.valueOf(stu.getId()));
			studentId=stu.getId();
			findName.setText(stu.getName());
			for(Classes cl:classess) {
				if(stu.getCid()==cl.getId()) {
					findClasses.setText(cl.getName());
					break;
				}
			}
			break;
		}
		String scoreSql="select * from "+Result.tablename+" where stuid="+studentId;
		scores=new ResultDaoImpl().ExecuteQueryBySql(scoreSql);
		for(Result re:scores) {
			findExamName.setText(re.getExamname());
			findScore.setText(String.valueOf(re.getScore()+"分"));
			findDataTime.setText(re.getDatetime());
			break;
		}
		
		this.add(stuId);
		this.add(name);
		this.add(classes);
		this.add(examName);
		this.add(score);
		this.add(dataTime);
		  
		this.add(findStuId);
		this.add(findName);
		this.add(findClasses);
		this.add(findExamName);
		this.add(findScore);
		this.add(findDataTime);

	}
}
