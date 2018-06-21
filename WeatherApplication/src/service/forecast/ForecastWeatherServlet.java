package service.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entities.WeatherReport;

/**
 * Servlet implementation class ForecastWeatherServlet
 */
@WebServlet("/ForecastWeatherServlet")
public class ForecastWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String URL_OpenWeatherMap =
			"http://api.openweathermap.org/data/2.5/forecast?APPID=4a61b3b3abbf8db29e1f1301abe9d42b&units=metric";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForecastWeatherServlet() {
        super();
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
		List<WeatherReport> reports= null;

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
					reports = getWeatherReportListFromJson(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
                
                request.setAttribute("forecastWeatherResponse",reports);
        }else
        {
        	request.setAttribute("errMsg","No result found.");
        }
        request.getRequestDispatcher("ForecastView.jsp").forward(request, response);
	}

	/**
	 * This method constructs list of objects of type WeatherReport from jsonObject.
	 * The input 'jsonObject' parameter represents the response of weather forecast API.
	 * It contains records of next 5 days with an interval of 3 hours.
	 * This method constructs 5 days weather forecast report by averaging the measurements of records of same day.
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public List<WeatherReport> getWeatherReportListFromJson(JSONObject jsonObject) throws JSONException, ParseException
	{
		List<WeatherReport> reports = new ArrayList<WeatherReport>();		
		JSONArray JSONArray_list = jsonObject.getJSONArray("list");
        int totalRecords = jsonObject.getInt("cnt");
        
        // format present in json
        SimpleDateFormat sdfCurrent = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
        
        //format needed to return
        SimpleDateFormat sdfNew = new SimpleDateFormat("EEE, MMM d");
        
        if(JSONArray_list.length() > 0) {            
        	String prevDate_txt="";
        	double temp = 0.0;
        	double windSpeed = 0.0;
        	double humidity = 0.0;
        	int count = 0;
        	DecimalFormat df2 = new DecimalFormat(".##");
        	for(int i= 0; i < totalRecords ; i++)
        	{        		
        		JSONObject JSONObjectElement = JSONArray_list.getJSONObject(i);
        		
        		//main
        		JSONObject JSONObject_main = JSONObjectElement.getJSONObject("main");
        		double currtemp = JSONObject_main.getDouble("temp");
        		double currhumidity = JSONObject_main.getDouble("humidity");
        		
        		//wind
        		JSONObject JSONObject_wind = JSONObjectElement.getJSONObject("wind");
        		double currwindSpeed = JSONObject_wind.getDouble("speed");
        		
        		String dt_txt = JSONObjectElement.getString("dt_txt");
        		Date currDate = sdfCurrent.parse(dt_txt);
				
        		String currDate_txt = sdfNew.format(currDate);
        		
        		// Indicates next day records are coming , so we find average of all the records of previous date.
        		if(!currDate_txt.equals(prevDate_txt) && i!=0)
        		{
        			double avgTemp = temp/count;
        			double avgWindSpeed = windSpeed/count;
        			double avgHumidity = humidity/count;
        			WeatherReport wr = new WeatherReport(df2.format(avgTemp)+"",df2.format(avgWindSpeed)+"",df2.format(avgHumidity)+"", prevDate_txt);
        			reports.add(wr);
        	
        			//resent values for next date
        			count = 0;
        			temp = currtemp;
        			windSpeed =currwindSpeed;
        			humidity = currhumidity;
        		}
        		else
        		{
        			temp += currtemp;
        			windSpeed += currwindSpeed;
        			humidity += currhumidity;
        		}
        		prevDate_txt = currDate_txt;
        		count ++;        		
        	}
        	if(count != 0)
        	{
        		double avgTemp = temp/count;
    			double avgWindSpeed = windSpeed/count;
    			double avgHumidity = humidity/count;
    			WeatherReport wr = new WeatherReport(df2.format(avgTemp)+"",df2.format(avgWindSpeed)+"",df2.format(avgHumidity)+"", prevDate_txt);
    			reports.add(wr);
        	}
        }
        return reports;
		
	}


}
