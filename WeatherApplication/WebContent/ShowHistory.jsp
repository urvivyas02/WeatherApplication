<%@ page import="java.util.*" %>
<%@ page import="entities.UserHistoryEntity" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show History</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
    <div class="row">
    <div class="form-card col-xs-12" style="max-width: 100%">
        <div style="float:center"><%=session.getAttribute("user") %>'s History</div>
        <%
        	if(session.getAttribute("errMsg") != null)
        	{
        		out.println(session.getAttribute("errMsg"));
        	}
			else{
		%>
	<div class="table responsive">
	<table class="table table-hover">
	<thead>
            <tr>
                <th>Time visited</th>
                <th>City</th>
              </tr>
     </thead>
	<c:forEach items="${searchedCities}" var="history">
		<tr >
                <td>${history.timestamp}</td>
               <td>${history.city}</td>
              </tr>
            </c:forEach>
	</table>
</div>
<% } %>
</div>
</div>
</body>
</html>