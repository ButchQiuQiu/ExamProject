package com.bdqn.data;

import java.util.List;

//所有接口的父类 有2个通用方法 如果某张表需求独特的功能就写在对应的接口上 
public interface Dao {
	
	//查
	public <T>List<T>  ExecuteQueryBySql(String sql);
	//删改
	public boolean UpdateBySql(String sql);
	//增
	public boolean InsertBySql(String sql);
}
