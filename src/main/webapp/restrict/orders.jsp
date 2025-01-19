<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifsp.dsw.company.model.entity.Order"%>
<%@page import="org.apache.commons.collections4.CollectionUtils"%>
<%@page import="org.apache.tomcat.jakartaee.commons.lang3.StringUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
</head>
<body>
	<jsp:include page="../includes/nav-menu.html" />

	<%
		String client = (String) request.getParameter("client");
		if (StringUtils.isEmpty(client)) client = "";
	%>

	<h1>Orders</h1>
	
	<form action="/company/controller" method="GET">
		<input type="hidden" name="targetCommand" value="OrderCommand">
		<input type="hidden" name="action" value="filter">
		<label for="client">Search order by client</label>
		<input type="text" id="client" name="client" value="<%= client %>">
		<button>Search</button>
	</form>
	
	<%
		var orders = (List<Order>) request.getAttribute("orders");
	%>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Price</th>
				<th>Client</th>
				<th>Address</th>
				<th>Created by</th>
			</tr>
		</thead>
		<tbody>
			<%
			    if (CollectionUtils.isNotEmpty(orders)) {
			        for (var order : orders) {
			%>
			            <tr>
			                <td><%= order.getId() %></td>
			                <td><%= order.getDescription() %></td>
			                <td><%= order.getPrice() %></td>
			                <td><%= order.getClient() %></td>
			                <td><%= order.getAddress() %></td>
			                <td><%= order.getUser().getUsername() %></td>
			            </tr>
			<%
			        }
			    } else {
			%>
			        <tr>
			            <td colspan="6">No orders available</td>
			        </tr>
			<%
			    }
			%>
		</tbody>
	</table>
</body>
</html>