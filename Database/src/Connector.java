import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.Statement;

public class Connector {
Connection con;
Statement stat;
static String url,database,username,password,port,driver,hostname;

public Connector(Properties props,String pass){
	database=props.getProperty("Database");
	username=props.getProperty("User_Name");
	password=pass;
	hostname=props.getProperty("Host_Name");
	port=props.getProperty("Port");
	driver="com.mysql.jdbc.Driver";
	url="jdbc:mysql://"+hostname+":"+port+"/"+database;
}
public boolean open(){
	try {
		DriverManager.registerDriver((java.sql.Driver)Class.forName(driver).newInstance());
	   con =DriverManager.getConnection(url,username,password);
	stat=con.createStatement();
	
	}
	
	catch (Exception e) {
		
		e.printStackTrace();
		if(con==null)
			return false;
	}
	System.out.println("Connection successful");
	return true;
}
public ResultSet executeQuery(String s) throws SQLException{
	return stat.executeQuery(s);
}
public void executeUpdate(String s) throws SQLException {
	stat.executeUpdate(s);
}



}
