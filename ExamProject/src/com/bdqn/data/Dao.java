package com.bdqn.data;

import java.util.List;

//���нӿڵĸ��� ��2��ͨ�÷��� ���ĳ�ű�������صĹ��ܾ�д�ڶ�Ӧ�Ľӿ��� 
public interface Dao {
	
	//��
	public <T>List<T>  ExecuteQueryBySql(String sql);
	//ɾ��
	public boolean UpdateBySql(String sql);
	//��
	public boolean InsertBySql(String sql);
}
