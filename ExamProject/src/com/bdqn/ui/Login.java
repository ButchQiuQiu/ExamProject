package com.bdqn.ui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bdqn.biz.LoginBiz;

@SuppressWarnings("serial")
public class Login extends JFrame{
	@SuppressWarnings({ })
	public Login() {
		setTitle(" 考 试 平 台");
		setBounds(600, 800, 646, 397);
		this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2), 
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		Container c=getContentPane();
		c.setLayout(null);
		
		JLabel jl1=new JLabel(),
				jl2=new JLabel(),
				jl3=new JLabel(),
				jl4=new JLabel();
		
		
		jl1.setFont(new Font("微软雅黑",Font.BOLD,18));
		jl1.setBounds(230, 125, 50, 30);
		jl1.setText("账号:");
		jl2.setFont(new Font("微软雅黑",Font.BOLD,18));
		jl2.setBounds(230, 175, 50, 30);
		jl2.setText("密码:");
		
		JTextField jf1=new JTextField();
		jf1.setFont(new Font("微软雅黑",Font.PLAIN,18));
		jf1.setBorder(BorderFactory.createBevelBorder(1));
		jf1.setBounds(350, 125, 170, 30);
		jf1.addKeyListener(new KeyListener() {

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
		
		JPasswordField jp=new JPasswordField();
		jp.setFont(new Font("微软雅黑",Font.PLAIN,18));
		jp.setBorder(BorderFactory.createBevelBorder(1));
		jp.setBounds(350, 175, 170, 30);
		jp.setEchoChar('*');
		
		
		c.add(jl1);
		c.add(jf1);
		c.add(jl2);
		c.add(jp);
		c.add(jl3);
		c.add(jl4);
		
		JRadioButton teacher=new JRadioButton("老师");
		JRadioButton student=new JRadioButton("学生");
		teacher.setFont(new Font("微软雅黑",Font.BOLD,18));
		student.setFont(new Font("微软雅黑",Font.BOLD,18));
		teacher.setBounds(310,220, 100, 50);
		student.setBounds(450,220, 100, 50);
		teacher.setContentAreaFilled(false);
		student.setContentAreaFilled(false);
		c.add(teacher);
		c.add(student);
		ButtonGroup but=new ButtonGroup();
		teacher.setSelected(true);
		but.add(teacher);
		but.add(student);
		
		JButton jt=new JButton("登   录");
		jt.setBounds(301, 267, 250, 50);
		jt.setFont(new Font("微软雅黑",Font.BOLD,18));
		jt.setBackground(new Color(88,185,227));
		jt.setForeground(Color.WHITE);
		
		jt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginBiz.Login(jf1.getText(), new String(jp.getPassword()), teacher.isSelected());
			}
		});
		c.add(jt);
		JLabel label=new JLabel(new ImageIcon("src/com/bdqn/images/登录.jpg"));
		label.setBounds(0, 0,640,360);
		c.add(label);
	}
}
