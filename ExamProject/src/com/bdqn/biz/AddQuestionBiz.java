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

//锟斤拷锟斤拷锟斤拷锟斤拷
public final class AddQuestionBiz {
	
	private AddQuestionBiz() {
		
	}
	 
	
	public static boolean addQuestion(Question q) {
		boolean isFlag=false;
		String sql=String.format("insert into question(title,`option`,solution,analysis,chapid) values('%s','%s','%s','%s','%s')",q.getTitle(),q.getOption(),q.getSolution(),q.getAnalysis(),q.getChapid());
		isFlag=new QuestionDaoImpl().InsertBySql(sql);
		if(isFlag) {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"锟斤拷锟斤拷晒锟�!");
			return true;
		}else {
			new MainDialog();
			JOptionPane.showMessageDialog(null,"锟斤拷锟斤拷失锟斤拷!");
			return isFlag;
		}
		
	}
	
	public static int chapter(String name) {
		int i=0;
		i=(new ChapterDaoImpl().<Chapter>ExecuteQueryBySql("select id from "+Chapter.tablename+" where name='"+name+"'").get(0).getId());
		return i;
	}
	
	public static void SelectComboBox(JComboBox<String>majorhb,JComboBox<String>book,JComboBox<String>chapter) {
		if(majorhb.getSelectedItem().equals("锟斤拷选锟斤拷")) {
			AddQuestion t=new AddQuestion();
			t.init();//锟斤拷始锟斤拷锟斤拷锟斤拷
			
		}else {
			String majorName=majorhb.getSelectedItem().toString();//锟斤拷锟窖★拷锟斤拷专业
			int majorId=(new MajorDaoImpl().<Major>ExecuteQueryBySql("select id from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
			
			List<Book>lbook=new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" where mid="+majorId);//锟斤拷锟斤拷专业锟斤拷取锟斤拷锟叫的革拷专业锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥�
			
			List<String>stringBook=new ArrayList<String>();//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷目锟斤拷锟斤拷锟斤拷
			//stringBook.add("锟斤拷选锟斤拷");
			for(Book k:lbook) {
				stringBook.add(k.getName());//锟斤拷酶锟阶ㄒ碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷目
			}
			book.setModel(new DefaultComboBoxModel<String>(stringBook.toArray(new String[stringBook.size()])));//锟斤拷目刷锟斤拷
			selecComboBoxChapter(book,chapter);
			
		}
	
	}
	
	public static void selecComboBoxChapter(JComboBox<String>book,JComboBox<String>chapter) {

		String bookName=book.getSelectedItem().toString();//锟斤拷锟斤拷锟侥�
		
		int bookId=(new BookDaoImpl().<Book>ExecuteQueryBySql("select * from "+Book.tablename+" where name='"+bookName+"'")).get(0).getId();//锟斤拷锟窖★拷锟斤拷锟侥匡拷亩锟接︼拷锟斤拷菘锟斤拷械谋锟斤拷
		
		List<Chapter> listchapter=new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" where mid="+bookId);//锟斤拷锟斤拷锟斤拷目锟斤拷询锟斤拷锟斤拷目锟铰碉拷锟斤拷锟斤拷锟铰斤拷
		
		List<String>chaperString=new ArrayList<String>();
		for(Chapter c:listchapter) {
			chaperString.add(c.getName());
		}
		chapter.setModel(new DefaultComboBoxModel<String>(chaperString.toArray(new String[chaperString.size()])));
		
	} 
}
