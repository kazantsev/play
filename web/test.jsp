<%-- 
    Document   : test
    Created on : Apr 3, 2012, 7:17:01 PM
    Author     : Kazancev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<%
		out.println(session.getAttribute("UserLogin"));
		


%>
	</body>
</html>
