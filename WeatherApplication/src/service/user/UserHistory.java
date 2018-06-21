package service.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.UserHistoryDBView;
import entities.UserHistoryEntity;

/**
 * This servlet persists the user's search history in the DB.
 * Servlet implementation class UserHistory
 */
@WebServlet("/UserHistory")
public class UserHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uname = (String)request.getSession().getAttribute("user");
		List<UserHistoryEntity> historyList = new ArrayList<UserHistoryEntity>();
		try
		{
			String history = UserHistoryDBView.selectUserHistory(uname);
			if(history.isEmpty())
			{
				request.setAttribute("errMsg","No History Found");
			}
			else
			{
				String[] cityArray = history.split(util.Constants.CITY_DELIMITER);
				
				if(cityArray != null && cityArray.length > 0)
				{						
					for (int i = 0; i < cityArray.length; i++)
					{
						String[] cityTSArray = cityArray[i].split(util.Constants.TIMESTAMP_DELIMITER);
						long timestamp = Long.valueOf(cityTSArray[1]);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						String dateTime = sdf.format(timestamp);
						UserHistoryEntity userHistory = new UserHistoryEntity(cityTSArray[0], dateTime);
						historyList.add(userHistory);
						
					}
				}
				request.setAttribute("searchedCities",historyList);
			}
			request.getRequestDispatcher("ShowHistory.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			
		}

	}

}
