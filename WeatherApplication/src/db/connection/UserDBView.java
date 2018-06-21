package db.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.User;

/**
 * This class provides methods for insert/update/select operations on DB table USER
 * @author Urvi
 *
 */
public class UserDBView {
	
	/**
	 * Inserts one record in USER table
	 * @param user
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertUser(User user) throws SQLException, ClassNotFoundException
	{
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		try 
		{
			con = db.getConnection();		
			stmt =  con.createStatement();
			stmt.executeUpdate("insert into user (fname,lname,uname,pass,email) " +
					"values('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getUname()+"','"+user.getPassword()
					+"','"+user.getEmail()+"')");
		} 
		finally
		{
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					//ignore
				}
			}
			if(con != null)
			{
				try
				{						
					con.close();
				}
				catch(SQLException e)
				{
					//ignore
				}
			}
		}


	}
	
	/**
	 * Fetch record based in uname from table USER and returns a 'user' object for it.
	 * If record not found, null is returned.
	 * @param uname
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static User selectUser(String uname) throws SQLException, ClassNotFoundException
	{
		User user = null;
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = db.getConnection();		
			stmt = con.createStatement();
				rs = stmt.executeQuery("select * from user where uname = '"+uname+"'");
				if(rs.next())
				{
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String pass = rs.getString("pass");
					String email = rs.getString("email");
					user = new User(uname,pass,fname,lname,email);
				}
				return user;
			} 
			
			finally
			{
				if(stmt != null)
				{
					try
					{
						stmt.close();
					}
					catch(SQLException e)
					{
						//ignore
					}
				}
				if(con != null)
				{
					try
					{						
						con.close();
					}
					catch(SQLException e)
					{
						//ignore
					}
				}
				if(rs != null)
				{
					try
					{						
						rs.close();
					}
					catch(SQLException e)
					{
						//ignore
					}
				}
			}

	}
	
	/**
	 * Updates password in table USER for record corresponding to uname
	 * @param uname
	 * @param password
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updateUserPassword(String uname, String password) throws SQLException, ClassNotFoundException
	{
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		try {
			con = db.getConnection();		
			stmt = con.createStatement();
			stmt.executeUpdate("update user set pass = '"+password+"' where uname = '"+uname+"'");
		}
		finally
		{
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					//ignore
				}
			}
			if(con != null)
			{
				try
				{						
					con.close();
				}
				catch(SQLException e)
				{
					//ignore
				}
			}
		}	

	}

}
