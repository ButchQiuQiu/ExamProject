package com.bdqn.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.bdqn.biz.EditExamPaperBiz;
import com.bdqn.data.bean.Book;
import com.bdqn.data.bean.Chapter;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Major;
import com.bdqn.data.impl.BookDaoImpl;
import com.bdqn.data.impl.ChapterDaoImpl;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;

/*
 * �����Ծ�
 * 
 */
@SuppressWarnings("serial")
public class EditExamPaper extends MainBusinessPanel {


	public EditExamPaper() {
		JLabel jlExampaperId = new JLabel("�Ծ�ID��");
		jlExampaperId.setBounds(230,150,150,30);
		jlExampaperId.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
        this.add(jlExampaperId);
        
        JTextField jtExampaperId = new JTextField();//�����ı���
        jtExampaperId.setBounds(380,150,350,30);
        jtExampaperId.setFont(new Font("΢���ź�",Font.PLAIN,20));
        jtExampaperId.addKeyListener(new KeyListener() {

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
        this.add(jtExampaperId);
        
        
        JLabel jlExampaperName = new JLabel("�Ծ����֣�");
        jlExampaperName.setBounds(230,200,150,30);
        jlExampaperName.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
        this.add(jlExampaperName);
		
        JTextField jtExampaperName = new JTextField();//�����ı���
        jtExampaperName.setBounds(380,200,350,30);
        jtExampaperName.setFont(new Font("΢���ź�",Font.PLAIN,20));
        this.add(jtExampaperName);
        
        
        JLabel jlTotal = new JLabel("�ܷ֣�");
        jlTotal.setBounds(230,250,150,30);
        jlTotal.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
        this.add(jlTotal); 
		
        JTextField jtTotal = new JTextField();//�����ı���
        jtTotal.setBounds(380,250,350,30);
        jtTotal.setFont(new Font("����",Font.PLAIN,20));
        jtTotal.addKeyListener(new KeyListener() {

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
        this.add(jtTotal);
        
        
		JLabel jlMajor = new JLabel("רҵ��");
		jlMajor.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
		jlMajor.setBounds(230,300,150,30);
		this.add(jlMajor);
		
		JComboBox<String> jcbMajor=new JComboBox<String>();//����רҵ�����б�
		List<Major>  majors=new ArrayList<Major>();
		majors=new MajorDaoImpl().ExecuteQueryBySql("select * from "+Major.tablename);
		List<String> majornames=new ArrayList<String>();
		for(Major tempMajor:majors) {
			majornames.add(tempMajor.getName());
		}
		jcbMajor.setModel(new DefaultComboBoxModel<String>(majornames.toArray(new String[majornames.size()])));
		jcbMajor.setOpaque(false);
		jcbMajor.setBounds(380,300,350,30);
		this.add(jcbMajor);
		
		
		JLabel jlBook = new JLabel("������");
		jlBook.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
		jlBook.setBounds(230,350,150,30);
		this.add(jlBook);
		
		JComboBox<String> jcbBook = new JComboBox<>();//�������������б�
		List<Book> books = new ArrayList<Book>();
		books = new BookDaoImpl().ExecuteQueryBySql(
				"select * from "+Book.tablename+" where mid=ANY("+"select id from "+Major.tablename+" where name='"+jcbMajor.getItemAt(0)+"')");
		List<String> booknames = new ArrayList<String>();
		for(Book tempBook:books) {
			booknames.add(tempBook.getName());
		}
		jcbBook.setModel(new DefaultComboBoxModel<String>(booknames.toArray(new String[booknames.size()])));
		jcbBook.setOpaque(false);
		jcbBook.setBounds(380,350,350,30);
		this.add(jcbBook);
		
		
		JLabel jlChapter = new JLabel("�½ڣ�");
		jlChapter.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
		jlChapter.setBounds(230,400,150,30);
		this.add(jlChapter);
		
		JComboBox<String> jcbChapter = new JComboBox<>();//�����鱾�½������б�
		List<Chapter> chapters = new ArrayList<Chapter>();
		chapters = new ChapterDaoImpl().ExecuteQueryBySql(
				"select * from "+Chapter.tablename+" where mid=ANY(select id from "+Book.tablename+" where name='"+jcbBook.getItemAt(0)+"')");
		List<String> chapternames = new ArrayList<String>();
		for(Chapter tempChapter:chapters) {
			chapternames.add(tempChapter.getName());
		}
		jcbChapter.setModel(new DefaultComboBoxModel<String>(chapternames.toArray(new String[chapternames.size()])));
		jcbChapter.setOpaque(false);
		jcbChapter.setBounds(380,400,350,30);
		this.add(jcbChapter);
		
		JLabel jlClass = new JLabel("�༶��");
		jlClass.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
		jlClass.setBounds(230,450,150,30);
		this.add(jlClass);
		JComboBox<String> jcbClass = new JComboBox<>();//�����༶�����б�
		List<Classes> classs = new ArrayList<Classes>();
		classs = new ClassesDaoImpl().ExecuteQueryBySql(
				"select * from "+Classes.tablename+" where mid=ANY(select id from "+Major.tablename+" where name='"+jcbMajor.getItemAt(0)+"')");
		List<String> classnames = new ArrayList<String>();
		for(Classes tempClass:classs) {
			classnames.add(tempClass.getName());
		}
		jcbClass.setModel(new DefaultComboBoxModel<String>(classnames.toArray(new String[classnames.size()])));
		jcbClass.setOpaque(false);
		jcbClass.setBounds(380,450,350,30);
		this.add(jcbClass);
		
		JButton btnDelete=new JButton("ɾ���˰༶���Ծ�");
		btnDelete.setFont(new Font("΢���ź�",Font.PLAIN,20));
		btnDelete.setBounds(740, 445, 200, 40);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditExamPaperBiz.DeleteExamPaper(jcbClass.getSelectedItem().toString());
			}
		});
		this.add(btnDelete);
		
		JLabel jlExampaperCount = new JLabel("��Ŀ������");
		jlExampaperCount.setBounds(230,500,150,30);
		jlExampaperCount.setFont(new Font("΢���ź�",Font.CENTER_BASELINE,25));
        this.add(jlExampaperCount);
        
        JTextField jtExampaperCount = new JTextField();//�����ı���
        jtExampaperCount.setBounds(380,500,350,30);
        jtExampaperCount.setFont(new Font("΢���ź�",Font.PLAIN,20));
        jtExampaperCount.addKeyListener(new KeyListener() {

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
       
        
        this.add(jtExampaperCount);
		
        
		JButton btn=new JButton("�½��Ծ�");
		btn.setFont(new Font("΢���ź�",Font.PLAIN,20));
		btn.setBounds(330, 550, 150, 40);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditExamPaperBiz.AddExamPaper(
						jtExampaperId.getText(),jtExampaperName.getText(),jtTotal.getText(),
						jcbChapter.getSelectedItem().toString(),jcbClass.getSelectedItem().toString(),jtExampaperCount.getText());
			}
		});
		this.add(btn);
		
		JButton btnAddQuestion=new JButton("������Ŀ");
		btnAddQuestion.setFont(new Font("΢���ź�",Font.PLAIN,20));
		btnAddQuestion.setBounds(550, 550, 150, 40);
		btnAddQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditExamPaperBiz.AddQuestion(jcbChapter.getSelectedItem().toString(),jcbClass.getSelectedItem().toString(),jtExampaperCount.getText());
			}
		});
		this.add(btnAddQuestion);
		
		//�����б��¼�
		jcbMajor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//jcbExampaper.removeAllItems();
				EditExamPaperBiz.SelectComboBox(jcbMajor, jcbBook, jcbChapter,jcbClass);
			}
		});
		jcbBook.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//jcbExampaper.removeAllItems();
				EditExamPaperBiz.SelectComboBox(null, jcbBook, jcbChapter,jcbClass);
			}
		});
	}
}
