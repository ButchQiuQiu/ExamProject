package com.bdqn.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Major;
import com.bdqn.data.bean.Student;
import com.bdqn.data.dao.ClassesDao;
import com.bdqn.data.dao.MajorDao;
import com.bdqn.data.dao.StudentDao;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;
import com.bdqn.data.impl.StudentDaoImpl;
import com.bdqn.ui.MainDialog;

//��̬ҵ��ʵ����
//���ѧ����������Ҫ��ҵ���ҵ�����ݲ�(�ؼ��¼�����)
public final class EditStudentBiz {
	static StudentDao stu=new StudentDaoImpl();
	static MajorDao maj=new MajorDaoImpl();
	static ClassesDao cla=new ClassesDaoImpl();
	static List<Student> students=new ArrayList<Student>();
	static List<Major> majors=new ArrayList<Major>();
	static List<Classes> classess=new ArrayList<Classes>();
	//�¼��������ݲ���������Ҫʵ���� ���Բ������̳� �������к������Ǿ�̬����
	private EditStudentBiz() {};	
	
	//�����Ϣ  //���ѧ��Ϊ�� ������ʾ�� ������ʾ��
	@SuppressWarnings("static-access")
	public static boolean AddStrudent (String stuId,String stuName,String stuPassword,String stuAddress,String stuPhone,String stuClass) {
		int cid=new ClassesDaoImpl().<Classes>ExecuteQueryBySql("select * from "+Classes.tablename+" where name='"+stuClass+"'").get(0).getId();
		boolean re=new StudentDaoImpl().InsertBySql("insert into "+Student.tablename+" (id,name,password,address,phone,cid)values("
				+Integer.valueOf(stuId)+",'"+stuName+"','"+stuPassword+"','"+stuAddress+"','"+stuPhone+"','"+cid+"')");
		if(re) {
			new MainDialog().showMessageDialog(null, "��ӳɹ�!!!");
			return true;
		}else {
			new MainDialog().showMessageDialog(null, "���ʧ��!!! ��ȷ��ѧ���Ƿ��Ѵ���!!!");
			return false;
		}
		
	}
	
