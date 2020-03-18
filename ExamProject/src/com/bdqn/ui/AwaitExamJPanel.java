package com.bdqn.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;


import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Result;
import com.bdqn.data.bean.Student;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.ResultDaoImpl;
import com.bdqn.data.impl.StudentDaoImpl;
import com.bdqn.mainfunction.MainFunction;

//等待考试界面
public class AwaitExamJPanel extends MainBusinessPanel{

	/**
	 * 
	 */
	public static long dateStor;//存放考试结束的时间
	public static long dateStart;//存放考试开始的时间
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = 1L;
	static List<Exampaper>listExampaper=new ArrayList<Exampaper>();
	static JButton jbtnAwaitExam;//点击按钮出现考试的界面
	static int n;
	static List<Student>listStudent=new ArrayList<Student>();//保存所有没有参加考试的学生
	static String sqlStudentAll="";//保存成绩 最长
	static int time;//用来判断 考试的时长
	static long time5;//对应时差
	public AwaitExamJPanel() {
		AwaitExamJPanel.jbtnAwaitExam=new JButton("考试未开始");
		AwaitExamJPanel.jbtnAwaitExam.setBounds(400,300,200,60);
		AwaitExamJPanel.jbtnAwaitExam.setFont(new Font("微软雅黑",1,20));
		AwaitExamJPanel.jbtnAwaitExam.setOpaque(false);
		this.add(AwaitExamJPanel.jbtnAwaitExam);
		AwaitExamJPanel.jbtnAwaitExam.setEnabled(false);
		awaite();
		
		AwaitExamJPanel.jbtnAwaitExam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AwaitExamJPanel.jbtnAwaitExam.setEnabled(false);//考试开始之后无法继续点击
				try {
					
					AwaitExamJPanel.time5=new Date().getTime();//获得当前时间
					time=(int)((dateStor-time5)/1000);//获得分钟
					new ExamForm(exam.getId(),MainFunction.StudentId,time).setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//获得开始时间
				
			}
		});
		
	}
	
	ExamForm e;
	Exampaper exam=new Exampaper();
	public boolean awaite() {
		String sql="select * from exampaper where classid in(select cid from student where id="+MainFunction.StudentId+")ORDER BY date desc limit 1;";
		AwaitExamJPanel.listExampaper=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql);
		if(AwaitExamJPanel.listExampaper.size()>0) {
			exam=AwaitExamJPanel.listExampaper.get(0);
			Date d=null;
			try {
				d = sf.parse(exam.getDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			AwaitExamJPanel.dateStart=d.getTime();//考试开始的时间
			AwaitExamJPanel.dateStor=d.getTime()+exam.getTime()*60*1000;//获得结束时间
			new Thread(new Runnable() {
				public void run() {
					while(true) {
						String date=time();
						try {
							Thread.sleep(2000);
							if(date.compareTo(exam.getDate())>0&&dateTimeLong()&&reuslt()) {
								AwaitExamJPanel.jbtnAwaitExam.setEnabled(true);
								exam=AwaitExamJPanel.listExampaper.get(0);
								AwaitExamJPanel.jbtnAwaitExam.setText("开始考试");
								AwaitExamJPanel.n=5;
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
			
		}else {
			
		}
		return false;
	}
	
	//时间格式
	public String time() {
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);//返回当前时间
	}
	
	//判断考试时间结束是否已经过了
	public boolean dateTimeLong() {
		boolean isFlag=false;
		Date date=new Date();
		long present=date.getTime();
		if(present<AwaitExamJPanel.dateStor){
			isFlag=true;
		}
		return isFlag;
	}
	//判断考试结束的时间
	
	//判断是否已经参加了考试
	public boolean reuslt() {
		boolean isFlag=false;
		String sql="select * from result where datetime BETWEEN '"+exam.getDate()+"' and '"+sf.format(new Date(AwaitExamJPanel.dateStor))+"'";
		List<Result>listresult=new ResultDaoImpl().ExecuteQueryBySql(sql);
		if(listresult.size()>0) {
			isFlag=false;
		}else {
			isFlag=true;
		}
		return isFlag;
		//查到之后为true
	}
	
	static String options="";
	
	//监听结束时间
	public void outdateTime() {
		new Thread(new Runnable() {
			public void run() {
				
				while(true) {
					if(n==5) {
						try {
							Thread.sleep(1000);
							if(AwaitExamJPanel.dateStor<=new Date().getTime()) {
								String sql="select s.* from student s where s.id not in(select stuid from result r where r.examname='"+exam.getName()+"' and datetime BETWEEN '"+exam.getDate()+"' and '2020-01-12 16:40:44') and s.cid="+exam.getClassid()+";";
								AwaitExamJPanel.listStudent=new StudentDaoImpl().<Student>ExecuteQueryBySql(sql);//查询没有参加考试的学生
								if(AwaitExamJPanel.listStudent.size()>0) {
									for(int i=0;i<exam.getCount();i++) {
										AwaitExamJPanel.options+="您没有选择*";
									}
									for(Student s:AwaitExamJPanel.listStudent) {
										AwaitExamJPanel.sqlStudentAll+="insert into result(stuid,examname,`option`,score,datetime) values("+s.getId()+",'"+exam.getName()+"','"+AwaitExamJPanel.options+"',0,'"+sf.format(new Date(AwaitExamJPanel.dateStor))+"');";
										
									}
									//System.out.println(AwaitExamJPanel.sqlStudentAll);
									new ResultDaoImpl().UpdateBySql(AwaitExamJPanel.sqlStudentAll);
									break;
								}else {
									break;
								}
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		
	}
}
