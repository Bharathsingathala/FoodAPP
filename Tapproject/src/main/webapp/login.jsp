<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login - FoodApp</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
<style>
body {
	font-family: 'Inter', sans-serif;
	background: url('https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=1470&q=80') center/cover no-repeat fixed;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	color: #fff;
}

.login-container {
	background: rgba(0, 0, 0, 0.7);
	padding: 2rem 3rem;
	border-radius: 15px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
	width: 100%;
	max-width: 400px;
	text-align: center;
	backdrop-filter: blur(8px);
}

h1 {
	color: #ff6b00;
	margin-bottom: 1.5rem;
	font-size: 28px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 0.75rem 1rem;
	margin-bottom: 1rem;
	border: none;
	border-radius: 25px;
	font-size: 1rem;
	outline: none;
	background: #1e1e1e;
	color: #f8fafc;
	transition: box-shadow 0.3s ease;
}

input[type="text"]:focus, input[type="password"]:focus {
	box-shadow: 0 0 0 2px #ff6b00;
}

button {
	background-color: #ff6b00;
	border: none;
	color: white;
	padding: 0.75rem 1.5rem;
	border-radius: 25px;
	font-weight: 700;
	cursor: pointer;
	width: 100%;
	font-size: 1rem;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #e05800;
}

.register-link {
	margin-top: 1rem;
	font-size: 0.9rem;
}

.register-link a {
	color: #ff6b00;
	text-decoration: none;
}

.register-link a:hover {
	text-decoration: underline;
}

.error-message {
	color: #f87171;
	margin-bottom: 1rem;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="login-container">
		<h1>Login to <span style="color: #fff;">Food</span><span style="color: #ff6b00;">App</span></h1>

		<!-- Show error message if present -->
		<c:if test="${not empty error}">
			<div class="error-message">${error}</div>
		</c:if>

		<!-- Login Form -->
		<form action="LoginServlet" method="post">
			<input type="text" name="usernameOrEmail"
				placeholder="Username or Email" required />
			<input type="password" name="password" placeholder="Password" required />
			<button type="submit">Login</button>
		</form>

		<div class="register-link">
			Don't have an account? <a href="registration.jsp">Register here</a>
		</div>
	</div>
</body>
</html>
