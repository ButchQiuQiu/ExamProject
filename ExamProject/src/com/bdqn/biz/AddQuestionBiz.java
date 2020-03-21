package com.bdqn.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.bdqn.data.bean.Book;
import com.bdqn.data.bean.Chapter;
import com.bdqn.data.bean.Major;
import com.bdqn.data.bean.Question;
import com.bdqn.data.impl.BookDaoImpl;
import com.bdqn.data.impl.ChapterDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;
import com.bdqn.data.impl.QuestionDaoImpl;
import com.bdqn.ui.MainDialog;
import com.bdqn.ui.AddQuestion;

//��������
public final class AddQuestionBiz {
	
	private AddQuestionBiz() {
		
	}
	 
	
	public static boolean addQuestion(Question q) {
		boolean isFlag=false;
		String sql=String.format("insert into question(title,`option`,solution,analysis,chapid) values('%s','%s','%s','%s','%s')",q.getTitle(),q.getOption(),q.getSolution(),q.getAnalysis(),q.getChapid());
		isFlag=new QuestionDaoImpl().InsertBySql(sql);
		if(isFlag) {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"����ɹ�!");
			return true;
		}else {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"����ʧ��!");
			return isFlag;
		}
		
	}
	
	public static int chapter(String name) {
		int i=0;
		i=(new ChapterDaoImpl().<Chapter>ExecuteQueryBySql("select id from "+Chapter.tablename+" where name='"+name+"'").get(0).getId());
		return i;
	}
	
	public static void SelectComboBox(JComboBox<String>majorhb,JComboBox<String>book,JComboBox<String>chapter) {
		if(majorhb.getSelectedItem().equals("��ѡ��")) {
			AddQuestion t=new AddQuestion();
			t.init();//��ʼ������
			
		}else {
			String majorName=majorhb.getSelectedItem().toString();//���ѡ���רҵ
			int majorId=(new MajorDaoImpl().<Major>ExecuteQueryBySql("select id from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
			
			List<Book>lbook=new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" where mid="+majorId);//����רҵ��ȡ���еĸ�רҵ�����������Ŀ
			
			List<String>stringBook=new ArrayList<String>();//����������Ŀ������
			//stringBook.add("��ѡ��");
			for(Book k:lbook) {
				stringBook.add(k.getName());//��ø�רҵ�����������Ŀ
			}
			book.setModel(new DefaultComboBoxModel<String>(stringBook.toArray(new String[stringBook.size()])));//��Ŀˢ��
			selecComboBoxChapter(book,chapter);
			
		}
	
	}
	
	public static void selecComboBoxChapter(JComboBox<String>book,JComboBox<String>chapter) {

		String bookName=book.getSelectedItem().toString();//�����Ŀ
		
		int bookId=(new BookDaoImpl().<Book>ExecuteQueryBySql("select * from "+Book.tablename+" where name='"+bookName+"'")).get(0).getId();//���ѡ����Ŀ�Ķ�Ӧ���ݿ��еı��
		
		List<Chapter> listchapter=new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" where mid="+bookId);//������Ŀ��ѯ����Ŀ�µ������½�
		
		List<String>chaperString=new ArrayList<String>();
		for(Chapter c:listchapter) {
			chaperString.add(c.getName());
		}
		chapter.setModel(new DefaultComboBoxModel<String>(chaperString.toArray(new String[chaperString.size()])));
		
	} 
}
