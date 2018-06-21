package service.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.UserDBView;
import entities.User;

/**
 * This servlet handles new user registration in system.
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Validates user for duplicate entry and register in the system if no duplicate user is found.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String first_name = request.getParameter("fname");
		String last_name = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		
		if(uname == null || uname.isEmpty() || pass == null || pass.isEmpty())
		{
				request.setAttribute("errorMsg","username and password fields are mandatory.");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
				return;
		}
		try
		{
			User duplicateUser = UserDBView.selectUser(uname);
			if (duplicateUser != null)
			{
				request.setAttribute("errorMsg","User already exist.Please login");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			}
			else
			{
				User user = new User(uname,pass,first_name,last_name,email);	
				UserDBView.insertUser(user);
				System.out.println("New user entered successfully !!!");
				response.sendRedirect("homepage.jsp");
			}
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

}
