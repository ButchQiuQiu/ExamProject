package com.bdqn.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.bdqn.biz.SelectResultBiz;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Result;
import com.bdqn.data.bean.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



//查看成绩
public class SelectResultForm extends MainBusinessPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JPanel plLeft=new JPanel();//左边
		//成绩显示JPanel
		JPanel plResult=new JPanel();//显示成绩
		JScrollPane js;
		//左边显示初始化
	public SelectResultForm() {
		//左边JPanel
		plLeft.setBounds(30,0,970,130);//改换成顶部
		plLeft.setLayout(null);
		plLeft.setOpaque(false);
		this.add(plLeft);
		section();//焦点事件
		plResult.setBounds(30,150,970,550);
		plResult.setLayout(null);
		//plResult.setBorder(BorderFactory.createLineBorder(Color.black));
		data(textsec.getText());//获得数据
		js=new JScrollPane(plMax);//出现滚动条
		js.setOpaque(false);
		js.setBackground(Color.white);
		js.setBounds(40,30,900,500);
		plResult.add(js);
		plResult.setOpaque(false);
		this.add(plResult);
	}//初始化 一个JPanel
	
	
	JTextField textsec=new JTextField(20);
	public void section() {
		textsec.setBounds(200,50,200,30);
		textsec.setFont(font());
		textsec.setBorder(BorderFactory.createLineBorder(Color.black));
		plLeft.add(textsec);
		this.textsec.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtInput_KeyTyped(arg0);
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		JButton btnOut=new JButton("查找");
		btnOut.setFont(font());//获得字体
		btnOut.setBounds(500,40,150,50);
		btnOut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnOut.setContentAreaFilled(false);
		plLeft.add(btnOut);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plMax.removeAll();
				plMax.repaint();
				load(jPanelconent(textsec.getText()));//重新加载成绩'
				plMax.repaint();
				plMax.updateUI();
				//plMax.revalidate();
			}
		});
	}
	
	//刷新组件
	List<Result>listResult=new ArrayList<Result>();
	
	public List<Result> jPanelconent(String name) {
		//筛选数据
		if(name.equals("")) {
			return listResult;//返回全部
		}else {
			int id=Integer.parseInt(name);
			List<Result>listlin=new ArrayList<Result>();
			for(Result result:listResult) {
				if(result.getStuid()==id) {
					listlin.add(result);
				}
			}
			return listlin;
		}
		
	}
	
	//只能输入数字
	public void txtInput_KeyTyped(KeyEvent ke){
		if(ke.getKeyChar() < '0' || ke.getKeyChar() > '9'){
			ke.setKeyChar('\0');
		}
	}
	
	//字体样式
	public Font font() {
		Font f=new Font("微软雅黑",1,18);
		return f;
	}
	
	
	JPanel plMax=new JPanel();
	
	//最大

	//获得数据
	public void data(String name) {
		SelectResultBiz selectResult=new SelectResultBiz();
		listResult=selectResult.findResult(name);//获得所有的成绩
		if(listResult.size()>0) {
			load(listResult);
		}else {
			textsec.setText("");
			textsec.requestFocus();
			jPanelconent(textsec.getText());
		}
	
	}
	
	//加载数据
	@SuppressWarnings("deprecation")
	public void load(List<Result>lresult) {
		plMax.setLayout(null);
		plMax.setPreferredSize(new Dimension(600,100*lresult.size()));
		JPanel plTop=new JPanel();
		plTop.setLayout(null);
		plTop.setBounds(10,5,850,70);
		plTop.setBorder(BorderFactory.createLineBorder(Color.black));
		
		String[] lie= {"人数","学号","姓名","班级","考试","成绩","时间"};
		int x1=20;
		for(int k=0;k<lie.length;k++) {
			JLabel lbl=new JLabel(lie[k]);
			lbl.setFont(font());//获取字体
			lbl.setBounds(x1,-6,130,80);
			plTop.add(lbl);
			if(lie[k].equals("班级")) {
				x1+=170;
			}else {
				x1+=105;
			}
			if(lie[k].equals("考试")) {
				x1+=45;
			}
		}
		int x=10;
		int y=120;
		int width=850;
		int height=80;
		int count=1;
		
		for(Result s:lresult) {
			JPanel pl=new JPanel();
			int x2=30;
			pl.setLayout(null);
			pl.setBounds(x,y, width, height);
			pl.setBorder(BorderFactory.createLineBorder(Color.black));
			//pl.setBackground(Color.white);
			pl.setFont(font());
			
			SelectResultBiz sResult=new SelectResultBiz();//获得参加学生的信息
			Student student=sResult.findStudent(s.getStuid());//根据学好查看该学生信息
			
			//Exampaper exam=sResult.findExampaper(s.getEid());//获得考试信息
			
			Classes classes=sResult.findClasses(student.getCid());//获得年级信息
			
			JLabel lblCount=new JLabel(count+"");//人数
			lblCount.setBounds(x2, 0,130,80);
			lblCount.setFont(font());
			count++;
			JLabel lbl=new JLabel(student.getId()+"");//学号
			lbl.setFont(font());
			lbl.setBounds(x2+=80,0,130,80);
			JLabel lbl1=new JLabel(student.getName());//姓名
			lbl1.setFont(font());
			lbl1.setBounds(x2+=120,0,130,80);
			
			JLabel lbl2=new JLabel(classes.getName());//班级
			lbl2.setFont(font());
			lbl2.setBounds(x2+=100,0,130,80);
			
			JLabel lblExam=new JLabel(s.getExamname());//考试名称
			lblExam.setFont(font());
			lblExam.setBounds(x2+=130,0,230,80);
			
			JLabel lblResult=new JLabel(s.getScore()+"");//成绩
			lblResult.setFont(font());
			lblResult.setBounds(x2+=190,0,230,80);
			
			JLabel lbltime=new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date(s.getDatetime())));//
			lbltime.setFont(font());
			lbltime.setBounds(x2+=80,0,230,80);
			
			pl.add(lblCount);//统计人数
			pl.add(lbl);//学号
			pl.add(lbl1);//姓名
			pl.add(lbl2);//班级
			pl.add(lblExam);//考试名称
			pl.add(lblResult);
			pl.add(lbltime);//时间
			plMax.add(pl);//
			y+=90;
		}
		plMax.add(plTop);//顶部
		plMax.setOpaque(false);
		
	}
	//鼠标方法
}


