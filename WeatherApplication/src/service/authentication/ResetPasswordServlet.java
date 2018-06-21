package service.authentication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.MySqlConnector;
import db.connection.UserDBView;
import entities.User;

/**
 * This servlet handles reset password service.
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Checks if the user's current password matches with the old password entered by user, then update the new password in the DB.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String oldPass = request.getParameter("oldpass");
		String newPass = request.getParameter("newpass");
		String renewPass = request.getParameter("renewpass");
		String uname = (String)request.getSession().getAttribute("user");
		try
		{
			User user = UserDBView.selectUser(uname);
			
			if(oldPass == null || oldPass.isEmpty() || newPass == null || newPass.isEmpty() 
					|| renewPass == null || renewPass.isEmpty())
					{
						request.setAttribute("msg","All fields are mandatory.");
						request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
					}
			else if(!newPass.equals(renewPass))
			{
				request.setAttribute("msg","Password does not match.");
				request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
			}
			else if(!user.getPassword().equals(oldPass))
			{
				request.setAttribute("msg","Old password is not correct.");
				request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
			}
			else
			{
				UserDBView.updateUserPassword(uname, newPass);
				System.out.println("Password Updated Successfully!!!!");
				request.setAttribute("msg","Password Updated Successfully!");
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
