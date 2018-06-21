<%@ page import="java.util.*" %>
<%@ page import="entities.WeatherReport" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forecast view</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
    <div class="row">
    <div class="form-card col-xs-12" style="max-width: 100%">
        <div class="form-head row">5 Days Forecast</div>
	<%
	if(session.getAttribute("errMsg") != null)
	{
		out.println(session.getAttribute("errMsg"));
	}
%>
	<center>
	<%		
		List<WeatherReport> reports = (List<WeatherReport>)request.getAttribute("forecastWeatherResponse");		
	%>
    <div class="table responsive">
	<table class="table table-hover">
        <thead>
            <tr>
                <th><%=reports.get(0).getDate()%></th>
                <th><%=reports.get(1).getDate()%></th>
                <th><%=reports.get(2).getDate()%></th>
                <th><%=reports.get(3).getDate()%></th>
                <th><%=reports.get(4).getDate()%></th>
            </tr>
        </thead>
        <tbody>
            
             <tr >
                <td><%=reports.get(0).getTemperature()%>&deg;C</td>
               <td><%=reports.get(1).getTemperature()%>&deg;C</td>
                <td><%=reports.get(2).getTemperature()%>&deg;C</td>
               <td><%=reports.get(3).getTemperature()%>&deg;C</td>
                <td><%=reports.get(4).getTemperature()%>&deg;C</td>
            </tr>
            
             <tr >
                <td>Humidity:<%=reports.get(0).getHumidity()%>%</td>
                <td>Humidity:<%=reports.get(1).getHumidity()%>%</td>
                <td>Humidity:<%=reports.get(2).getHumidity()%>%</td>
                <td>Humidity:<%=reports.get(3).getHumidity()%>%</td>
                <td>Humidity:<%=reports.get(4).getHumidity()%>%</td>
            </tr>
            
             <tr >
                <td>Wind Speed:<%=reports.get(0).getWindSpeed()%> miles/hr</td>
                <td>Wind Speed:<%=reports.get(1).getWindSpeed()%> miles/hr</td>
                <td>Wind Speed:<%=reports.get(2).getWindSpeed()%> miles/hr</td>
                <td>Wind Speed:<%=reports.get(3).getWindSpeed()%> miles/hr</td>
                <td>Wind Speed:<%=reports.get(4).getWindSpeed()%> miles/hr</td>
            </tr>
        </tbody>
	</table>
	</center>
</div>
</div>
</div>
</body>
</html>