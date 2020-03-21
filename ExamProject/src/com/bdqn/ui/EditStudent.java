package com.bdqn.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.bdqn.biz.EditStudentBiz;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Student;
import com.bdqn.data.dao.ClassesDao;
import com.bdqn.data.impl.ClassesDaoImpl;

@SuppressWarnings("serial")
public class EditStudent extends MainBusinessPanel{
	ClassesDao cla=new ClassesDaoImpl();
	List<Classes> classess=new ArrayList<Classes>();
	List<Student> data=new ArrayList<Student>();
	
	public EditStudent() {
		
		JRadioButton personal=new JRadioButton("个 人");
		JRadioButton classes=new JRadioButton("班 级");
		personal.setFont(new Font("宋体",Font.CENTER_BASELINE,30));
		classes.setFont(new Font("宋体",Font.CENTER_BASELINE,30));
		personal.setBounds(300, 50, 110, 50);
		classes.setBounds(550, 50, 110, 50);
		personal.setSelected(true);
		personal.setContentAreaFilled(false);
		classes.setContentAreaFilled(false);
		ButtonGroup bg=new ButtonGroup();
		bg.add(personal);
		bg.add(classes);
		this.add(personal);
		this.add(classes);
		
		JLabel jlabel=new JLabel("学 号/班级名：");
		jlabel.setBounds(270, 130, 200, 50);
		jlabel.setFont(new Font("宋体",Font.CENTER_BASELINE,20));
		this.add(jlabel);
		JTextField jtf=new JTextField();
		jtf.setFont(new Font("宋体",Font.CENTER_BASELINE,20));
		jtf.setBounds(430, 130, 200, 50);
		jtf.setBorder(BorderFactory.createBevelBorder(1));
		jtf.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if(personal.isSelected()) {
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
						
					} else {
						e.consume();
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {

			}});
		this.add(jtf);
		
		JLabel allStu=new JLabel("点击返回到全部学生！");
		allStu.setBounds(740, 190, 200, 30);
		allStu.setFont(new Font("微软雅黑",Font.PLAIN,15));
		allStu.setForeground(Color.WHITE);
		
		this.add(allStu);
		
		String[] col ={"学 号", "名 字","密 码","地 址","手机号","班 级"};
		JTable table=new JTable();
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("宋体",Font.BOLD,15));
		table.setFont(new Font("宋体",Font.PLAIN,15));
		table.setBackground(Color.LIGHT_GRAY);
		
		//默认管理二维表格数据的实例
		DefaultTableModel mm = new DefaultTableModel(col,0);
		EditStudentBiz.findStudentAll(table, mm);
		classess=cla.<Classes>ExecuteQueryBySql("select * from "+Classes.tablename);
		Vector<String> classessItems=new Vector<String>();
		for(Classes cl:classess) {
			classessItems.add(cl.getName());
		}
		JComboBox<String> claCbxs = new JComboBox<>(classessItems);
		DefaultCellEditor claDcers = new DefaultCellEditor(claCbxs);
		table.getColumnModel().getColumn(5).setCellEditor(claDcers);
		//把实例加到表格
		/*
		 * 	对齐方式居中
		 */
		DefaultTableCellRenderer r = new DefaultTableCellRenderer(); 
		r.setHorizontalAlignment(JLabel.CENTER); 
		table.setDefaultRenderer(Object.class, r);
		JScrollPane jsp1=new JScrollPane(table);
		jsp1.setBounds(130, 220, 750, 350);
		
		JTable addtable=new JTable();
		addtable.getTableHeader().setFont(new Font("宋体",Font.BOLD,15));
		addtable.setRowHeight(30);
		addtable.setFont(new Font("宋体",Font.PLAIN,18));
		addtable.setBackground(Color.LIGHT_GRAY);
		addtable.setDefaultRenderer(Object.class, r);
		String[] cols ={"学 号", "名 字","密 码","地 址","手机号","班 级"};
		DefaultTableModel mms= new DefaultTableModel(cols,1);
		
		addtable.setModel(mms);
		Vector<String> claItems=new Vector<String>();
		for(Classes cl:classess) {
			claItems.add(cl.getName());
		}
		JComboBox<String> claCb = new JComboBox<>(claItems);
		claCb.setEditable(true);
		DefaultCellEditor claDce = new DefaultCellEditor(claCb);
		addtable.getColumnModel().getColumn(5).setCellEditor(claDce);
		
		JScrollPane jsp=new JScrollPane(addtable);
		jsp.setBounds(130, 650, 750, 55);
		
		this.add(jsp);
		
		JButton findbtn=new JButton("查 找");
		findbtn.setFont(new Font("宋体",Font.CENTER_BASELINE,18));
		findbtn.setBounds(650, 135, 100, 40);
		this.add(findbtn);
		
		JButton addbtn=new JButton("添 加");
		addbtn.setFont(new Font("宋体",Font.CENTER_BASELINE,18));
		addbtn.setBounds(890, 655, 90, 40);
		this.add(addbtn);
		
		JButton upbtn=new JButton("修 改");
		upbtn.setFont(new Font("宋体",Font.CENTER_BASELINE,18));
		upbtn.setBounds(300, 590, 100, 40);
		this.add(upbtn);
		
		JButton delbtn=new JButton("删 除");
		delbtn.setFont(new Font("宋体",Font.CENTER_BASELINE,18));
		delbtn.setBounds(600, 590, 100, 40);
		this.add(delbtn);
		
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				EditStudentBiz.tableListener(table, data);
			}
		});
		allStu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				allStu.setLocation(740, 190);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				allStu.setLocation(740, 189);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				data.clear();
				for (int index = mm.getRowCount() - 1; index >= 0; index--) {
					mm.removeRow(index);
				} 
				EditStudentBiz.findStudentAll(table, mm);
			}
		});
		findbtn.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				data.clear();
				boolean isP=personal.isSelected();
				boolean isC=classes.isSelected();
				String id=jtf.getText();
				if(!id.equals("")) {
					if(id.length()<11) {
						for (int index = mm.getRowCount() - 1; index >= 0; index--) {
							mm.removeRow(index);
						} 
						boolean isSelect=false;
						if(isP){
							isSelect=EditStudentBiz.findStudent(table,mm,id);
						}
						if(isC){
							isSelect=EditStudentBiz.findClass(table, mm, id);
						}
						if(!isSelect) {
							new MainDialog().showMessageDialog(null, "未找到该学号和班级!\n请重新输入！");
							EditStudentBiz.findStudentAll(table, mm);
						}
					}else {
						new MainDialog().showMessageDialog(null, "输入的学号或班级不规范！");
					}
				}else {
					new MainDialog().showMessageDialog(null, "请输入学号或者班级号！");
				}
			}
		});
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditStudentBiz.addStudent(table, addtable, mm);
			}
		});	
		
		upbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditStudentBiz.upStudent(table, data);
			}
		});	
		delbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num=MainDialog.showConfirmDialog(null, "    确定删除该信息吗？", "提 示", 2, 1);
				if(num==0) {
					EditStudentBiz.delStudent(table);
				}
			}
		});	
		this.add(jsp1);
	}
}
