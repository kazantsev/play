<%-- 
    Document   : login
    Created on : Apr 3, 2012, 6:08:27 PM
    Author     : Kazancev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
				<script src="my.js"></script>
				<script src="reg.js"></script>
				<script src="ajax.js"></script>
				<script src="login.js"></script>
    </head>
    <body>
        <form style="align: center">
            <table>
                
                <tr>
                    <td>
                        <div id="LoginLabel"></div> <input type="text" name="UserLogin" id="UserLogin" value="Your Login"  onFocus="handleFocus('UserLogin', 'LoginLabel', 'Your Login', false);"
                               onblur="handleBlur('UserLogin', 'LoginLabel', 'Your Login', false);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                       <div id="PasswLabel"></div> <input type="text" name="UserPassw" id="UserPassw" value="Your Password" onFocus="handleFocus('UserPassw', 'PasswLabel', 'Your Password', true);"
                                                          onblur="handleBlur('UserPassw', 'PasswLabel', 'Your Password', true);"/>
                    </td>
                </tr>
               
                <tr>
                    <td>
											<input type="button" value="log in" onclick="sendLoginReq()"/>
                    </td>
                </tr>
            </table>   
        </form>
    </body>
</html>

