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

//ҵ����
public final class ExamFormBiz {
	
	public Exampaper findExampaper(int id) {//���ݱ�Ų�ѯָ�����Ե���Ϣ
		Exampaper exam=new Exampaper();
		
		String sql="select * from Exampaper where id="+id;
		exam=new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);
		return exam;
	}
	
	public List<Question> findQuestion(String id){//���������Ų�ѯָ������Ϣ
		List<Question>listQuestion=new ArrayList<Question>();
		String sql="select * from question";		
		String sql1=id.replaceAll("\\*","=");//�滻

		String []queid=sql1.split("=");//�ָ�
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
	
	//����ɼ�
	public  boolean doResult(Result result) {
		boolean isFlag=false;
		result.setDatetime(time());//���ʱ��
		String sql=String.format("INsert INTO "+Result.tablename+"(stuid,examname,`option`,score,datetime) values(%d,'%s','%s',%5.1f,'%s')"
				,result.getStuid(),result.getExamname(),result.getOption(),result.getScore(),result.getDatetime());
		isFlag=new ResultDaoImpl().InsertBySql(sql);
		System.out.println(sql);
		return isFlag;
	}
	
	//��ȡ��ǰʱ��
		public String time() {
			Date date=new Date();
			//SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String tiem=sf.format(date);
			return date.toString();
		}
		
	//��ð༶ͬʱ����Ծ�
		
	public Exampaper findClasses(int id) {//���ѧ�����ڵİ༶
		String sql="select * from exampaper where classid in (select cid from Student where id="+id+");";
		return new ExampaperDaoImpl().<Exampaper>ExecuteQueryBySql(sql).get(0);//��õ�һ���Ծ�
		
	}
		
}	
