<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.apache.tomcat.jakartaee.commons.lang3.StringUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (StringUtils.isNotBlank(errorMessage)) {
	%>
	<div>
		<p><%=errorMessage%></p>
	</div>
	<%
	}
	%>
	
	<%
	String successMessage = (String) request.getAttribute("successMessage");
	if (StringUtils.isNotBlank(successMessage)) {
	%>
	<div>
		<p><%=successMessage%></p>
	</div>
	<%
	}
	%>
</body>
</html>