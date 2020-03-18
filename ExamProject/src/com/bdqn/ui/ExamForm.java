package com.bdqn.ui;

import java.awt.Color;
import java.awt.Component;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;


import com.bdqn.biz.ExamFormBiz;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Question;
import com.bdqn.data.bean.Result;

//考试
public class ExamForm extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int questionScore;//用来保存每道题的分数
	
	static String dateOutString;//获得考试结束的时间
	
	static Result result=new Result();//保存成绩
	
	
	
	private static JLabel jl1;//倒计时
	private static JLabel jl2;//倒计时
	private static JLabel jl3;//倒计时
	static int countScore=0;//每道题多个分
	static JPanel jpanel=new JPanel();//等待面板
	public JButton JButton;//提交
	
	static JPanel jPanelMaxConter;//把窗体的所有内容都包裹
	static JPanel jPanelNumber;//左边编号 可以点击右边内容会跳过去
	static JPanel jPanelExamConten;//考试内容
	protected JButton btnsubmit=new JButton();//提交考试
	static JPanel plMax;//保存所有的试题
	Map<String,String>stringOption=new HashMap<String,String>();//获取选项的集合
	
	static List<Question>listQuestion=new ArrayList<Question>();//保存所有根据试卷查出来的试题信息
	static Exampaper exam;//保存试卷的对象
	ExamFormBiz examBiz=new ExamFormBiz();
	static JScrollPane scrollPane;//滚动条
	static JButton jbtnSumit;
	static int datetime;//用来判断试卷不能提前提交
	
	List<JRadioButton>listRadio=new ArrayList<JRadioButton>();//保存所有的JRadioButton按钮
	public ExamForm() {
		
	}
	
	//i 为考试的编号 stuid 为时差
	public ExamForm( int i,int stuid,int time) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//隐藏窗体
		ExamForm.result.setStuid(stuid);
		this.setSize(1200,770);
		this.setLayout(null);
		this.setLocationRelativeTo(null);//窗体打开时居中
		this.setVisible(true);
		this.setBackground(Color.white);
		
		ExamForm.exam=examBiz.findExampaper(i);
		ExamForm.time=time;//考试的时长
		ExamForm.datetime=ExamForm.exam.getTime()*60-(ExamForm.exam.getTime()*20);
		
		ExamForm.questionScore=(int)(ExamForm.exam.getScore()/ExamForm.exam.getCount());//获得每道题的分数
		ExamForm.listQuestion=examBiz.findQuestion(exam.getQids());//获得所有试题
		//包裹所有的内容
		ExamForm.result.setExamname(ExamForm.exam.getName());//获得试卷名称
		ExamForm.jPanelMaxConter=new JPanel();
		ExamForm.jPanelMaxConter.setBounds(0,0,1200,770);
		ExamForm.jPanelMaxConter.setBackground(Color.white);
		ExamForm.jPanelMaxConter.setLayout(null);
		
		ExamForm.jPanelNumber=new JPanel();
		ExamForm.jPanelNumber.setBounds(5,100,300,580);
		ExamForm.jPanelNumber.setBackground(Color.white);
		ExamForm.jPanelNumber.setLayout(null);
		ExamForm.jPanelNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		//编号实例化
		ExamForm.jPanelMaxConter.add(ExamForm.jPanelNumber);
		//提交按钮
		ExamForm.jbtnSumit=new JButton("提交");
		ExamForm.jbtnSumit.setBounds(100,570,120,70);
		ExamForm.jbtnSumit.setBackground(Color.white);
		ExamForm.jbtnSumit.setFont(new Font("微软雅黑",1,24));

		examJPanel();
		ExamForm.jPanelMaxConter.add(ExamForm.jbtnSumit);
		
		this.add(ExamForm.jPanelMaxConter);
		this.setResizable(false);
		this.addWindowListener(new MyWindowLisitener());
		init();
		
	}
	//考试内容JPanel
	public void examJPanel() {
		ExamForm.jPanelExamConten=new JPanel();
		ExamForm.jPanelExamConten.setBounds(325,10,840,768);
		ExamForm.jPanelExamConten.setLayout(null);
		ExamForm.jPanelExamConten.setBackground(Color.white);
		//ExamForm.jPanelExamConten.setBorder(BorderFactory.createLineBorder(Color.black));
		
		dynamic();//获取试题
		ExamForm.jPanelExamConten.add(ExamForm.scrollPane);//右边最大
		ExamForm.jPanelMaxConter.add(ExamForm.jPanelExamConten);//最大
		
		//提交考试
		ExamForm.jbtnSumit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ExamForm.time>ExamForm.datetime) {
					JOptionPane.showMessageDialog(ExamForm.this,"考试前"+(((ExamForm.exam.getTime()*60)-ExamForm.datetime))/60+"分钟,不能交卷");
				}else if(submit()){
					subminResult();
					ExamForm.this.dispose();
					//刷新成绩！！！！
					
					StudentScore.STUDENTSCORE.removeAll();
					StudentScore.STUDENTSCORE.init();
					StudentScore.STUDENTSCORE.updateUI();
					
					//--------------------
				}
			}
		});
		jbutton();
		jRadioButton();
	}
	
	
	
	
	//提交考试
	public boolean submit() {
		boolean isFlag=false;
		String idName="";
		for(JButton btn:listIntger) {
			if(btn.getBackground().equals(Color.green)) {
				
			}else {
				idName+=btn.getText()+"、";
			}
		}
		if(idName.equals("")) {
			idName="确定提交考试?";
		}else {
			idName+="还有以上题没有做完,确定提交考试?";
		}
		int i=JOptionPane.showConfirmDialog(null,idName,"考试",JOptionPane.YES_NO_CANCEL_OPTION);
		if(i==0) {
			isFlag=true;
		}else if(i==1) {
			isFlag=false;
		}else {
			isFlag=false;
		}
		return isFlag;
	}
	
	//获得成绩
	public boolean subminResult() {
		Component items[]=plMax.getComponents();//获得所有的容器
		List<JPanel>listJPanel=new ArrayList<JPanel>();//底下所有的容器
		for(Component c:items) {
			if(c instanceof JPanel) {
				listJPanel.add((JPanel) c);//获得所有JPanel
			}
		}
		
		String option1="";//成绩中的选项
		for(JPanel jpanel:listJPanel) {
			Component []com=jpanel.getComponents();//获得每个面板的所有容器
			String title="";
			String option="";
			for(Component c:com) {
				if(c instanceof JTextArea) {
					JTextArea ti=(JTextArea)c;//获得没有容器下的标题
					title=ti.getText().substring(ti.getText().indexOf("、")+1);
				}
				if(c instanceof JRadioButton) {
					//获得每个容器下的按钮
					JRadioButton rbtnOption=(JRadioButton)c;
					if(rbtnOption.isSelected()) {
						option+=rbtnOption.getText().substring(0,1);
					}
				}
			}
			if(option.equals("")) {
				option="您没有选择";
				
			}
			option1+=option+"*";
			stringOption.put(title,option);
		}
		double score=0;
		for(int i=0;i<ExamForm.listQuestion.size();i++) {
			Question question=ExamForm.listQuestion.get(i);//获得对象
			Set<String>list=stringOption.keySet();
			for(String s:list ) {
				if(s.equalsIgnoreCase(question.getTitle())) {
			
					if(stringOption.get(s).equalsIgnoreCase(question.getSolution())) {
						score+=5;
					}else {
						break;
					}
				}
			}
		}
		ExamFormBiz s=new ExamFormBiz();
		JOptionPane.showMessageDialog(ExamForm.this,"考试完成，您的成绩为："+score);
		ExamForm.result.setScore(score);//获得分数
		ExamForm.result.setOption(option1); 
		if(s.doResult(ExamForm.result)) {
			
			return true;
		}
		return false;
	}
	List<Question>ls=null;

	//字体方法
	public Font typeface() {
		Font g=new Font("微软雅黑",1,18);
		return g;
	}

	List<JButton>listIntger=new ArrayList<JButton>();//保存左边所有的编号
	
	//动态加载试题
	public void dynamic() {
		int jpanelX=10;//控制每道题的x轴
		int jpanxlY=10;//控制每到题的Y轴
		ExamForm.plMax=new JPanel();
		ExamForm.plMax.setLayout(null);
		ExamForm.plMax.setPreferredSize(new Dimension(820,ExamForm.listQuestion.size()*211));
		ExamForm.plMax.setBackground(Color.white);
		ExamForm.plMax.setBounds(1,1,820,ExamForm.listQuestion.size()*205);
		for(int i=0;i<ExamForm.listQuestion.size();i++) {
			//根据每道题产生一个JPanel
			Question que=ExamForm.listQuestion.get(i);//获得集合中的每到Question
			JPanel jpanel=new JPanel();
			jpanel.setLayout(null);
			jpanel.setBounds(jpanelX, jpanxlY,807,200);
			jpanel.setBorder(BorderFactory.createLineBorder(Color.black));
			jpanel.setBackground(Color.white);
			
			JTextArea textAreaTitle=new JTextArea(i+1+"、"+que.getTitle());//获得标题
			textAreaTitle.setBounds(15,5,780,50);//标题的大小
			textAreaTitle.setFont(new Font("微软雅黑",0,18));
			textAreaTitle.setEditable(false);//不可编辑
			textAreaTitle.setLineWrap(true);
			textAreaTitle.setWrapStyleWord(true);
			jpanel.add(textAreaTitle);//保存到面板去
			
			
			String []option=que.getOption().split("\\*");//分割选项获得每个选项
			int optionY=55;//选项的Y轴
			ButtonGroup group=new ButtonGroup();//把单选按钮组合起来
			for(int j=0;j<option.length;j++) {//根据选项循环几次
				JRadioButton jrbtn=new JRadioButton(option[j]);//每个选项
				jrbtn.setBounds(30,optionY,765,24);
				jrbtn.setFont(new Font("微软雅黑",0,18));
				jrbtn.setBackground(Color.white);
				
				if(que.getTitle().indexOf("单选")>0) {//判断是否单选或多选
					group.add(jrbtn);
				}
				optionY+=30;
				jpanel.add(jrbtn);//把每个单选按钮放进Jpanel中
				listRadio.add(jrbtn);//把按钮放进集合中
				
			}
			jpanxlY+=210;
			ExamForm.plMax.add(jpanel);
		}
		
		ExamForm.jPanelNumber.setBounds(10,100,300,19*ExamForm.listQuestion.size());
		int numberX=30;
		int numberY=10;
		//左边的编号按钮
		int count=1;//用来计数 左边编号的个数
		for(int j=0;j<ExamForm.listQuestion.size();j++) {
			JButton jbtn=new JButton(j+1+"");
			jbtn.setFont(typeface());//获得字体
			jbtn.setBorder(BorderFactory.createLineBorder(Color.black));
			jbtn.setBackground(Color.white);
			jbtn.setBounds(numberX, numberY,50,50);
			listIntger.add(jbtn);
			count++;
			numberX+=65;
			if(count>4) {
				count=1;
				numberX=30;
				numberY+=70;
			}
		
			ExamForm.jPanelNumber.add(jbtn);
			
		}
		
		ExamForm.scrollPane= new JScrollPane(ExamForm.plMax);
		ExamForm.scrollPane.setBounds(1,1,840,680);
		ExamForm.scrollPane.getVerticalScrollBar().setUnitIncrement(20);//改变滚动条滑动的速度
		
//		jbutton();
//		jRadioButton();
		
	}
	
	List<JButton> listbrn=new ArrayList<JButton>();//左边编号的集合
	//按钮点击事件 发生考试内容变动
	public void jbutton() {
		Component items[]=ExamForm.jPanelNumber.getComponents();
		
		for(Component c:items) {
			if(c instanceof JButton) {
				listbrn.add((JButton)c); 
			}
		}
		for(JButton jbtn:listbrn) {
			jbtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JScrollBar hua=scrollPane.getVerticalScrollBar();
					hua.doLayout();
					hua.setValue((Integer.parseInt(jbtn.getText())-1)*190);
				}
				
			});
			jbtn.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
				}
				public void mouseEntered(MouseEvent arg0) {
					jbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					jbtn.setBorder(BorderFactory.createLineBorder(new Color(101,199,234)));
				}
				 
				public void mouseExited(MouseEvent arg0) {
					jbtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					jbtn.setBorder(BorderFactory.createLineBorder(Color.black));
				}
				public void mousePressed(MouseEvent arg0) {
				}
				public void mouseReleased(MouseEvent arg0) {
					
				}
				
			});
		}
	}
	
	//按钮对应
	List<String>listString=new ArrayList<String>();//所有的编号
	
	//按钮变色
	public void jRadioButton() {
		for(JRadioButton r:listRadio) {
			r.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JPanel p=(JPanel)r.getParent();//获得按钮的父容器
					Component s[]=p.getComponents();//获得父容器中的所有控件
					
					JButton btn=new JButton();
					String name="";
					
					for(Component tempcom:s) {
						if(tempcom instanceof JTextArea) {
							JTextArea textTitle=(JTextArea)tempcom;
							name=textTitle.getText().substring(0,textTitle.getText().indexOf("、"));
						}
					}
					for(JButton j:listbrn){
						if(j.getText().equals(name)) {
							btn=j;
							break;
						}
					}
					if(r.isSelected()) {//判断按钮是否被选中
						btn.setBackground(Color.green);
					}else {
						for(Component tempcom:s) {
							if(tempcom instanceof JRadioButton) {
								JRadioButton jrbtn=(JRadioButton)tempcom;
								if(jrbtn.isSelected()) {
									return;
								}
							}
						}//主要判断按钮还是是否选中
						btn.setBackground(Color.white);
					}
				}
			});
		}
		
	}
	
	//单击事件
	
	int ci=0;
	private void init() {
		ExamForm.jl1=new JLabel();
		ExamForm.jl2=new JLabel();
		ExamForm.jl3=new JLabel();
		//jPanel.add(jl1);
		ExamForm.jPanelMaxConter.add(jl2);
		jl2.setBounds(120,10,100,100);
		ExamForm.jPanelMaxConter.add(jl3);
		jl3.setBounds(170,10,100,100);
		new Thread(new Runnable() {
			@Override
			public void run() {
				getTime();
			}
		}).start();;
	}
	static long time;
	private void getTime() {
		long hour = 0;
		long minute = 0;
		long seconds = 0;
		while (time >=0) {
			//hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			jl1.setText(hour + "时");
			jl1.setFont(new Font("微软雅黑",1,25));
			jl2.setText(minute + "分");
			jl2.setFont(new Font("微软雅黑",1,25));
			jl3.setText(seconds + "秒");
			jl3.setFont(new Font("微软雅黑",1,25));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
				if(time<=(exam.getTime()*60)*0.3&&ci==0) {
					JOptionPane.showMessageDialog(ExamForm.this,"考试马上结束了!");
					ci++;
				}
				if(ExamForm.time==-1) {
					subminResult();//结束成绩	
					ExamForm.this.dispose();
					
				}
		}
	}



	//窗体被关闭时触发
	class MyWindowLisitener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
		}
		@Override
		public void windowClosing(WindowEvent e) {
			int result=JOptionPane.showConfirmDialog(null,"退出本窗体将会结束考试?","Information",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_NO_OPTION) {
				subminResult();
				new AwaitExamJPanel().outdateTime();
				ExamForm.this.dispose();
			}else {
				
			}
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		@Override
		public void windowIconified(WindowEvent e) {
		}
		@Override
		public void windowOpened(WindowEvent e) {
			
		}
	}
}


