package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class provides the method for retrieving connection to My sql DB.
 * @author Urvi
 *
 */
public class MySqlConnector {
		
	/**
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException  
	{
		Connection conn =null;		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB","root","root");			
		return conn;
		
	}

}
