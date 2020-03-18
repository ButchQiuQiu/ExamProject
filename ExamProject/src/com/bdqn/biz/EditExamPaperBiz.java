package com.bdqn.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.bdqn.data.bean.Book;
import com.bdqn.data.bean.Chapter;
import com.bdqn.data.bean.Classes;
import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Major;
import com.bdqn.data.bean.Question;
import com.bdqn.data.impl.BookDaoImpl;
import com.bdqn.data.impl.ChapterDaoImpl;
import com.bdqn.data.impl.ClassesDaoImpl;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.MajorDaoImpl;
import com.bdqn.data.impl.QuestionDaoImpl;
import com.bdqn.ui.MainDialog;

public final class EditExamPaperBiz {
	private static final int QUESTIONCOUNT=50;
	//下拉栏更改事件
	public static void SelectComboBox(JComboBox<String> jcbMajor, JComboBox<String> jcbBook, JComboBox<String> jcbChapter,JComboBox<String> jcbClass) {
		if(jcbMajor==null) {
			int BookId=(new BookDaoImpl().<Book>ExecuteQueryBySql("select * from "+Book.tablename+" where name='"+jcbBook.getSelectedItem()+"'")).get(0).getId();
			List<Chapter> chapter = new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" where mid="+BookId);
			List<String> chapternames = new ArrayList<String>();
			for(Chapter tempchapter:chapter) {
				chapternames.add(tempchapter.getName());
			}
			jcbChapter.setModel(new DefaultComboBoxModel<String>(chapternames.toArray(new String[chapternames.size()])));
			return;
		}
		String majorName = jcbMajor.getSelectedItem().toString();
		int MajorId = (new MajorDaoImpl().<Major>ExecuteQueryBySql("select * from "+Major.tablename+" where name='"+majorName+"'")).get(0).getId();
		List<Book> book = new BookDaoImpl().ExecuteQueryBySql("select * from "+Book.tablename+" where mid="+MajorId);
		int BookId=(new BookDaoImpl().<Book>ExecuteQueryBySql("select * from "+Book.tablename+" where name='"+jcbBook.getSelectedItem()+"'")).get(0).getId();
		List<Chapter> chapter = new ChapterDaoImpl().ExecuteQueryBySql("select * from "+Chapter.tablename+" where mid="+BookId);
		List<String> booknames = new ArrayList<String>();
		List<String> chapternames = new ArrayList<String>();
		for(Book tempbook:book) {
			booknames.add(tempbook.getName());
		}
		for(Chapter tempchapter:chapter) {
			chapternames.add(tempchapter.getName());
		}
		jcbBook.setModel(new DefaultComboBoxModel<String>(booknames.toArray(new String[booknames.size()])));
		jcbChapter.setModel(new DefaultComboBoxModel<String>(chapternames.toArray(new String[chapternames.size()])));
		
