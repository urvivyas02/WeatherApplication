package db.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides methods for insert/update/select operations on DB table USER_HISTORY
 * @author Urvi
 *
 */
public class UserHistoryDBView {
	
	/**
	 * Inserts one record in USER_HISTORY table
	 * @param uname
	 * @param history
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertUserHistory(String uname, String history) throws SQLException, ClassNotFoundException
	{
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		try {
			con = db.getConnection();		
			stmt = con.createStatement();
			stmt.executeUpdate("insert into user_history (uname,history) " +
					"values('"+uname+"','"+history+"')");
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
	 * Fetch record based on uname from table USER_HISTORY and returns 'history' for it.
	 * If record not found, empty string is returned.
	 * @param uname
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String selectUserHistory(String uname) throws SQLException, ClassNotFoundException
	{
		String userHistory="";
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = db.getConnection();		
			stmt = con.createStatement();
			 rs = stmt.executeQuery("select * from user_history where uname = '"+uname+"'");
				if(rs.next())
				{
					userHistory = rs.getString("history");
				}
				return userHistory;
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
	 * Updates history in table USER_HISTORY for record corresponding to uname
	 * @param uname
	 * @param history
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateUserHistory(String uname, String history) throws ClassNotFoundException, SQLException
	{
		MySqlConnector db = new MySqlConnector();
		Connection con = null;
		Statement stmt = null;
		try {
			con = db.getConnection();		
			stmt = con.createStatement();
			stmt.executeUpdate("update user_history set history = '"+history+"' where uname = '"+uname+"'");
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

