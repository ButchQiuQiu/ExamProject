package com.bdqn.staticmeth;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class Reflection {
	//获取实体类的所有属性名(去除tablename 需要传入实现类的superclass())
	@SuppressWarnings({ "rawtypes", "unused", "deprecation" })
	public static ArrayList<String> GetBeansAllFields(Class cs){
		ArrayList<String> beanmethods=new ArrayList<String>(); 
		try {
			Object bean=cs.newInstance(); //创建一个实例
			Field[] fields= cs.getDeclaredFields();
			for(Field fieldtemp:fields) {
				if(fieldtemp.getName().equals("tablename")==false) {
					beanmethods.add(fieldtemp.getName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanmethods;
	}
	
	//把传入的属性名转换为eclipse的默认封装名 Get
	public static String  GetGetMethodNameForEclipseEncap(String fieldname) {
		char[] temp=fieldname.toCharArray();
		temp[0]=Character.toUpperCase(temp[0]);
		return "get"+new String(temp);
	}
	
	
	//把传入的属性名转换为eclipse的默认封装名 Set
	public static String GetSetMethodNameForEclipseEncap(String fieldname) {
		char[] temp=fieldname.toCharArray();
		temp[0]=Character.toUpperCase(temp[0]);
		return "set"+new String(temp);
	}
	
	
	//使用class的某个函数 仅传入一个data参数 参数:方法名,参数,传参bean
	public static Object invokeByMethodName(String MethodName,Object data,Object bean) {
		try {
			Method method=bean.getClass().getDeclaredMethod(MethodName,data.getClass());
			method.invoke(bean,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
}
