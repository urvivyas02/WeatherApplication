<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
    <div class="container">
        <form class="form-card" method="post" action="${pageContext.request.contextPath}/ResetPasswordServlet">
            <center>
            <table  width="100%" cellpadding="3">
                <thead>
                    <tr>
                        <td colspan="2">
                            <div class="form-head row">Reset Password</div>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label>Old Password</label></td>
                        <td><input class="form-control" type="password" name="oldpass" value="" /></td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div></td></tr>
                    <tr>
                        <td><label>New Password</label></td>
                        <td><input class="form-control" type="password" name="newpass" value="" /></td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div></td></tr>
                    <tr>
                        <td><label>Re enter Password</label></td>
                        <td><input class="form-control" type="password" name="renewpass" value="" /></td>
                    </tr>
                    <tr><td colspan="2"><div class="form-group"></div><hr/></td></tr>
                    <tr>
                        <td class="text-center" colspan="2"><input class="btn btn-primary" type="submit" value="RESET" /></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
        <%
        	if(null != request.getAttribute("msg"))
        	{
        		out.println(request.getAttribute("msg"));
        	}
        %>
    </div>
    </body>
</html>