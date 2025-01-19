<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifsp.dsw.company.model.entity.Order"%>
<%@page import="org.apache.commons.collections4.CollectionUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../includes/nav-menu.html" />

	<h1>My orders</h1>
	
	<form action="">
		<label for="client">Search order by client</label>
		<input type="text" id="client" name="client">
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
				<th>Action</th>
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
			                <td>
			                	<a href="/company/controller?targetCommand=OrderCommand&action=delete?orderId=<%= order.getId() %>">Edit</a>
			                	<a>Delete</a>
			                </td>
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