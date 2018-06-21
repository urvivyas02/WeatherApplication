
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My weather app</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
</head>
<body>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("centigrade").removeAttribute("href");
	var CentigradeClicked = true;
	var fahrenheitClicked =  false;
	document.getElementById("Farehnite").onclick = function(){
		if(!fahrenheitClicked)
		{
			var tempval = document.getElementById("tempVal");
			tempFloat = parseFloat(tempval.innerHTML);
			tempval.innerHTML = ((tempFloat *1.8)+32).toFixed(2);
			document.getElementById("Farehnite").removeAttribute("href");
			document.getElementById("centigrade").href="#";
			CentigradeClicked = false;
		}
		fahrenheitClicked = true;
		return false;
	}
	
	document.getElementById("centigrade").onclick = function(){
		if(!CentigradeClicked)
		{
			var tempval = document.getElementById("tempVal");
			tempFloat = parseFloat(tempval.innerHTML);
			tempval.innerHTML = ((tempFloat - 32)/1.8).toFixed(2);
			document.getElementById("centigrade").removeAttribute("href");
			document.getElementById("Farehnite").href="#";
			fahrenheitClicked = false;
		}
		CentigradeClicked = true;
		return false;
	}
}
</script>

<div class="container">

<div class="topnav">
<h1 class="app-title">My Weather app</h1>
<div class="col-md-6">
    <form method="GET" name="frm" action="${pageContext.request.contextPath}/CurrentWeatherServlet">
        <input class="form-control" placeholder="city,country.." type="text" name="city" id="city" >
        <input class="btn btn-primary"  type="submit" name="submit" value="Search">       
    </form>
    <% if(session.getAttribute("user") != null){ %>
    <div class="hello-name">Hello <%=session.getAttribute("user") %> !</div>
    <% } %>
</div>

<div class="col-md-6">
    <% if(session.getAttribute("user") == null){ %>
		<a href="registration.jsp">Sign Up</a>
  		<a class="active" href="login.jsp">Login</a>
  <% } else { %>
  
  <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
  <a href="ResetPassword.jsp">Reset Password</a>
  <a href="${pageContext.request.contextPath}/UserHistory">Show History</a>
  <% } %>
</div>
</div>
<%
	if(request.getAttribute("errMsg") != null)
	{
		out.println(request.getAttribute("errMsg"));
	}else{
%>
<c:set var="icon" value="${currentWeatherResponse.iconID}"/>
<% 
	String imgaeSrc =  (String)pageContext.getAttribute("icon");
%>

<div class="content-div">
<div class="row">
<div class="col-md-12">
  <div class="city-name text-center">${currentWeatherResponse.cityName}</div>
</div>
  <div class="col-sm-6">
    <p>${currentWeatherResponse.date}</p>
    <p>${currentWeatherResponse.weatherConditionDesc}</p>
  </div>

  <div class="col-sm-6 text-right">
    <p>Humidity = ${currentWeatherResponse.humidity} % </p>
    <p>Wind  = ${currentWeatherResponse.windSpeed} miles/hr</p>
  </div>

  <div class="col-md-12 text-center">
     <img src="http://openweathermap.org/img/w/<%=imgaeSrc%>.png" height=100 width=100 align=top/>
      <span id="tempVal" class="temp">${currentWeatherResponse.temperature}</span>
      <span class="temp"><a href="#" id="centigrade">&deg;C</a> | <a href="#" id="Farehnite">&deg;F</a></span>
    <div>
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/ForecastWeatherServlet?city=${currentWeatherResponse.cityName}">Next 5 Days Forecast</a>
    </div>
  </div>

</div>
</div>
  	

  <% } %>
</div>
</body>
</html>