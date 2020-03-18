package com.bdqn.data;

import java.util.List;

import com.bdqn.staticmeth.DataBase;
//数据通用类 继承dao通用接口 所有数据实体类都会继承他 所有impl数据实现类都会使用它的函数 函数会调用impl实现类所对应的实体类中的属性名查询数据库
//每添加一张表只需要实体类继承bean 对应的dao接口只需要写独特需求的函数 对应的impl只需要继承实体类实现对应dao接口的独特需求方法
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
	
	//删改
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
	//增
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
