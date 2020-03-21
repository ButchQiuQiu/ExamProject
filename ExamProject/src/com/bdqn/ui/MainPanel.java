package com.bdqn.ui;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bdqn.mainfunction.MainFunction;

@SuppressWarnings("serial")
//����� ���ڷ��ð�ť��ҵ�����
public class MainPanel extends JPanel{
	@SuppressWarnings({ })
	public MainPanel() {
		super();
		this.setBounds(0, 0, 1280, 720);
		
		this.setLayout(null);
		
		if(MainFunction.MainPanelType==1) {
			this.TeacherPanel();
		}else if(MainFunction.MainPanelType==2) {
			this.StudentPanel();
		}
	}
	
	//��ʦ����
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void TeacherPanel() {
		EditStudent mp1=new EditStudent();mp1.setPanelName("1");mp1.setLayers(1);
		//Ĭ����ʾ��һ������
		mp1.setVisible(true);
		MainFunction.LastSelectedPanelName=mp1.getPanelName();
		MainFunction.SelectedPanelName=mp1.getPanelName();
		
		AddQuestion mp2=new AddQuestion();mp2.setPanelName("2");mp2.setLayers(2);;
		EditExamPaper mp3=new EditExamPaper();mp3.setPanelName("3");mp3.setLayers(3);
		StartExam mp4=new StartExam();mp4.setPanelName("4");mp4.setLayers(4);
		SelectResultForm mp5=new SelectResultForm();mp5.setPanelName("5");mp5.setLayers(5);
		MainBusinessPanel mp6=new MainBusinessPanel();mp6.setPanelName("6");mp6.setLayers(6);
		mp1.setBackground(Color.RED);
		mp3.setBackground(Color.BLUE);
		mp4.setBackground(Color.CYAN);
		mp5.setBackground(Color.WHITE);
		mp6.setBackground(Color.YELLOW);
		this.add(mp1);
		this.add(mp2);
		this.add(mp3);
		this.add(mp4);
		this.add(mp5);
		this.add(mp6);
		MainFunction.Panels=new ArrayList();
		MainFunction.Panels.add(mp1);
		MainFunction.Panels.add(mp2);
		MainFunction.Panels.add(mp3);
		MainFunction.Panels.add(mp4);
		MainFunction.Panels.add(mp5);
		MainFunction.Panels.add(mp6);
		
		MainButton b1=new MainButton(mp1.getPanelName(),"ѧ������");
		MainButton b2=new MainButton(mp2.getPanelName(),"¼������");
		MainButton b3=new MainButton(mp3.getPanelName(),"�Ծ����");
		MainButton b4=new MainButton(mp4.getPanelName(),"��ʼ����");
		MainButton b5=new MainButton(mp5.getPanelName(),"�鿴�ɼ�");
		MainButton b6=new MainButton(mp6.getPanelName(),"�˳�ϵͳ");
		
		b1.setBounds(0, 138, 280, 65);
		b2.setBounds(0, 216, 280, 65);
		b3.setBounds(0, 287, 280, 65);
		b4.setBounds(0, 357, 280, 65);
		b5.setBounds(0, 428, 280, 65);
		b6.setBounds(0, 500, 280, 65);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		
		MainFunction.mainPanelBackground=new JLabel(new ImageIcon("src/com/bdqn/images/1.jpg"));
		MainFunction.mainPanelBackground.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(MainFunction.mainPanelBackground);
	}
	
	//ѧ������
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void StudentPanel() {
		StudentScore mp1=new StudentScore();mp1.setPanelName("ѧ��1");mp1.setLayers(1);
		//Ĭ����ʾ��һ������
		mp1.setVisible(true);
		MainFunction.LastSelectedPanelName=mp1.getPanelName();
		MainFunction.SelectedPanelName=mp1.getPanelName();
		
		MainFunction.StudentExamForm=new ExamForm();MainFunction.StudentExamForm.setPanelName("ѧ��2");MainFunction.StudentExamForm.setLayers(2);
		MainFunction.StudentExamForm.setBackground(null);MainFunction.StudentExamForm.setOpaque(false);;
		MainBusinessPanel mp3=new MainBusinessPanel();mp3.setPanelName("ѧ��3");mp3.setLayers(3);
		mp1.setBackground(Color.RED);
		mp3.setBackground(Color.BLUE);
		this.add(mp1);
		this.add(MainFunction.StudentExamForm);
		this.add(mp3);
		MainFunction.Panels=new ArrayList();
		MainFunction.Panels.add(mp1);
		MainFunction.Panels.add(MainFunction.StudentExamForm);
		MainFunction.Panels.add(mp3);
		
		MainButton b1=new MainButton(mp1.getPanelName(),"�鿴����");
		MainButton b2=new MainButton(MainFunction.StudentExamForm.getPanelName(),"��ʼ����");
		MainButton b3=new MainButton(mp3.getPanelName(),"�˳�ϵͳ");
		
		
		b1.setBounds(0, 138, 280, 65);
		b2.setBounds(0, 216, 280, 65);
		b3.setBounds(0, 287, 280, 65);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		
		MainFunction.mainPanelBackground=new JLabel(new ImageIcon("src/com/bdqn/images/ѧ��1.jpg"));
		MainFunction.mainPanelBackground.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(MainFunction.mainPanelBackground);
	}
	
}