		List<Classes> classs = new ArrayList<Classes>();
		classs = new ClassesDaoImpl().ExecuteQueryBySql(
				"select * from "+Classes.tablename+" where mid=ANY(select id from "+Major.tablename+" where name='"+jcbMajor.getSelectedItem()+"')");
		List<String> classnames = new ArrayList<String>();
		for(Classes tempClass:classs) {
			classnames.add(tempClass.getName());
		}
		jcbClass.setModel(new DefaultComboBoxModel<String>(classnames.toArray(new String[classnames.size()])));
	}

	//新建试卷
	public static void AddExamPaper(String id,String examName,String score,String chapterName,String className,String questionCount) {
		if(id.equals("")||examName.equals("")||score.equals("")||questionCount.equals("")) {
			MainDialog.DefaultMessage("输入栏不能为空");
			return;
		}
		
		int classId=new ClassesDaoImpl().<Classes>ExecuteQueryBySql("select id from "+Classes.tablename+" where name='"+className+"'").get(0).getId();
		if(new ExampaperDaoImpl().ExecuteQueryBySql("select * from "+Exampaper.tablename+" where id="+id+" or name='"+examName+"'").size()!=0) {
			MainDialog.DefaultMessage("此试卷已存在 ");
			return;
		}else if(new ExampaperDaoImpl().ExecuteQueryBySql(
				"select * from "+Exampaper.tablename+" where classid="+classId).size()!=0) {
			MainDialog.DefaultMessage("此班级已有一张试卷 ");
			return;
		}
		String questionsStr=null;
		List <Question>questions=new ArrayList<Question>();
		questions=new QuestionDaoImpl().ExecuteQueryBySql("select * from "+Question.tablename+" where chapid=ANY(select id from "+Chapter.tablename+" where name='"+chapterName+"')");
		if(Integer.parseInt(questionCount)>questions.size()) {
			MainDialog.DefaultMessage("此章节的题目只有"+questions.size()+"个,题目数量要少于"+questions.size()+"个");
			return;
		}
		int temp=0;
		for(Question tempQuestion:questions) {
			if(questionsStr==null) {
				questionsStr=tempQuestion.getId()+"";
			}else if(temp<Integer.parseInt(questionCount)){
				questionsStr=questionsStr+"*"+tempQuestion.getId();
			}
			temp++;
		}
		if(new ExampaperDaoImpl().InsertBySql("insert into "+Exampaper.tablename+" (id,name,qids,score,classid,count)values"
				+ "("+id+",'"+examName+"','"+questionsStr+"',"+score+","+classId+","+questionCount+")")) {
			MainDialog.DefaultMessage("添加试卷成功!");
		}
	}
	
	//删除试卷
	public static void DeleteExamPaper(String className) {
		
		if(new ExampaperDaoImpl().UpdateBySql("delete from "+Exampaper.tablename+" where classid=ANY(select id from "+Classes.tablename+" where name='"+className+"')")) {
			MainDialog.DefaultMessage("删除试卷成功");
		}else {
			MainDialog.DefaultMessage("删除试卷失败 这个班级没有试卷");
		}
	}

	//增加题目
	public static void AddQuestion(String chapterName,String className,String questionCount) {
		String tempSql="select * from "+Exampaper.tablename+" where classid=ANY(select id from "+Classes.tablename+" where name='"+className+"')";
		
		String questionsStr=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(tempSql).get(0).getQids();
		
		List <Question>questions=new ArrayList<Question>();
		questions=new QuestionDaoImpl().ExecuteQueryBySql("select * from "+Question.tablename+" where chapid=ANY(select id from "+Chapter.tablename+" where name='"+chapterName+"')");
		if(Integer.parseInt(questionCount)>questions.size()) {
			MainDialog.DefaultMessage("此章节的题目只有"+questions.size()+"个,插入题目数量要少于"+questions.size()+"个");
			return;
		}
		int temp=0;
		for(Question tempQuestion:questions) {
			if(questionsStr==""||questionsStr==null) {
				questionsStr=tempQuestion.getId()+"";
			}else if(temp<Integer.parseInt(questionCount)){
				questionsStr=questionsStr+"*"+tempQuestion.getId();
			}
			temp++;
		}
		//判断加的题目是否超过最大
		int allQuestioncount=questionsStr.length()-questionsStr.replaceAll("\\*", "").length();
		if(allQuestioncount>50) {
			MainDialog.DefaultMessage("题目总数不能超过"+EditExamPaperBiz.QUESTIONCOUNT+"个,已经有"+allQuestioncount+"道题目");
			return;
		}
		if(new ExampaperDaoImpl().UpdateBySql("update "+Exampaper.tablename+" set qids='"+questionsStr
				+"' where classid=ANY(select id from "+Classes.tablename+" where name='"+className+"')")) {
			
			MainDialog.DefaultMessage("添加题目成功");
		}else {
			MainDialog.DefaultMessage("添加题目失败");
		}
		
	}
	
	
}
