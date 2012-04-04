
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mafia</title>
    </head>
    <body>
        <% if (session.getAttribute("UserLogin") == null ){
            out.println ("<table><tr><td><a href=\"registration.jsp\" >register </a></td>");
            out.println("<td><a href=\"login.jsp\" >log in </a></td> </tr></table>");       
           } else {
            out.println("<table><tr><td>Hi, " + session.getAttribute("UserName") + "!</td> <td><a href=\"\" >log out</a> </td> </tr> </table>");
           
           }    
    
    %>
    </body>
</html>
