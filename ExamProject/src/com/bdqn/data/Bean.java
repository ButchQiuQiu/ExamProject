package com.bdqn.data;

import java.util.List;

import com.bdqn.staticmeth.DataBase;
//����ͨ���� �̳�daoͨ�ýӿ� ��������ʵ���඼��̳��� ����impl����ʵ���඼��ʹ�����ĺ��� ���������implʵ��������Ӧ��ʵ�����е���������ѯ���ݿ�
//ÿ���һ�ű�ֻ��Ҫʵ����̳�bean ��Ӧ��dao�ӿ�ֻ��Ҫд��������ĺ��� ��Ӧ��implֻ��Ҫ�̳�ʵ����ʵ�ֶ�Ӧdao�ӿڵĶ������󷽷�
public abstract class Bean implements Dao{
	public Bean() {};
	public <T>List<T> ExecuteQueryBySql(String sql){
		try {
			return DataBase.ExecuteQueryBySql(sql, this.getClass().getSuperclass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//ɾ��
	public boolean UpdateBySql(String sql) {
		try {
			if(DataBase.UpdateBySql(sql)!=0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//��
	public boolean InsertBySql(String sql) {
		try {
			if(DataBase.UpdateBySql(sql)!=0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
