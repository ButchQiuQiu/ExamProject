package com.bdqn.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

import com.bdqn.biz.StartExamBiz;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Major;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;

/*
 * 开始考试
 */
@SuppressWarnings("serial")
public class StartExam extends MainBusinessPanel {

	public StartExam() {
		JLabel jlDate = new JLabel("开考时间：");
		jlDate.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
		jlDate.setBounds(230,120,150,30);
		this.add(jlDate);
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner year = new JSpinner(model);
        year.setValue(new Date());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(year,
                "yyyy-MM-dd HH:mm:ss");
        year.setEditor(editor);
        year.setBounds(380, 120, 350, 30);
        this.add(year);
		
		JLabel jlMajor = new JLabel("专业：");
		jlMajor.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
		jlMajor.setBounds(230,190,150,30);
		this.add(jlMajor);
		
		JComboBox<String> jcbMajor=new JComboBox<String>();//创建专业下拉列表
		List<Major>  majors=new ArrayList<Major>();
		majors=new MajorDaoImpl().ExecuteQueryBySql("select * from "+Major.tablename);
		List<String> majornames=new ArrayList<String>();
		for(Major tempMajor:majors) {
			majornames.add(tempMajor.getName());
		}
		jcbMajor.setModel(new DefaultComboBoxModel<String>(majornames.toArray(new String[majornames.size()])));
		jcbMajor.setOpaque(false);
		jcbMajor.setBounds(380,190,350,30);
		this.add(jcbMajor);
		
		
		JLabel jlClasses = new JLabel("考试班级：");
		jlClasses.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
		jlClasses.setBounds(230,260,150,30);
		this.add(jlClasses);
		
		JComboBox<String> jcbClasses = new JComboBox<>();//创建考试班级下拉列表
		List<Classes> classess = new ArrayList<Classes>();
		classess = new ClassesDaoImpl().ExecuteQueryBySql("select * from "+Classes.tablename+" where mid="+majors.get(0).getId());
		List<String> classesnames = new ArrayList<String>();
		for(Classes tempClasses:classess) {
			classesnames.add(tempClasses.getName());
		}
		jcbClasses.setModel(new DefaultComboBoxModel<String>(classesnames.toArray(new String[classesnames.size()])));
		jcbClasses.setOpaque(false);
		jcbClasses.setBounds(380,260,350,30);
		this.add(jcbClasses);
		
		
		JLabel jlExampaper = new JLabel("试卷：");
		jlExampaper.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
		jlExampaper.setBounds(230,330,150,30);
		this.add(jlExampaper);
		
		JComboBox<String> jcbExampaper = new JComboBox<>();//创建试卷下拉列表
		List<Exampaper> exampapers = new ArrayList<Exampaper>();
		exampapers = new ExampaperDaoImpl().ExecuteQueryBySql("select * from "+Exampaper.tablename+""
				+ " where classid=ANY (select id from "+Classes.tablename+" where name='"+jcbClasses.getItemAt(0)+"')");
		
		if(exampapers.size()!=0) {
			List<String> exampapernames = new ArrayList<String>();
			for(Exampaper tempExampaper:exampapers) {
			exampapernames.add(tempExampaper.getName());
			}
			jcbExampaper.setModel(new DefaultComboBoxModel<String>(exampapernames.toArray(new String[exampapernames.size()])));
		}
		jcbExampaper.setOpaque(false);
		jcbExampaper.setBounds(380,330,350,30);
		this.add(jcbExampaper);

	
		JLabel jlTime = new JLabel("考试用时：");
		jlTime.setBounds(230,400,150,30);
		jlTime.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
        this.add(jlTime);
        
        JTextField jtTime = new JTextField();//设置文本框
        jtTime.setBounds(380,400,280,30);
        jtTime.setFont(new Font("微软雅黑",Font.PLAIN,20));
        jtTime.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyChar=e.getKeyChar();
			if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
			} else {
			e.consume();
			}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			}});
        this.add(jtTime);
        
        JLabel jlMinute = new JLabel("分钟");
        jlMinute.setBounds(680,400,150,30);
        jlMinute.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,25));
        this.add(jlMinute);
		
        
		JButton btn=new JButton("确认");
		btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
		btn.setBounds(450, 490, 100, 40);
		btn.addActionListener(new ActionListener() { //监听
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!jtTime.getText().equals("")) {
					if(Integer.parseInt(jtTime.getText())>0&&Integer.parseInt(jtTime.getText())<=150) {
						if(jcbExampaper.getSelectedItem()==null) {
							MainDialog.DefaultMessage("当前班级没有试卷");
							return;
						}
						StartExamBiz.addEaxmpaperTime(((DefaultEditor) year.getEditor()).getTextField().getText(), jcbClasses.getSelectedItem().toString(), 
								jcbExampaper.getSelectedItem().toString(), jtTime.getText());
					}else {
						new MainDialog().showMessageDialog(null, "考试时长范围为1~150分钟！");
					}
				}else {
					new MainDialog().showMessageDialog(null, "考试时长不能为空！");
				}
			}
		});
		this.add(btn);
		
		
		//下拉列表事件
		jcbMajor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//jcbExampaper.removeAllItems();
				StartExamBiz.SelectComboBox(jcbMajor, jcbClasses, jcbExampaper);
				
			}
		});
		
		//下拉列表事件
		jcbClasses.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				StartExamBiz.SelectComboBox(null, jcbClasses, jcbExampaper);
			}
		});
	}

}
