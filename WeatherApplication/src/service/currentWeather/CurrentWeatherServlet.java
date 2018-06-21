package service.currentWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.connection.UserHistoryDBView;

import entities.CurrentWeatherReport;

/**
 * Provides current weather details for the searched city
 * Servlet implementation class CurrentWeatherServlet
 */

public class CurrentWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String URL_OpenWeatherMap = 
            "http://api.openweathermap.org/data/2.5/weather?APPID=4a61b3b3abbf8db29e1f1301abe9d42b&units=metric";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentWeatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String city=request.getParameter("city");
		StringBuffer urlString = new StringBuffer(URL_OpenWeatherMap);
		urlString.append("&q=").append(city);
		URL url = new URL(urlString.toString());
		String result="";
		CurrentWeatherReport report= null;
		
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) 
        {
        	InputStreamReader inputStreamReader =
                    new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                 
               bufferedReader.close();                
               JSONObject jsonObject;
				try 
				{
					jsonObject = new JSONObject(result);
					report = getCurrentWeatherReportFromJson(jsonObject);
					request.getSession().setAttribute("currentWeatherResponse",report);
	                Object user = request.getSession().getAttribute("user");
	                //persist search history in DB if user is logged in
	                if(user != null)
	                {
	                	persistUserSearchInDB((String)user,city);
	                }
				} catch (Exception e) 
				{
					e.printStackTrace();
					throw new ServletException(e);
				}                
           
        }
        else
        {
        	request.setAttribute("errMsg","No result found.");
        	request.getSession().removeAttribute("currentWeatherResponse");
        }
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
	}
	
	/**
	 * This method constructs object of type CurrentWeatherReport from jsonObject.
	 * This parameter 'jsonObject' represents response of current weather API.
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private CurrentWeatherReport getCurrentWeatherReportFromJson(JSONObject jsonObject) throws JSONException
	{
		//weather
		JSONArray JSONArray_weather = jsonObject.getJSONArray("weather");
		String description="";
		String icon="";
		if(JSONArray_weather.length() > 0)
		{
            JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(0);
            description = JSONObject_weather.getString("description");
            icon = JSONObject_weather.getString("icon");
		}
         
        //"main"
        JSONObject JSONObject_main = jsonObject.getJSONObject("main");
        double temp = JSONObject_main.getDouble("temp");
        double humidity = JSONObject_main.getDouble("humidity");
       
         
        //"wind"
        JSONObject JSONObject_wind = jsonObject.getJSONObject("wind");
        double windSpeed = JSONObject_wind.getDouble("speed");
       
        //"dt"
        String formattedDate = formatDate(jsonObject.getLong("dt") * 1000);
                 
        //"name"
        String cityName = jsonObject.getString("name");
        
        String countryCode = jsonObject.getJSONObject("sys").getString("country");
        
        return new CurrentWeatherReport(cityName+","+countryCode,formattedDate,temp+"",description,icon,humidity+"",windSpeed+"");
         

	}
	
	/**
	 * Converts date to format <Day of week>, <month name><day of month>.
	 * For example, Tue, Jun 20
	 * @param dt
	 * @return
	 */
	private String formatDate(long dt)
	{
		Date date = new Date(dt);
		SimpleDateFormat df2 = new SimpleDateFormat("EEE, MMM d");
        String dateText = df2.format(date);
		return dateText;
	}	
	
	/**
	 * History in the user_history table is maintained in the below format:
	 * city1,country1#timestamp;city2,country2#timestamp2;city3,country3#timestamp3
	 * With the new search , new city name and timestamp is appended to history and persisted in the DB.
	 * Only last 5 records are maintained. So if city count exceeds 5, we delete oldest one from history.
	 * @param uname
	 * @param city
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void persistUserSearchInDB(String uname,String city) throws SQLException, ClassNotFoundException 
	{		
		String history = UserHistoryDBView.selectUserHistory(uname);
		long timestamp = System.currentTimeMillis();
				
		if(!history.isEmpty())
		{
			String[] cityArray = history.split(util.Constants.CITY_DELIMITER);
			if(cityArray.length == 5)
			{
				history = history.substring(history.indexOf(util.Constants.CITY_DELIMITER) + 1);
			}
			history += util.Constants.CITY_DELIMITER + city +util.Constants.TIMESTAMP_DELIMITER + timestamp ;
			UserHistoryDBView.updateUserHistory(uname, history);
											
		} else 
		{	
			UserHistoryDBView.insertUserHistory(uname, city+util.Constants.TIMESTAMP_DELIMITER + timestamp);
		}

}
	

}
