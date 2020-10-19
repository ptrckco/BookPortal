package booklist.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import booklist.model.bean.AdminLogin;

public class AdminLoginDAO {
	
	public static boolean validate(AdminLogin loginBean) throws ClassNotFoundException {
		boolean status = false;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
			
		Class.forName("com.mysql.cj.jdbc.Driver");

	try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "mysql");

			// Step 2:Create a statement using connection object
			ps = conn.prepareStatement("select * from users where uname = ? and pws = ? "); 
			ps.setString(1, loginBean.getUname());
			ps.setString(2, loginBean.getPws());

			//System.out.println(ps);
			rs = ps.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			
		}
		finally {
			if (rs != null)	{
				try {
					rs.close();
				} catch (Exception e) {}
					rs = null;
				}
		
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {}
			}
		}
	
	return status;
	}

}
