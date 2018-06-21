<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <link rel="stylesheet" type="text/css" href="style.css">
        
    </head>
    <body>
        <div class="container">
        <form class="form-card" method="get" action="${pageContext.request.contextPath}/LoginServlet">
            <center>
            <table border="0" width="100%" cellpadding="10">
                <thead>
                    <tr>
                        <th colspan="2"><div class="form-head row">Login Here</div></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label>User Name</label></td>
                        <td>
                           
                                <input class="form-control" type="text" name="uname" value="" />
                           
                        </td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div></td></tr>
                    <tr>
                        <td><label>Password</label></td>
                        <td>
                            
                                <input class="form-control" type="password" name="pass" value="" />
                            
                        </td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div><hr/></td></tr>
                    <tr>
                        <td class="text-center" colspan="2"><input class="btn btn-primary" type="submit" value="Login" /></td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div></td></tr>
                    <tr>
                        <td class="text-right" colspan="2">Yet Not Registered!! <a href="registration.jsp">Register Here</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
        <%
        	if(null != request.getAttribute("errorMsg"))
        	{
        		out.println(request.getAttribute("errorMsg"));
        	}
        %>
        <div class="container">
    </body>
</html>