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

// ��ʦ֮¼������
@SuppressWarnings("serial")
public class AddQuestion extends MainBusinessPanel {
	

	public AddQuestion() {
		lblTitle.setBounds(50,50,200,60);
		lblTitle.setFont(new Font("΢���ź�",1,30));
		jp.add(lblTitle);
		init();//��ʼ������
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
		
	//�ǿշ���
	public boolean isNotNull() {
		if(textTitle.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(AddQuestion.this,"��������Ŀ����");
			textTitle.requestFocus();//��ȡ����
			return false;
		}else if(textoptionA.getText().trim().equals("")){
			JOptionPane.showMessageDialog(AddQuestion.this,"������ѡ��");
			textoptionA.requestFocus();
			return false;
		}else if(textArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(AddQuestion.this,"�������");
			textArea.requestFocus();
			return false;
		}else  if(textArea1.getText().trim().equals("")){
			JOptionPane.showMessageDialog(AddQuestion.this,"���������");
			textArea1.requestFocus();
			return false;
		}else {
			return true;
		}
	}
	
	 JPanel jp=new JPanel();//����//����
	 JLabel lblTitle=new JLabel("¼����Ŀ");
	 JPanel jpl=new JPanel();//�м�����
	
	 JLabel lbltitle=new JLabel("��Ŀ��");//��Ŀ
	 TextArea textTitle=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//��Ŀ�ı���
	 JLabel lbloption=new JLabel("ѡ�");
	 TextArea textoptionA=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 JScrollPane jsoption=new JScrollPane();
	 
	 JLabel lblArea=new JLabel("�𰸣�");
	 TextArea textArea=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 
	 JLabel lbljx=new JLabel("������");
	 TextArea textArea1=new TextArea(null,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	 
	 JLabel lblmajor=new JLabel("רҵ��");
	 JComboBox<String> cboMajor;
	 
	 JLabel lblbook=new JLabel("��Ŀ��");
	 String str[]= {"��ѡ��","��һ�½�"};
	 JComboBox<String> cbobook;
	 
	 JLabel lblsetion=new JLabel("�½�");
	 JComboBox<String> cbosection;
	 
	 JButton btnSubmit=new JButton("�ύ:");
	 JButton btnEmpty=new JButton("���:");
	
	 //��ʼ��
	 public void init() {
		 
		 List<Major>listMajor=new MajorDaoImpl().ExecuteQueryBySql("select * from "+Major.tablename+" order by id");
		 List<String>majorName=new ArrayList<String>();
		 //majorName.add("��ѡ��");
		 for(Major m:listMajor) {
			 majorName.add(m.getName());
		 }
		 cboMajor=new JComboBox<String>(majorName.toArray(new String[majorName.size()]));
		 
		 List<Book>listBok=new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" order by id");//��ȡ���е���Ŀ
		 List<String>bookString=new ArrayList<String>();
		 //bookString.add("��ѡ��");
		 for(Book b:listBok) {
			 bookString.add(b.getName());//���������Ŀ������
		 }
		 cbobook=new JComboBox<String>(bookString.toArray(new String[bookString.size()]));
		 
		 
		 List<Chapter>listChapter=new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" order by id");//��ȡ���е��½�
		 List<String>chapterString=new ArrayList<String>();
		 //chapterString.add("��ѡ��");
		 for(Chapter b:listChapter) {
			 chapterString.add(b.getName());//���������Ŀ������
		 }
		 cbosection=new JComboBox<String>(chapterString.toArray(new String[chapterString.size()]));
		 
	 }
	
	 	public JPanel addJpanel() {
		jpl.setBounds(200,40,750,640);
		jpl.setLayout(null);
		jpl.setOpaque(false);
		
		JLabel lbltitle=new JLabel("��Ŀ��");//��Ŀ
		lbltitle.setBounds(10,20,60,40);//��Ŀ���꼰��С
		lbltitle.setFont(typeface());
		
		textTitle.setFont(typeface());
		textTitle.setBounds(80,13,550,60);//��Ŀ���꼰��С
		textTitle.setColumns(5);
		
		
		//ѡ��
		lbloption.setFont(typeface());//��ȡ����
		lbloption.setBounds(10,100,60,40);
		
		//ѡ���ı���
		
		//ѡ���ı������꼰��С
		textoptionA.setBounds(80,100,550,100);
		
		//ѡ���ı�����ʽ
		textoptionA.setFont(typeface());
		
		
		//��

		lblArea.setFont(typeface());//���������ʽ
		lblArea.setBounds(10,226,60,40);
		
		//���ı���

		textArea.setBounds(80,230,550,40);
		textArea.setFont(typeface());
		//����ʾ�߿�
		//����

		
		lbljx.setFont(typeface());//���������ʽ
		lbljx.setBounds(10,276,60,40);
		
		//�����ı���

		textArea1.setBounds(80,280,550,80);
		textArea1.setFont(typeface());
		textArea1.setColumns(5);
		
		
		//רҵ
		
		lblmajor.setFont(typeface());
		lblmajor.setBounds(10,388,60,40);
		
		//רҵ������
		
		cboMajor.setFont(typeface());
		cboMajor.setEditable(false);
		cboMajor.setBounds(80,390,550,40);
		
		
		//��Ŀ
		
		
		lblbook.setFont(typeface());
		lblbook.setBounds(10,448,60,40);
		
		//��Ŀ������

		cbobook.setFont(typeface());
		cbobook.setEditable(false);
		cbobook.setBounds(80,450,550,40);
		
		//�½�
		
		lblsetion.setFont(typeface());
		lblsetion.setBounds(10,508,60,40);
		
		//�½�������
		
		
		cbosection.setFont(typeface());
		cbosection.setEditable(false);
		cbosection.setBounds(80,510,550,40);
		
		//�ύ
	
		
		btnSubmit.setFont(typeface());
		btnSubmit.setBounds(250,580,80,40);
		
		//���
		
		btnEmpty.setFont(typeface());
		btnEmpty.setBounds(360,580,80,40);
		
		List<String>listtext=new ArrayList<String>();
		
		listtext.add(textTitle.getText());//����
		listtext.add(textoptionA.getText());//ѡ��
		listtext.add(textArea.getText());//��
		listtext.add(textArea1.getText());//����
		listtext.add(cboMajor.getSelectedItem().toString());//רҵ������
		listtext.add(cbobook.getSelectedItem().toString());//��Ŀ
		listtext.add(cbosection.getSelectedItem().toString());//�½�
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
		jpl.add(textoptionA);//ѡ��
		
		jpl.add(lbloption);
		jpl.add(lbltitle);
		jpl.add(textTitle);
		
		
		return jpl;
	}
	
	
	
		//���巽��
		public Font typeface() {
			Font g=new Font("΢���ź�",1,18);
			return g;
		}
	



	public  void ss() {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNotNull()) {
					Question q=new Question();
					q.setTitle(textTitle.getText());//����
					q.setOption(textoptionA.getText());//ѡ��
					q.setSolution(textArea.getText());//��
					q.setAnalysis(textArea1.getText());//����
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
