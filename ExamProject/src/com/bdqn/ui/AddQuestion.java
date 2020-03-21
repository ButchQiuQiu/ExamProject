package com.bdqn.ui;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.bdqn.biz.AddQuestionBiz;
import com.bdqn.data.bean.Book;
import com.bdqn.data.bean.Chapter;
import com.bdqn.data.bean.Major;
import com.bdqn.data.bean.Question;
import com.bdqn.data.impl.BookDaoImpl;
import com.bdqn.data.impl.ChapterDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;

// 教师之录入试题
@SuppressWarnings("serial")
public class AddQuestion extends MainBusinessPanel {
	

	public AddQuestion() {
		lblTitle.setBounds(50,50,200,60);
		lblTitle.setFont(new Font("微软雅黑",1,30));
		jp.add(lblTitle);
		init();//初始化数据
		jpl=addJpanel();
		
		cboMajor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				AddQuestionBiz.SelectComboBox(cboMajor,cbobook,cbosection);
			}
			
		});
		
		cbobook.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				AddQuestionBiz.selecComboBoxChapter(cbobook,cbosection);
			}
		});
		this.add(jp);
		this.add(jpl);
		ss();
		clear();
		
	}
		
	//非空方法
	public boolean isNotNull() {
		if(textTitle.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(AddQuestion.this,"请输入题目标题");
			textTitle.requestFocus();//获取焦点
			return false;
		}else if(textoptionA.getText().trim().equals("")){
			JOptionPane.showMessageDialog(AddQuestion.this,"请输入选项");
			textoptionA.requestFocus();
			return false;
		}else if(textArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(AddQuestion.this,"请输入答案");
			textArea.requestFocus();
			return false;
		}else  if(textArea1.getText().trim().equals("")){
			JOptionPane.showMessageDialog(AddQuestion.this,"请输入解析");
			textArea1.requestFocus();
			return false;
		}else {
			return true;
		}
	}
	
	 JPanel jp=new JPanel();//容器//最大的
	 JLabel lblTitle=new JLabel("录入题目");
	 JPanel jpl=new JPanel();//中间内容
	
	 JLabel lbltitle=new JLabel("题目：");//题目
	 TextArea textTitle=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//题目文本框
	 JLabel lbloption=new JLabel("选项：");
	 TextArea textoptionA=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 JScrollPane jsoption=new JScrollPane();
	 
	 JLabel lblArea=new JLabel("答案：");
	 TextArea textArea=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 
	 JLabel lbljx=new JLabel("解析：");
	 TextArea textArea1=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 
	 JLabel lblmajor=new JLabel("专业：");
	 JComboBox<String> cboMajor;
	 
	 JLabel lblbook=new JLabel("书目：");
	 String str[]= {"请选择","第一章节"};
	 JComboBox<String> cbobook;
	 
	 JLabel lblsetion=new JLabel("章节");
	 JComboBox<String> cbosection;
	 
	 JButton btnSubmit=new JButton("提交:");
	 JButton btnEmpty=new JButton("清空:");
	
	 //初始化
	 public void init() {
		 
		 List<Major>listMajor=new MajorDaoImpl().ExecuteQueryBySql("select * from "+Major.tablename+" order by id");
		 List<String>majorName=new ArrayList<String>();
		 //majorName.add("请选择");
		 for(Major m:listMajor) {
			 majorName.add(m.getName());
		 }
		 cboMajor=new JComboBox<String>(majorName.toArray(new String[majorName.size()]));
		 
		 List<Book>listBok=new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" order by id");//获取所有的书目
		 List<String>bookString=new ArrayList<String>();
		 //bookString.add("请选择");
		 for(Book b:listBok) {
			 bookString.add(b.getName());//获得所有书目的名称
		 }
		 cbobook=new JComboBox<String>(bookString.toArray(new String[bookString.size()]));
		 
		 
		 List<Chapter>listChapter=new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" order by id");//获取所有的章节
		 List<String>chapterString=new ArrayList<String>();
		 //chapterString.add("请选择");
		 for(Chapter b:listChapter) {
			 chapterString.add(b.getName());//获得所有书目的名称
		 }
		 cbosection=new JComboBox<String>(chapterString.toArray(new String[chapterString.size()]));
		 
	 }
	
	 	public JPanel addJpanel() {
		jpl.setBounds(200,40,750,640);
		jpl.setLayout(null);
		jpl.setOpaque(false);
		
		JLabel lbltitle=new JLabel("题目：");//题目
		lbltitle.setBounds(10,20,60,40);//题目坐标及大小
		lbltitle.setFont(typeface());
		
		textTitle.setFont(typeface());
		textTitle.setBounds(80,13,550,60);//题目坐标及大小
		textTitle.setColumns(5);
		
		
		//选项
		lbloption.setFont(typeface());//获取字体
		lbloption.setBounds(10,100,60,40);
		
		//选项文本框
		
		//选项文本框坐标及大小
		textoptionA.setBounds(80,100,550,100);
		
		//选项文本框样式
		textoptionA.setFont(typeface());
		
		
		//答案

		lblArea.setFont(typeface());//获得字体样式
		lblArea.setBounds(10,226,60,40);
		
		//答案文本框

		textArea.setBounds(80,230,550,40);
		textArea.setFont(typeface());
		//不显示边框
		//解析

		
		lbljx.setFont(typeface());//获得字体样式
		lbljx.setBounds(10,276,60,40);
		
		//解析文本框

		textArea1.setBounds(80,280,550,80);
		textArea1.setFont(typeface());
		textArea1.setColumns(5);
		
		
		//专业
		
		lblmajor.setFont(typeface());
		lblmajor.setBounds(10,388,60,40);
		
		//专业下拉框
		
		cboMajor.setFont(typeface());
		cboMajor.setEditable(false);
		cboMajor.setBounds(80,390,550,40);
		
		
		//书目
		
		
		lblbook.setFont(typeface());
		lblbook.setBounds(10,448,60,40);
		
		//书目下拉框

		cbobook.setFont(typeface());
		cbobook.setEditable(false);
		cbobook.setBounds(80,450,550,40);
		
		//章节
		
		lblsetion.setFont(typeface());
		lblsetion.setBounds(10,508,60,40);
		
		//章节下拉框
		
		
		cbosection.setFont(typeface());
		cbosection.setEditable(false);
		cbosection.setBounds(80,510,550,40);
		
		//提交
	
		
		btnSubmit.setFont(typeface());
		btnSubmit.setBounds(250,580,80,40);
		
		//清空
		
		btnEmpty.setFont(typeface());
		btnEmpty.setBounds(360,580,80,40);
		
		List<String>listtext=new ArrayList<String>();
		
		listtext.add(textTitle.getText());//标题
		listtext.add(textoptionA.getText());//选项
		listtext.add(textArea.getText());//答案
		listtext.add(textArea1.getText());//解析
		listtext.add(cboMajor.getSelectedItem().toString());//专业下拉框
		listtext.add(cbobook.getSelectedItem().toString());//书目
		listtext.add(cbosection.getSelectedItem().toString());//章节
		jpl.add(lbljx);
		jpl.add(textArea1);
		jpl.add(btnEmpty);
		jpl.add(btnSubmit);
		jpl.add(lblsetion);
		jpl.add(cbosection);
		jpl.add(cbobook);
		jpl.add(lblbook);
		jpl.add(lblmajor);
		jpl.add(cboMajor);
		jpl.add(lblArea);
		jpl.add(textArea);
		jpl.add(textoptionA);//选项
		
		jpl.add(lbloption);
		jpl.add(lbltitle);
		jpl.add(textTitle);
		
		
		return jpl;
	}
	
	
	
		//字体方法
		public Font typeface() {
			Font g=new Font("微软雅黑",1,18);
			return g;
		}
	



	public  void ss() {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNotNull()) {
					Question q=new Question();
					q.setTitle(textTitle.getText());//标题
					q.setOption(textoptionA.getText());//选项
					q.setSolution(textArea.getText());//答案
					q.setAnalysis(textArea1.getText());//解析
					String ca=cbosection.getSelectedItem().toString();
					q.setChapid(AddQuestionBiz.chapter(ca));
					AddQuestionBiz.addQuestion(q);
				}
			}
		});
	}

	public void clear() {
		btnEmpty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chear1();
			}
		});
	}
	 
	public  boolean chear1() {
		if(!(textTitle.getText().trim().equals(""))) {
			textTitle.setText("");
		}
		if(!(textoptionA.getText().trim().equals(""))){ 
			textoptionA.setText("");
		}
		if(!(textArea.getText().trim().equals(""))) {
			textArea.setText("");
		}
		if(!(textArea1.getText().trim().equals(""))){
			textArea1.setText("");
		}
		
		init();
		return true;
	}
	
}
