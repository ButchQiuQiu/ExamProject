package com.bdqn.staticmeth;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//���ô���ʵ���ľ�̬������ �����ײ����ݲ��� ui������
public final class DataBase {
	private static final String ConStr="jdbc:mysql://localhost:3306/exam?characterEncoding=utf-8",
			  User="root",
			  Pwd="199598";
//	private static final String ConStr="jdbc:mysql://192.168.43.162:3306/exam?characterEncoding=utf-8",
//			  User="root",
//			  Pwd="";
	
	//����ָ�������ݿ�(�Զ������)
	public  static Connection GetConnection(String constr,String user,String pwd) throws Exception  {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con= DriverManager.getConnection(constr,user,pwd);
		return con;
	}
	
	//���ӳ���Ĭ�ϵ����ݿ�
	public static Connection GetDefaultConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;
		con=DriverManager.getConnection(DataBase.ConStr,DataBase.User,DataBase.Pwd);
		return con;
	}
	
	//�Ͽ�����
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
	
	//sql��ѯ  ����Field���Ϸ���һ���Ѿ��������ݸ�ֵ��װ���ʵ���༯��  ����:sql,������
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
				//�ӹ�tablename��û�����ݵ����Գ�Ա
				if(fields[i].getName().equals("tablename")||(IsColumnExist(rs.getMetaData(),fields[i].getName())==false)) {continue;}
				Object data=rs.getObject(fields[i].getName()); 								// ȡ�ض�Ӧֵ
				Reflection.invokeByMethodName(
						Reflection.GetSetMethodNameForEclipseEncap(fields[i].getName()), 	//���䷽����--�ӹ�Ϊ��װset����
						data,																//���䷽���Ĳ���
						bean																//ָ������Ķ���
				);
			}
			beans.add((T) bean);
		}
		DataBase.CloseConnection(con, pstm, rs);
		return beans;
	}
	
	// ��ɾ�Ļ���
	public static int UpdateBySql(String sql) throws Exception {
		Connection con=DataBase.GetDefaultConnection();
		PreparedStatement pstm= con.prepareStatement(sql);
		return pstm.executeUpdate();
	}
	
	//�ж��Ƿ��д���
	public static boolean IsColumnExist(ResultSetMetaData md,String columnname) throws Exception {
		for(int i=0;i<md.getColumnCount();i++) {
			if(md.getColumnName(i+1).equals(columnname)) {
				return true;
			};
		}
		return false;
	}
}
