<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../includes/nav-menu.html" />
	
	<h1>Order creation</h1>
	
	<form action="/company/controller" method="POST">
		<input type="hidden" name="targetCommand" value="OrderCommand">
		<input type="hidden" name="action" value="create">
	
		<label for="description">Description:</label> <br>
		<textarea id="description" name="description" rows="5" cols="30" style="resize: none;"></textarea>
		
		<br> <br>
		
		<label for="price">Price:</label>
		<input type="number" id="price" name="price">
		
		<br> <br>
		
		<label for="client">Client:</label>
		<input type="text" id="client" name="client">
		
		<br> <br>
		
		<label for="address">Address:</label>
		<input type="text" id="address" name="address">
		
		<br> <br>
	
		<button type="submit">Create</button>
	</form>
</body>
</html>