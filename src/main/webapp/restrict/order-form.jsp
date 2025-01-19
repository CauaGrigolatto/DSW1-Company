<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="br.edu.ifsp.dsw.company.model.entity.Order"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Form</title>
</head>
<body>
	<jsp:include page="../includes/nav-menu.html" />
	
	<%
		var order = (Order) request.getAttribute("order");
	%>
	
	<h1>Order creation</h1>
	
	<form action="/company/controller" method="POST">
		<input type="hidden" name="targetCommand" value="OrderCommand">
		<input type="hidden" name="action" value="save">
		
		<input type="hidden" name="id" value="<%= order != null ? order.getId() : "" %>">
	
		<label for="description">Description:</label> <br>
		<textarea id="description" name="description" rows="5" cols="30" style="resize: none;"><%= order != null ? order.getDescription() : "" %></textarea>
		
		<br> <br>
		
		<label for="price">Price:</label>
		<input type="number" id="price" name="price" value="<%= order != null ? order.getPrice() : "" %>">
		
		<br> <br>
		
		<label for="client">Client:</label>
		<input type="text" id="client" name="client" value="<%= order != null ? order.getClient() : "" %>">
		
		<br> <br>
		
		<label for="address">Address:</label>
		<input type="text" id="address" name="address" value="<%= order != null ? order.getAddress() : "" %>">
		
		<br> <br>
	
		<button type="submit">Save</button>
	</form>
</body>
</html>