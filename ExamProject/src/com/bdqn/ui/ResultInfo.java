package com.bdqn.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.bdqn.biz.ResultInfoBiz;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Question;
import com.bdqn.data.bean.Result;
import com.bdqn.data.bean.Student;


//成绩的详细信息
public class ResultInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JPanel jPanelNumber;//左边的编号
	static JPanel jPanelResultConent;//成绩内容
	static JScrollPane jScroConent;//内容滚动
	static JPanel jPanelQuestionMax;//滚动条里的每到题的总和
	static List<Question>listQuestion;
	
	JTextArea textInfo;//存放详细信息
	
	Student stu;//学生信息
	Classes classes;//班级班级
	Exampaper exam;//考试信息
	ResultInfoBiz resultBiz=new ResultInfoBiz();
	
	//存放对应的编号的解析
	static Map<Integer,String>listAnalysis=new HashMap<Integer,String>();

	static List<JButton>listButton=new ArrayList<JButton>();//存放所有的按钮
	static List<JButton>listButtonAnalysis=new ArrayList<JButton>();//获得所有的解析的按钮
	String option[];
	//点击需要获得一些数据
	//listResult 为试题  result 为成绩信息
	public ResultInfo(List<Question>listResult,Result result) {
		//j.setEnabled(false);//设置父窗体不可被操作
		
		this.option=result.getOption().split("\\*");//获得选项
		ResultInfo.listQuestion=listResult;//获得想要的数据
		this.setSize(1200,770);//大小
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setBackground(Color.white);
		ResultInfo.jPanelNumber=new JPanel();//实例化
		ResultInfo.jPanelNumber.setBounds(1,1,300,768);
		ResultInfo.jPanelNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		ResultInfo.jPanelNumber.setLayout(null);
		ResultInfo.jPanelNumber.setBackground(Color.white);
		jPanelReuslt();
		
		
		stu=resultBiz.findStudent(result.getStuid());//获得学生信息
		classes=resultBiz.findClasses(stu.getCid());//获得学生班级的信息
		exam=resultBiz.findExampaper(result.getExamname());//获得试卷的信息
		//存放学生的详细信息
		textInfo=new JTextArea("考试："+exam.getName()+"\n时间："+exam.getDate()+"\n班级："+classes.getName()+"\n姓名："+stu.getName()+"\n成绩："+result.getScore());
		textInfo.setBounds(30,500,250,200);
		textInfo.setEditable(false);//不可编辑
		textInfo.setLineWrap(true);
		textInfo.setWrapStyleWord(true);
		textInfo.setBackground(Color.white);
		textInfo.setFont(new Font("微软雅黑",1,18));
		
		this.add(textInfo);
		this.setResizable(false);
		this.add(ResultInfo.jPanelNumber);
		this.setVisible(true);
	}
	
	//初始化成绩内容并加载
	public void jPanelReuslt() {
		ResultInfo.jPanelResultConent=new JPanel();//实例化
		ResultInfo.jPanelResultConent.setLayout(null);//绝对布局
		ResultInfo.jPanelResultConent.setBackground(Color.white);
		ResultInfo.jPanelResultConent.setBounds(301,1,880,768);
		//初始化成绩面板
		
		//初始化滚动条里的内容
		jPanelQuestion();
		
		//初始化滚动条
		ResultInfo.jPanelResultConent.add(ResultInfo.jScroConent);//给成绩内容添加滚动条
		this.add(ResultInfo.jPanelResultConent);
	}
	
	//加载试题
	public void jPanelQuestion() {
		ResultInfo.jPanelQuestionMax=new JPanel();
		ResultInfo.jPanelQuestionMax.setLayout(null);
		ResultInfo.jPanelQuestionMax.setPreferredSize(new Dimension(830,230*ResultInfo.listQuestion.size()));
		ResultInfo.jPanelQuestionMax.setBounds(10,5,830,230*ResultInfo.listQuestion.size());//动态大小
		ResultInfo.jPanelQuestionMax.setBackground(Color.white);
		int jPanelY=5;
		JLabel jlblAnswer=null;
		JLabel jlblOption=null;
		for(int i=0;i<ResultInfo.listQuestion.size();i++) {//每一个试题创建一个面板
			Question q=new Question();//试题的每个对象
			q=ResultInfo.listQuestion.get(i);
			//每到的题的面板
			JPanel jPanelQuestion=new JPanel();
			jPanelQuestion.setLayout(null);
			jPanelQuestion.setBorder(BorderFactory.createLineBorder(Color.black));
			jPanelQuestion.setBounds(1,jPanelY,825,220);//每到题的大小
			jPanelQuestion.setBackground(Color.white);
			//初始化每道题的大小
			
			ResultInfo.listAnalysis.put(q.getId(),q.getAnalysis());//获得试题的编号及解析
			
			//每道题的标题初始化
			String title=q.getTitle();
			JTextArea jtextTitle=new JTextArea(i+1+"、"+title);
			jtextTitle.setBounds(15,5,780,50);//标题的大小
			jtextTitle.setFont(new Font("微软雅黑",0,18));
			jtextTitle.setEditable(false);//不可编辑
			jtextTitle.setLineWrap(true);
			jtextTitle.setWrapStyleWord(true);
			//保存标题保存到每个面板中去
			jPanelQuestion.add(jtextTitle);
			
			String []option=q.getOption().split("\\*");//分割选项
			JRadioButton rbtnOption[]=new JRadioButton[option.length];//获得选项
			int optionY=55;//选项的Y轴
			
			//组合框
			ButtonGroup group=new ButtonGroup();
			
			for(int j=0;j<rbtnOption.length;j++) {//初始化选项
				rbtnOption[j]=new JRadioButton(option[j]);//获得选项
				rbtnOption[j].setBounds(30,optionY,780,24);
				rbtnOption[j].setFont(new Font("微软雅黑",0,18));//字体的样式
				rbtnOption[j].setBackground(Color.white);
				if(q.getTitle().indexOf("单选")>0) {
					group.add(rbtnOption[j]);
				}//判断试题是否为单选或多选
				optionY+=30;
				//保存到面板中去
				rbtnOption[j].setEnabled(false);
				rbtnOption[j].setForeground(Color.black);
				jPanelQuestion.add(rbtnOption[j]);//把每个选项保存到面板中去
				
				if(this.option[i].equalsIgnoreCase("您没有选择")) {//把选中的选项对应按钮中
					
				}else {
					for(int k=0;k<this.option[i].length();k++) {
						if(this.option[i].substring(k,k+1).equalsIgnoreCase(rbtnOption[j].getText().substring(0,1))) {
							rbtnOption[j].setSelected(true);
						}
					}
				}
			}
			
			//选项对比及解析
			jlblOption=new JLabel("您选项为："+this.option[i]);
			jlblOption.setFont(new Font("微软雅黑",0,18));
			jlblOption.setBackground(Color.white);
			jlblOption.setBounds(20,170,200,40);
			
			//答案
			jlblAnswer=new JLabel("答案为："+q.getSolution());
			jlblAnswer.setFont(new Font("微软雅黑",0,18));
			jlblAnswer.setBackground(Color.white);
			jlblAnswer.setBounds(250,170,200,40);
			jlblAnswer.setForeground(Color.black);
			//解析
			JButton jbtnAnalysis=new JButton("解析:");
			jbtnAnalysis.setFont(new Font("微软雅黑",0,18));
			jbtnAnalysis.setBackground(Color.white);
			jbtnAnalysis.setBounds(720,170,80,40);
			
			
			if(this.option[i].equalsIgnoreCase(ResultInfo.listQuestion.get(i).getSolution())){
				jlblOption.setForeground(Color.green);
			}else {
				jlblOption.setForeground(Color.red);
			}
			ResultInfo.listButtonAnalysis.add(jbtnAnalysis);//把解析存进去集合中 后期有用
			
			//选项
			//把选项、答案及解析放进面板中
			jPanelQuestion.add(jlblOption);
			jPanelQuestion.add(jlblAnswer);
			jPanelQuestion.add(jbtnAnalysis);
			jPanelY+=230;
			ResultInfo.jPanelQuestionMax.add(jPanelQuestion);
		}
		int optionNumberX=30;//左边编号的X轴
		int optionNumberY=60;//左边编号的Y轴
		int count=1;//编号个数
		for(int i=0;i<ResultInfo.listQuestion.size();i++) {
			JButton jbtn=new JButton(i+1+"");
			jbtn.setFont(new Font("微软雅黑",0,18));
			jbtn.setBorder(BorderFactory.createLineBorder(Color.black));//边框黑色 
			if(this.option[i].equalsIgnoreCase(ResultInfo.listQuestion.get(i).getSolution())) {
				jbtn.setBackground(Color.green);
			}else {
				jbtn.setBackground(Color.red);//背景红色
			}
			jbtn.setBounds(optionNumberX, optionNumberY,40,40);//大小及坐标
			ResultInfo.jPanelNumber.add(jbtn);//把每个编号放进左边的面板中
			count++;
			optionNumberX+=65;
			if(count>=5) {
				count=1;
				optionNumberX=30;
				optionNumberY+=65;
			}
			ResultInfo.listButton.add(jbtn);
		}
		
		
		
		//放进滚动条
		ResultInfo.jScroConent=new JScrollPane(ResultInfo.jPanelQuestionMax);
		ResultInfo.jScroConent.setBounds(10,10,850,680);
		ResultInfo.jScroConent.getVerticalScrollBar().setUnitIncrement(20);//改变滚动条的速度
		//实例化试题的总和
		jbutton();
		buttonAnalysis();
	}
	
	//编号跳转效果
	public void jbutton() {
		Component itmes[]=ResultInfo.jPanelNumber.getComponents();//获得左边所有按钮
		for(Component c:itmes) {
			if(c instanceof JButton) {
				ResultInfo.listButton.add((JButton) c);//强转
			} 
		}
		for(JButton jbtn:ResultInfo.listButton) {
			jbtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JScrollBar hua=ResultInfo.jScroConent.getVerticalScrollBar();//获得高度
					hua.doLayout();
					hua.setValue((Integer.parseInt(jbtn.getText())-1)*230);
				}
			});
		}
	}
	
	//解析方法
	public void buttonAnalysis() {
		for(JButton jbtn:ResultInfo.listButtonAnalysis) {
			jbtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JPanel jPanel=(JPanel) jbtn.getParent();//获得父容器
					Component itmes[]=jPanel.getComponents();//在获得旗下的子容器
					JTextArea jtextArea=null;
					for(Component c:itmes) {
						if(c instanceof JTextArea){
							jtextArea=(JTextArea)c;//获得标题
						}
					}
					
					String title=jtextArea.getText().substring(jtextArea.getText().indexOf("、")+1);
					ResultInfoBiz result=new ResultInfoBiz();
					String text=result.findAnalysis(title);
					new AnalysisDialog(new ResultInfo(),text);
					
				}
			});
		}

	}
	
	public ResultInfo() {
		
	}
	

}
