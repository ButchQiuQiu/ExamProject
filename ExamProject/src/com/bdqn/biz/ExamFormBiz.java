package com.bdqn.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bdqn.data.bean.Exampaper;
import com.bdqn.data.bean.Question;
import com.bdqn.data.bean.Result;
import com.bdqn.data.impl.ExampaperDaoImpl;
import com.bdqn.data.impl.QuestionDaoImpl;
import com.bdqn.data.impl.ResultDaoImpl;

//业务考试
public final class ExamFormBiz {
	
	public Exampaper findExampaper(int id) {//根据编号查询指定考试的信息
		Exampaper exam=new Exampaper();
		
		String sql="select * from Exampaper where id="+id;
		exam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);
		return exam;
	}
	
	public List<Question> findQuestion(String id){//根据试题编号查询指定的信息
		List<Question>listQuestion=new ArrayList<Question>();
		String sql="select * from question";		
		String sql1=id.replaceAll("\\*","=");//替换

		String []queid=sql1.split("=");//分割
		if(!(id.equals(""))) {
			sql="select * from question where";
			for(int i=0;i<queid.length;i++) {
				if(queid.length-1==i) {
					sql+=" id="+Integer.parseInt(queid[i]);
				}else {
					sql+=" id="+Integer.parseInt(queid[i])+" or";
				}
			}
		}
		listQuestion=new QuestionDaoImpl().<Question>ExecuteQueryBySql(sql);
		return listQuestion;
	}
	
	//保存成绩
	public  boolean doResult(Result result) {
		boolean isFlag=false;
		result.setDatetime(time());//获得时间
		String sql=String.format("INsert INTO "+Result.tablename+"(stuid,examname,`option`,score,datetime) values(%d,'%s','%s',%5.1f,'%s')"
				,result.getStuid(),result.getExamname(),result.getOption(),result.getScore(),result.getDatetime());
		isFlag=new ResultDaoImpl().InsertBySql(sql);
		System.out.println(sql);
		return isFlag;
	}
	
	//获取当前时间
		public String time() {
			Date date=new Date();
			//SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String tiem=sf.format(date);
			return date.toString();
		}
		
	//获得班级同时获得试卷
		
	public Exampaper findClasses(int id) {//获得学生所在的班级
		String sql="select * from exampaper where classid in (select cid from Student where id="+id+");";
		return new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);//获得第一章试卷
		
	}
		
}	
