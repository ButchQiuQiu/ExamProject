package com.bdqn.staticmeth;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class Reflection {
	//��ȡʵ���������������(ȥ��tablename ��Ҫ����ʵ�����superclass())
	@SuppressWarnings({ "rawtypes", "unused" })
	public static ArrayList<String> GetBeansAllFields(Class cs){
		ArrayList<String> beanmethods=new ArrayList<String>(); 
		try {
			Object bean=cs.newInstance(); //����һ��ʵ��
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
	
	//�Ѵ����������ת��Ϊeclipse��Ĭ�Ϸ�װ�� Get
	public static String  GetGetMethodNameForEclipseEncap(String fieldname) {
		char[] temp=fieldname.toCharArray();
		temp[0]=Character.toUpperCase(temp[0]);
		return "get"+new String(temp);
	}
	
	
	//�Ѵ����������ת��Ϊeclipse��Ĭ�Ϸ�װ�� Set
	public static String GetSetMethodNameForEclipseEncap(String fieldname) {
		char[] temp=fieldname.toCharArray();
		temp[0]=Character.toUpperCase(temp[0]);
		return "set"+new String(temp);
	}
	
	
	//ʹ��class��ĳ������ ������һ��data���� ����:������,����,����bean
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
