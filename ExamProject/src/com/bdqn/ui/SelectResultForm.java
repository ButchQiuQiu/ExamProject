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



//�鿴�ɼ�
public class SelectResultForm extends MainBusinessPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JPanel plLeft=new JPanel();//���
		//�ɼ���ʾJPanel
		JPanel plResult=new JPanel();//��ʾ�ɼ�
		JScrollPane js;
		//�����ʾ��ʼ��
	public SelectResultForm() {
		//���JPanel
		plLeft.setBounds(30,0,970,130);//�Ļ��ɶ���
		plLeft.setLayout(null);
		plLeft.setOpaque(false);
		this.add(plLeft);
		section();//�����¼�
		plResult.setBounds(30,150,970,550);
		plResult.setLayout(null);
		//plResult.setBorder(BorderFactory.createLineBorder(Color.black));
		data(textsec.getText());//�������
		js=new JScrollPane(plMax);//���ֹ�����
		js.setOpaque(false);
		js.setBackground(Color.white);
		js.setBounds(40,30,900,500);
		plResult.add(js);
		plResult.setOpaque(false);
		this.add(plResult);
	}//��ʼ�� һ��JPanel
	
	
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
		
		JButton btnOut=new JButton("����");
		btnOut.setFont(font());//�������
		btnOut.setBounds(500,40,150,50);
		btnOut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnOut.setContentAreaFilled(false);
		plLeft.add(btnOut);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plMax.removeAll();
				plMax.repaint();
				load(jPanelconent(textsec.getText()));//���¼��سɼ�'
				plMax.repaint();
				plMax.updateUI();
				//plMax.revalidate();
			}
		});
	}
	
	//ˢ�����
	List<Result>listResult=new ArrayList<Result>();
	
	public List<Result> jPanelconent(String name) {
		//ɸѡ����
		if(name.equals("")) {
			return listResult;//����ȫ��
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
	
	//ֻ����������
	public void txtInput_KeyTyped(KeyEvent ke){
		if(ke.getKeyChar() < '0' || ke.getKeyChar() > '9'){
			ke.setKeyChar('\0');
		}
	}
	
	//������ʽ
	public Font font() {
		Font f=new Font("΢���ź�",1,18);
		return f;
	}
	
	
	JPanel plMax=new JPanel();
	
	//���

	//�������
	public void data(String name) {
		SelectResultBiz selectResult=new SelectResultBiz();
		listResult=selectResult.findResult(name);//������еĳɼ�
		if(listResult.size()>0) {
			load(listResult);
		}else {
			textsec.setText("");
			textsec.requestFocus();
			jPanelconent(textsec.getText());
		}
	
	}
	
	//��������
	@SuppressWarnings("deprecation")
	public void load(List<Result>lresult) {
		plMax.setLayout(null);
		plMax.setPreferredSize(new Dimension(600,100*lresult.size()));
		JPanel plTop=new JPanel();
		plTop.setLayout(null);
		plTop.setBounds(10,5,850,70);
		plTop.setBorder(BorderFactory.createLineBorder(Color.black));
		
		String[] lie= {"����","ѧ��","����","�༶","����","�ɼ�","ʱ��"};
		int x1=20;
		for(int k=0;k<lie.length;k++) {
			JLabel lbl=new JLabel(lie[k]);
			lbl.setFont(font());//��ȡ����
			lbl.setBounds(x1,-6,130,80);
			plTop.add(lbl);
			if(lie[k].equals("�༶")) {
				x1+=170;
			}else {
				x1+=105;
			}
			if(lie[k].equals("����")) {
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
			
			SelectResultBiz sResult=new SelectResultBiz();//��òμ�ѧ������Ϣ
			Student student=sResult.findStudent(s.getStuid());//����ѧ�ò鿴��ѧ����Ϣ
			
			//Exampaper exam=sResult.findExampaper(s.getEid());//��ÿ�����Ϣ
			
			Classes classes=sResult.findClasses(student.getCid());//����꼶��Ϣ
			
			JLabel lblCount=new JLabel(count+"");//����
			lblCount.setBounds(x2, 0,130,80);
			lblCount.setFont(font());
			count++;
			JLabel lbl=new JLabel(student.getId()+"");//ѧ��
			lbl.setFont(font());
			lbl.setBounds(x2+=80,0,130,80);
			JLabel lbl1=new JLabel(student.getName());//����
			lbl1.setFont(font());
			lbl1.setBounds(x2+=120,0,130,80);
			
			JLabel lbl2=new JLabel(classes.getName());//�༶
			lbl2.setFont(font());
			lbl2.setBounds(x2+=100,0,130,80);
			
			JLabel lblExam=new JLabel(s.getExamname());//��������
			lblExam.setFont(font());
			lblExam.setBounds(x2+=130,0,230,80);
			
			JLabel lblResult=new JLabel(s.getScore()+"");//�ɼ�
			lblResult.setFont(font());
			lblResult.setBounds(x2+=190,0,230,80);
			
			JLabel lbltime=new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date(s.getDatetime())));//
			lbltime.setFont(font());
			lbltime.setBounds(x2+=80,0,230,80);
			
			pl.add(lblCount);//ͳ������
			pl.add(lbl);//ѧ��
			pl.add(lbl1);//����
			pl.add(lbl2);//�༶
			pl.add(lblExam);//��������
			pl.add(lblResult);
			pl.add(lbltime);//ʱ��
			plMax.add(pl);//
			y+=90;
		}
		plMax.add(plTop);//����
		plMax.setOpaque(false);
		
	}
	//��귽��
}


