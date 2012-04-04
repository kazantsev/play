<%-- 
    Document   : room
    Created on : Apr 4, 2012, 10:19:31 PM
    Author     : kazantsev
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="index.jsp"></jsp:include>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <%
            Properties pr = System.getProperties();
            String homeDir = pr.getProperty("user.dir");
            homeDir = homeDir.substring(0, homeDir.indexOf("/config"));
            homeDir += "/applications/play";
            File f = new File(homeDir + "/rooms");
            String[] fnames =  f.list();
            for (int i = 0; i < fnames.length; ++i){
                out.println("<tr><td>");
                out.println(fnames[i].replace(".txt", ""));
                out.println("</td><td><a href=\"\">join</a></td></tr>");
            }


%>
        </table>
    </body>
</html>
