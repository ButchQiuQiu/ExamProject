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

//增加试题
public final class AddQuestionBiz {
	
	private AddQuestionBiz() {
		
	}
	
	
	public static boolean addQuestion(Question q) {
		boolean isFlag=false;
		String sql=String.format("insert into question(title,`option`,solution,analysis,chapid) values('%s','%s','%s','%s','%s')",q.getTitle(),q.getOption(),q.getSolution(),q.getAnalysis(),q.getChapid());
		isFlag=new QuestionDaoImpl().InsertBySql(sql);
		if(isFlag) {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"保存成功!");
			return true;
		}else {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"保存失败!");
			return isFlag;
		}
		
	}
	
	public static int chapter(String name) {
		int i=0;
		i=(new ChapterDaoImpl().<Chapter>ExecuteQueryBySql("select id from "+Chapter.tablename+" where name='"+name+"'").get(0).getId());
		return i;
	}
	
	public static void SelectComboBox(JComboBox<String>majorhb,JComboBox<String>book,JComboBox<String>chapter) {
		if(majorhb.getSelectedItem().equals("请选择")) {
			AddQuestion t=new AddQuestion();
			t.init();//初始化数据
			
		}else {
			String majorName=majorhb.getSelectedItem().toString();//获得选择的专业
			int majorId=(new MajorDaoImpl().<Major>ExecuteQueryBySql("select id from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
			
			List<Book>lbook=new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" where mid="+majorId);//根据专业获取所有的该专业下面的所有书目
			
			List<String>stringBook=new ArrayList<String>();//保存所有书目的名称
			//stringBook.add("请选择");
			for(Book k:lbook) {
				stringBook.add(k.getName());//获得该专业下面的所有书目
			}
			book.setModel(new DefaultComboBoxModel<String>(stringBook.toArray(new String[stringBook.size()])));//书目刷新
			selecComboBoxChapter(book,chapter);
			
		}
	
	}
	
	public static void selecComboBoxChapter(JComboBox<String>book,JComboBox<String>chapter) {

		String bookName=book.getSelectedItem().toString();//获得书目
		
		int bookId=(new BookDaoImpl().<Book>ExecuteQueryBySql("select * from "+Book.tablename+" where name='"+bookName+"'")).get(0).getId();//获得选中书目的对应数据库中的编号
		
		List<Chapter> listchapter=new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" where mid="+bookId);//根据书目查询该书目下的所有章节
		
		List<String>chaperString=new ArrayList<String>();
		for(Chapter c:listchapter) {
			chaperString.add(c.getName());
		}
		chapter.setModel(new DefaultComboBoxModel<String>(chaperString.toArray(new String[chaperString.size()])));
		
	} 
}