	//����רҵ �ؼ���ʼ��/�ؼ�����ʱ����
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void SelectComboBox(JComboBox<String> majorJb,JComboBox<String> classesJb) {
		String majorName=majorJb.getSelectedItem().toString();
		int MajorId=(new MajorDaoImpl().<Major>ExecuteQueryBySql("select * from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
		List<Classes> classess=new ClassesDaoImpl().ExecuteQueryBySql("select * from "+Classes.tablename+" where mid="+MajorId);
		List<String> classesnames=new ArrayList<String>();
		for(Classes tempclasses:classess) {
			classesnames.add(tempclasses.getName());
		}
		classesJb.setModel(new DefaultComboBoxModel(classesnames.toArray(new String[classesnames.size()])));
		
	}
	//�ж��ֻ�����
	@SuppressWarnings("static-access")
	public static boolean checkPhone(String phone) {
		boolean isPhone=false;
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(String.valueOf(phone).length() != 11){
        	
        	new MainDialog().showMessageDialog(null,"�ֻ���Ϊ11λ��,���������룡");
        }else{
		    Pattern p = Pattern.compile(regex);
		    Matcher m = p.matcher(String.valueOf(phone));
		    boolean isMatch = m.matches();
		    if(isMatch){
		    	isPhone=true;
		    } else {
		    	new MainDialog().showMessageDialog(null,"��������ֻ��ţ�:" + phone + " ��ʽ�������������룡����");
		    }
        }
		return isPhone;
	}
	public static boolean findStudent(JTable table,DefaultTableModel mm,String id) {
		boolean isFind=false;
		classess=cla.<Classes>ExecuteQueryBySql("select * from "+Classes.tablename);
		students=stu.<Student>ExecuteQueryBySql("select * from "+Student.tablename+" where id like '%"+id+"%'");
		for(Student s:students) {
			Vector<Object> vr=new Vector<Object>();
			vr.add(s.getId());
			vr.add(s.getName());
			vr.add(s.getPassword());
			vr.add(s.getAddress());
			vr.add(s.getPhone());
			for(Classes cl:classess) {
				if(s.getCid()==cl.getId()) {
					vr.add(cl.getName());
					break;
				}
			}
			mm.addRow(vr);
			isFind=true;
		}
		table.setModel(mm);
		return isFind;
	}
	public static boolean findClass(JTable table,DefaultTableModel mm,String id) {
		boolean isFind=false;
		classess=cla.<Classes>ExecuteQueryBySql("select * from "+Classes.tablename+" where name like '%"+id+"%'");
		for(Classes clas:classess) {
			students=stu.<Student>ExecuteQueryBySql("select * from "+Student.tablename+" where cid like '%"+clas.getId()+"%'");
			for(Student s:students) {
				Vector<Object> vr=new Vector<Object>();
				vr.add(s.getId());
				vr.add(s.getName());
				vr.add(s.getPassword());
				vr.add(s.getAddress());
				vr.add(s.getPhone());
				for(Classes cl:classess) {
					if(s.getCid()==cl.getId()) {
						vr.add(cl.getName());
						break;
					}
				}
				mm.addRow(vr);
				isFind=true;
			}
		}
		table.setModel(mm);
		return isFind;	
	}
	public static void findStudentAll(JTable table,DefaultTableModel mm) {
		
		classess=cla.<Classes>ExecuteQueryBySql("select * from "+Classes.tablename);
		students=stu.<Student>ExecuteQueryBySql("select * from "+Student.tablename);
		for(Student s:students) {
			Vector<Object> vr=new Vector<Object>();
			vr.add(s.getId());
			vr.add(s.getName());
			vr.add(s.getPassword());
			vr.add(s.getAddress());
			vr.add(s.getPhone());
			for(Classes cl:classess) {
				if(s.getCid()==cl.getId()) {
					vr.add(cl.getName());
					break;
				}
			}
			mm.addRow(vr);
		}
		table.setModel(mm);
	}
	@SuppressWarnings({ "static-access", "unused" })
	public static void addStudent(JTable table,JTable addtable,DefaultTableModel mm) {
		boolean isNull=false;
		for(int i=0;i<addtable.getColumnCount();i++) {
			if(addtable.getValueAt(0, i)==null) {
				new MainDialog().showMessageDialog(null, addtable.getColumnName(i)+"����Ϊ��!!!!");
				isNull=true;
				break;
			}
		}
		if(!isNull) {
			boolean isPhone=false;
			boolean isClass=false;
			Object phone=addtable.getValueAt(0, 4);
			isPhone=checkPhone(String.valueOf(phone));
			int cid=0;
			classess=cla.<Classes>ExecuteQueryBySql("select * from "+Classes.tablename);
			for(Classes c:classess) {
				if(c.getName().equals(addtable.getValueAt(0, 5))) {
					cid=c.getId();
					isClass=true;
					break;
				}
			}
			if(!isClass) {
				new MainDialog().showMessageDialog(null, "�ð༶�����ڣ�");
			}
			if(isClass&&isPhone) {
				String sql="insert into "+Student.tablename+" values("+addtable.getValueAt(0, 0)+",'"+addtable.getValueAt(0, 1)+"','"+addtable.getValueAt(0, 2)+"','"
						+addtable.getValueAt(0, 3)+"','"+phone+"',"+cid+")";
				isNull=stu.InsertBySql(sql);
				if(isNull) {
					DefaultTableModel model =(DefaultTableModel) table.getModel();
					table.tableChanged(new TableModelEvent(table.getModel()));
					for(int i=0;i<addtable.getColumnCount();i++) {
						addtable.setValueAt(null,0, i);
					}
					for (int index = mm.getRowCount() - 1; index >= 0; index--) {
						mm.removeRow(index);
					} 
					findStudentAll(table, mm);
					new MainDialog().showMessageDialog(null, "��ӳɹ���");
				}else {
					new MainDialog().showMessageDialog(null, "����:\n1.��鿴��ѧ���Ƿ��Ѵ���!\n2.��Ϣ�Ƿ���д���淶!");
				}
			}
		}
	}
	public static List<Student> tableListener(JTable table ,List<Student> data){
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		int row = table.getSelectedRow();
		if(row!=-1) {
			if(row<table.getRowCount()) {
				if(table.isRowSelected(row)) {
					//��ѡ�е����������ݴ浽������
					Student stuData=new Student();
					stuData.setId(Integer.parseInt(String.valueOf(model.getValueAt(row, 0))));
					stuData.setName(String.valueOf(model.getValueAt(row, 1)));
					stuData.setPassword(String.valueOf(model.getValueAt(row, 2)));
					stuData.setAddress(String.valueOf(model.getValueAt(row, 3)));
					Object phone=model.getValueAt(row, 4);
					boolean isPhone=EditStudentBiz.checkPhone(String.valueOf(phone));
					if(isPhone) {
						stuData.setPhone(String.valueOf(phone));
						for(Classes c:classess) {
							if(model.getValueAt(row, 5)==c.getName()) {
								stuData.setCid(c.getId());
								break;
							}
						}
						if(stuData.getCid()!=null) {
							data.add(stuData);
						}
					}
				}
			}
		}
		return data;
	}
	@SuppressWarnings({ "static-access", "unused" })
	public static void upStudent(JTable table ,List<Student> data) {
		boolean isUpdate=false;
		for(Student s:data) {
			String sql="update "+Student.tablename+" set name='"+s.getName()+"',password='"+s.getPassword()+"',address='"+s.getAddress()+"',phone='"+s.getPhone()+"',cid="+s.getCid()+" where id="+s.getId();
			isUpdate=stu.UpdateBySql(sql);
		}
		if(isUpdate) {
			DefaultTableModel model =(DefaultTableModel) table.getModel();
			table.tableChanged(new TableModelEvent(table.getModel()));
			new MainDialog().showMessageDialog(null, "�޸ĳɹ���");
			data.clear();
		}else {
			new MainDialog().showMessageDialog(null, "�޸�ʧ�ܣ�\n�����Ƿ����ѧ�ţ�");
			data.clear();
		}
	}
	@SuppressWarnings("static-access")
	public static void delStudent(JTable table) {
		boolean isRemove=false;
		int row = table.getSelectedRow();
		if(row!=-1) {
			String sql="delete from "+Student.tablename+" where id="+table.getValueAt(row, 0);
			isRemove=stu.UpdateBySql(sql);
			if(isRemove) {
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				table.tableChanged(new TableModelEvent(table.getModel()));
				model.removeRow(row);
				new MainDialog().showMessageDialog(null, "ɾ���ɹ�!");
			}else {
				new MainDialog().showMessageDialog(null, "ɾ��ʧ��!");
			}
		}else {
			new MainDialog().showMessageDialog(null, "��ѡ��Ҫɾ������!");
		}
	}
}
