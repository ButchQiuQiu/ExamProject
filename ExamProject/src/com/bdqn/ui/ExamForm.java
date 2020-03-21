package com.bdqn.ui;

import java.awt.Color;
import java.awt.Component;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;


import com.bdqn.biz.ExamFormBiz;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Question;
import com.bdqn.data.bean.Result;
//����
public class ExamForm extends MainBusinessPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Result result=new Result();//����ɼ�
	
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	static int countScore=0;//ÿ��������
	static JPanel jpanel=new JPanel();//�ȴ����
	public JLabel lbl=new JLabel();//
	public ExamForm(){
		lbl=new JLabel("�ȴ�����");//
		lbl.setBounds(200,100,300,200);
		lbl.setFont(new Font("΢���ź�",1,20));
		jpanel.setBounds(1,1,1000,720);
		//jpanel.setVisible(true);
		jpanel.setLayout(null);
		jpanel.add(lbl);
		this.add(jpanel);
	}
	static boolean isFlag=true;
	//�ȴ�ʱ�俪ʼ
	
	@SuppressWarnings("static-access")
	public void jPanelj(int studentid) {
		if(this.jl1!=null) {
			return;
		}
		String dateTime=new ExamFormBiz().findClasses(studentid).getDate();
		Date baseDate=null;
		try {
			baseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("��ǰʱ�䣺"+new Date()+"\n����ʱ�䣺"+baseDate+"\n������:"+new Date().compareTo(baseDate));
		if(new Date().compareTo(baseDate)==-1) {
			System.out.println("��δ����");
			return;
		}
		result.setStuid(studentid);//���ѧ��
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		ExamFormBiz join=new ExamFormBiz();
		Exampaper exam=join.findClasses(studentid);//����Ծ����Ϣ
		this.result.setExamname(exam.getName());
		ls=join.findQuestion(exam.getQids());//����������Ϣ
		ExamForm.time=exam.getTime()*60;//���ʱ��
		//int date=(exam.getTime()-10)*60;//�����ύ���Ծ�
		countScore=(int)(exam.getScore()/exam.getCount());
		
		ExamForm e=new ExamForm();
		e.removeAll();
		e.repaint();
		ExamForm1();
		init();
		e.repaint();
		e.updateUI();

		this.add(jpanel);//�ȴ�����
		btnsubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(submit()) {
					subminResult();
					join.doResult(result);
				}
//				if(time>10){
//					JOptionPane.showMessageDialog(ExamForm.this,"���Բ����ύ�Ծ�");
//				}else {
//					if(submit()) {
//						subminResult();
//					}
//				}
			}
		});
	}
	
	
	protected JButton btnsubmit=new JButton();//�ύ����
	Map<String,String>stringOption=new HashMap<String,String>();//��ȡѡ��ļ���
	
	//�ύ����
	public boolean submit() {
		boolean isFlag=false;
		String idName="";
		for(JButton btn:listIntger) {
			if(btn.getBackground().equals(Color.green)) {
				
			}else {
				idName+=btn.getText()+"��";
			}
		}
		if(idName.equals("")) {
			idName="ȷ���ύ����?";
		}else {
			idName+="����������û������,ȷ���ύ����?";
		}
		int i=JOptionPane.showConfirmDialog(null,idName,"����",JOptionPane.YES_NO_CANCEL_OPTION);
		if(i==0) {
			isFlag=true;
		}else if(i==1) {
			isFlag=false;
		}else {
			isFlag=false;
		}
		return isFlag;
	}
	
	//��óɼ�
	public boolean subminResult() {
		Component items[]=plMax.getComponents();//������е�����
		List<JPanel>listJPanel=new ArrayList<JPanel>();//�������е�����
		for(Component c:items) {
			if(c instanceof JPanel) {
				listJPanel.add((JPanel) c);//�������JPanel
			}
		}
		String option1="";
		for(JPanel jpanel:listJPanel) {
			Component []com=jpanel.getComponents();//���ÿ��������������
			String title="";
			String option="";
			for(Component c:com) {
				if(c instanceof JLabel) {
					JLabel ti=(JLabel)c;//���û�������µı���
					title=ti.getText().substring(ti.getText().indexOf("��")+1);
				}
				if(c instanceof JRadioButton) {
					;//���ÿ�������µİ�ť
					JRadioButton rbtnOption=(JRadioButton)c;
					if(rbtnOption.isSelected()) {
						option+=rbtnOption.getText().substring(0,1)+"*";
					}
				}
			}
			if(option.equals("")) {
				option="��û��ѡ��*";
				
			}
			option1+=option;
			stringOption.put(title,option);
		}
		double score=0;
		for(int i=0;i<ls.size();i++) {
			Question question=ls.get(i);//��ö���
			for(int j=0;j<stringOption.size();j++) {
				if(stringOption.containsKey(question.getTitle())) {
					if(stringOption.get(question.getTitle()).equals(question.getSolution())) {
						score+=countScore;
						break;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(ExamForm.this,"������ɣ����ĳɼ�Ϊ��"+score);
		ExamForm.result.setScore(score);//��÷���
		ExamForm.result.setOption(option1);
		if(doResult(ExamForm.result)) {
			return true;
		}
		return false;
	}
	
	private boolean doResult(Result result2) {
		// TODO Auto-generated method stub
		return false;
	}

	//������������
	JPanel plexam=new JPanel();
	
	List<Question>ls=null;
	public void ExamForm1() {
		this.setLayout(null);
		this.setSize(1000,720);
		this.setBackground(Color.white);//������ɫ
		//jp.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//�ύ����
		btnsubmit.setText("�ύ����");
		btnsubmit.setFont(new Font("΢���ź�",1,30));
		btnsubmit.setBounds(30,550,200,80);
		btnsubmit.setContentAreaFilled(false);
		
		examJPanel();
		this.add(plexam);
		this.add(btnsubmit);
		//this.setVisible(true);
	}
	
	
	//���巽��
	public Font typeface() {
		Font g=new Font("΢���ź�",1,18);
		return g;
	}
	
	protected JRadioButton []rbtnA=new JRadioButton[5];
	
	//��ߵı������
	protected JPanel plId=new JPanel();
	
	//�ύ����


	//��������JPanel
	public void examJPanel() {
		plexam.setBounds(250,10,690,700);
		//plexam.setBorder(BorderFactory.createTitledBorder("��������"));
		//plexam.setBorder(BorderFactory.createLineBorder(Color.black));
		plexam.setBackground(Color.white);
		plexam.setLayout(null);
		dynamic();//��ȡ����
		plexam.add(scrollPane);
		this.add(plId);
		this.add(plexam);
	}
	
	JPanel plMax=new JPanel();//�������е�����
	JScrollPane scrollPane;
	List<JButton>listIntger=new ArrayList<JButton>();//����������еı��
	//��̬����
	public void dynamic() {
		int y1=20;
		plMax.setLayout(null);
		plMax.setPreferredSize(new Dimension(600,200*ls.size()));
		plMax.setBackground(Color.white);
		plMax.setBounds(10,10,900,2000);
		for(int j=0;j<ls.size();j++) {
				Question q=new Question();//����
				q=ls.get(j);//��ֵ
				String queid=q.getOption().replaceAll("\\*","����");//�滻
				String[]s=queid.split("����");//�ָ�ѡ��
				
				JPanel jp=new JPanel();//ÿ���������
				jp.setLayout(null);
				jp.setBorder(BorderFactory.createLineBorder(Color.black));
				jp.setBackground(Color.white);
				JLabel lbltitle=new JLabel(j+1+"��"+q.getTitle());//����
				lbltitle.setBounds(30,10,600,40);
				lbltitle.setFont(new Font("΢���ź�",0,15));
				jp.setBounds(25,y1,620,180);
				
				rbtnA=new JRadioButton[s.length];//��ѡ��
				
				int x=60;
				int y=45;
				int width=520;
				int height=30;
				ButtonGroup group=new ButtonGroup();//��ť��
				for(int k=0;k<s.length;k++) {//�����ַ����ĳ����ж�
					rbtnA[k]=new JRadioButton(s[k]);//�ѻ�ȡ����ѡ�ֵ����ť
					rbtnA[k].setBounds(x, y, width, height);
					rbtnA[k].setFont(new Font("΢���ź�",0,15));//���������ʽ
					rbtnA[k].setBackground(Color.white);
					jp.add(rbtnA[k]);
					
					if(q.getTitle().indexOf("��ѡ")>0) {
						group.add(rbtnA[k]);
					}
					list.add(rbtnA[k]);
					y+=30;
				}
				jp.add(lbltitle);
				
				y1+=200;
				plMax.add(jp);
			}
		plId.setBounds(30,120,200,20*ls.size());
		plId.setLayout(new GridLayout(5,10,2,2));
		//��ߵı�Ű�ť
		for(int j=0;j<ls.size();j++) {
			JButton jbtn=new JButton(j+1+"");
			jbtn.setFont(typeface());//�������
			jbtn.setBorder(BorderFactory.createLineBorder(Color.black));
			jbtn.setBackground(Color.white);
			listIntger.add(jbtn);
			plId.add(jbtn);
		}
		
		scrollPane= new JScrollPane(plMax);
		scrollPane.setBounds(10,10,680,650);
		//Component items[]=plId.getComponents();
		jbutton();
	}
	List<JButton> listbrn=new ArrayList<JButton>();//��߱�ŵļ���
	public void jbutton() {
		Component items[]=plId.getComponents();
		
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
		jRadioButton();
		
	}
	
	//��ť��Ӧ
	List<String>listString=new ArrayList<String>();//���еı��
	List<JRadioButton>list=new ArrayList<JRadioButton>();//�������JRadioButton
	public void jRadioButton() {
		for(JRadioButton r:list) {
			r.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JPanel p=(JPanel)r.getParent();
					Component s[]=p.getComponents();
					
					JButton btn=new JButton();
					String name="";
					
					for(Component tempcom:s) {
						if(tempcom instanceof JLabel) {
							JLabel lblName=(JLabel)tempcom;
							name=lblName.getText().substring(0,lblName.getText().indexOf("��"));
						}
					}
					for(JButton j:listbrn){
						if(j.getText().equals(name)) {
							btn=j;
							break;
						}
					}
					if(r.isSelected()) {
						btn.setBackground(Color.green);
					}else {
						for(Component tempcom:s) {
							if(tempcom instanceof JRadioButton) {
								JRadioButton jrbtn=(JRadioButton)tempcom;
								if(jrbtn.isSelected()) {
									return;
								}
							}
						}
						btn.setBackground(Color.white);
					}
				}
			});
		}
		
	}
	
	//�����¼�
	
	
	private void init() {
		//jPanel.add(jl1);
		this.add(jl2);
		jl2.setBounds(80,20,100,100);
		this.add(jl3);
		jl3.setBounds(140,20,100,100);
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
		while (time > 0) {
			//hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			jl1.setText(hour + "ʱ");
			jl1.setFont(new Font("΢���ź�",1,25));
			jl2.setText(minute + "��");
			jl2.setFont(new Font("΢���ź�",1,25));
			jl3.setText(seconds + "��");
			jl3.setFont(new Font("΢���ź�",1,25));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
				if(time==600) {
					JOptionPane.showMessageDialog(ExamForm.this,"���Ի���ʮ���ӽ�����!");
				}
				if(ExamForm.time==0) {
					subminResult();//�����ɼ�
				}
		}
	}
}


