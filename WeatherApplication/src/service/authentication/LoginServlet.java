package service.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import db.connection.UserDBView;
import entities.User;

/**
 * This servlet provides login service.
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Checks username and password and do login. 
	 * If user not present or username and password don't match, gives error message.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		if(name == null || name.isEmpty() || pass == null || pass.isEmpty())
		{
				request.setAttribute("errorMsg","All fields are mandatory.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
		}
		try {
			User user = UserDBView.selectUser(name);
			if(user != null)
			{
					if(user.getPassword().equals(pass))
					{
						HttpSession session = request.getSession();
						System.out.println("User logged in successfully !!!");
						session.setAttribute("user",name);
						response.sendRedirect("homepage.jsp");
					}
					else
					{
						request.setAttribute("errorMsg","Login failed !! Incorrect password.");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("errorMsg","Login failed !! Incorrect username and password.");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ServletException(ex);
			}	
		
	}

}
