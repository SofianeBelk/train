package trainLine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// j'ai utilisé la méme classe que dans l'UE Technologie web (Licence 3)

public class BaseTools {
	public static String MYSQLHOST="localhost";
	public static String MYSQLDB="TrainBase";
	
	public static boolean MYSQLPOOLING=false;
	
	
	public static String MYSQLUSERNAME="belkhir";  //a modifier selon  l'username
	public static String MYSQLPASSWORD="root";    //a modifier selon le mot de passe associe a l'username
	
	private DataSource dataSource;
	public static BaseTools database=null;
	
	public BaseTools(String jndiName) throws SQLException{
		try {
			dataSource =(DataSource) new InitialContext().lookup("java:comp/env/"+jndiName);
		} catch (NamingException e) {
			throw new SQLException(jndiName+"is missing in JNDI :"+e.getMessage());
		}
	}
	
	public Connection getConnection()throws SQLException{
		return dataSource.getConnection();
	}
	
	public static Connection getMySQLConnection()throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 

		} catch(java.lang.ClassNotFoundException e) {
			 System.err.print("Exception: ");
			 System.err.println(e.getMessage());
		}
		if (MYSQLPOOLING==false) {
			return (DriverManager.getConnection("jdbc:mysql://"+MYSQLHOST+"/"+MYSQLDB,MYSQLUSERNAME,MYSQLPASSWORD));
		} else {
			if (database==null) {
				database=new BaseTools("jdbc/"+MYSQLDB);
			} 
			return (database.getConnection());
		}
	}
	
	

}
