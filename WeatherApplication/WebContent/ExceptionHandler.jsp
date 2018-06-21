
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exception/Error Details</title>
</head>
<body>
<%
	Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
	String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
	if (servletName == null) {
		servletName = "Unknown";
	}
	String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
	if (requestUri == null) {
		requestUri = "Unknown";
	}
		
%>
	  	<p>Error Details</p>	  	
	  	<p>Requested URI:<%=requestUri%></p>
	  	<p>Servlet Name:<%=servletName%></p>
	  	<p>Exception Name:<%= throwable.getClass().getName()%> </p>
		<p>Exception Message:<%= throwable.getMessage()%></p>
	  	

	<br><br>
	<a href="homepage.jsp">Home Page</a>
	
</body>
</html>