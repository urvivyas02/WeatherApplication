

# About Application :
My Weather App is a weather application which shows current and future weather conditions for a city using openweathermap.org APIs.

# Technologies used:
J2EE technology is used for this web application development.

Server side programming : Java (JSP,Servlet)
Client side programming : HTML,CSS,JavaScript
Database programming : MySQL

	⦁	Java is used because it is object oriented , robust and platform independent language. It provides better performance, memory 			management through automatic garbage collection.
	⦁	JSP is faster and allows embadding code into HTML pages for many programming language such as java, javascript. It can use 				custom tags and let us separate the markup language code from the programming language code.
	⦁	JavaScript is an incredibly expressive and powerful language and easy to learn.
	⦁	MySQL is a free-to-use, open-source database that facilitates effective management of databases .It is a stable, reliable and 			powerful solution with good performance.
	
# Functionality of the application :
1. User can check the current weather condition for any city along with next 5 day forecast. Weather attributes like Temperature, Humidity & Wind Speed are surfaced.
	⦁User can input "<city name>" or "<city name>,<country code>" in the search box.
	⦁If <country code> is not provided in search, and the city is present in different countries, search result will be random.
	⦁User can view the current temperature in Centigrade or Farenheit. Default unit will be centigrade.
	⦁Application will show current weather condition of Toronto,CA by default.
	
2. User can login/logout or can use the application as guest. User session will be persisted after login. 
	⦁After login User can view the history of his previous searches(max 5) or reset password.
	
3. User name is case insensitive. Multiple users cannot have same user name.

4. API search results are purely based on openweathermap.org API.

# Instructions to use:
To use the application, following softwares must be installed : 
	⦁Apache tomcat application server (requires JRE or JDK)
	⦁Mysql server

How to deploy:
1. Download "db.sql" from github and import it in mysql using following command. 
	⦁Open cmd and go to the 'bin' directory where mysql server is installed. 
	⦁Type command "mysql -u <username> -p userDB <  <path of the db.sql file>
	This will create database "userDB" in mysql and import all the data and tables.
2. Create a folder named "WeatherApplication" in web-apps folder of apache tomcat directory i.e. "$CATALINA_BASE/webapps.
	⦁Download folder "WeatherApplication/WebContent" from github and place its contents in newly created "WeatherApplication" folder.
	⦁Restart tomcat server and run application using following URL
		http://localhost:8080/WeatherApplication/



