package com.bdqn.staticmeth;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//不用创建实例的静态工具类 包括底层数据操作 ui动画等
public final class DataBase {
	private static final String ConStr="jdbc:mysql://localhost:3306/exam?characterEncoding=utf-8",
			  User="root",
			  Pwd="199598";
//	private static final String ConStr="jdbc:mysql://192.168.43.162:3306/exam?characterEncoding=utf-8",
//			  User="root",
//			  Pwd="";
	
	//链接指定的数据库(自定义参数)
	public  static Connection GetConnection(String constr,String user,String pwd) throws Exception  {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con= DriverManager.getConnection(constr,user,pwd);
		return con;
	}
	
	//连接程序默认的数据库
	public static Connection GetDefaultConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;
		con=DriverManager.getConnection(DataBase.ConStr,DataBase.User,DataBase.Pwd);
		return con;
	}
	
	//断开连接
	public static void CloseConnection(Connection con,PreparedStatement pstm,ResultSet rs) throws Exception {
		if(con!=null) {
			con.close();
		}
		if(pstm!=null) {
			pstm.close();
		}
		if(rs!=null) {
			rs.close();
		}
	}
	
	//sql查询  按照Field集合返回一个已经进行数据赋值封装后的实体类集合  参数:sql,反射类
	@SuppressWarnings("unchecked")
	public static <T>List<T> ExecuteQueryBySql(String sql,Class<?> cs) throws Exception {
		List<T> beans=new ArrayList<T>();
		Field fields[]=cs.getDeclaredFields();
		Connection con=DataBase.GetDefaultConnection();
		PreparedStatement pstm=con.prepareStatement(sql);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			Object bean=cs.newInstance();
			for(int i=0;i<fields.length;i++) {
				//掠过tablename和没有数据的属性成员
				if(fields[i].getName().equals("tablename")||(IsColumnExist(rs.getMetaData(),fields[i].getName())==false)) {continue;}
				Object data=rs.getObject(fields[i].getName()); 								// 取回对应值
				Reflection.invokeByMethodName(
						Reflection.GetSetMethodNameForEclipseEncap(fields[i].getName()), 	//反射方法名--加工为包装set函数
						data,																//反射方法的参数
						bean																//指定反射的对象
				);
			}
			beans.add((T) bean);
		}
		DataBase.CloseConnection(con, pstm, rs);
		return beans;
	}
	
	// 增删改基础
	public static int UpdateBySql(String sql) throws Exception {
		Connection con=DataBase.GetDefaultConnection();
		PreparedStatement pstm= con.prepareStatement(sql);
		return pstm.executeUpdate();
	}
	
	//判断是否有此列
	public static boolean IsColumnExist(ResultSetMetaData md,String columnname) throws Exception {
		for(int i=0;i<md.getColumnCount();i++) {
			if(md.getColumnName(i+1).equals(columnname)) {
				return true;
			};
		}
		return false;
	}
}
