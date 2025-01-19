<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login Page</h1>

	<form action="/company/controller" method="POST">
		<input type="hidden" name="targetCommand" value="UserCommand">
		<input type="hidden" name="action" value="login">
	
		<label for="username">Username:</label>
		<input type="text" id="username" name="username">
		
		<br> <br>
		
		<label for="password">Password:</label>
		<input type="password" id="password" name="password">
		
		<br> <br>
	
		<button type="submit">Login</button>
	</form>
</body>
</html>