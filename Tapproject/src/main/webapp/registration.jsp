<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Register - FoodApp</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
<style>
body {
	font-family: 'Inter', sans-serif;
	background: url('https://images.unsplash.com/photo-1600891964599-f61ba0e24092?auto=format&fit=crop&w=1470&q=80') center/cover no-repeat fixed;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	color: #fff;
}

.register-container {
	background: rgba(0, 0, 0, 0.7);
	padding: 2rem 3rem;
	border-radius: 15px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
	width: 100%;
	max-width: 450px;
	text-align: center;
	backdrop-filter: blur(8px);
}

h1 {
	color: #ff6b00;
	margin-bottom: 1.5rem;
	font-size: 28px;
}

input, select {
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

input:focus, select:focus {
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

.login-link {
	margin-top: 1rem;
	font-size: 0.9rem;
}

.login-link a {
	color: #ff6b00;
	text-decoration: none;
}

.login-link a:hover {
	text-decoration: underline;
}

.success-message {
	color: #22c55e;
	margin-bottom: 1rem;
	font-weight: 600;
}

.error-message {
	color: #f87171;
	margin-bottom: 1rem;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="register-container">
		<h1>Create an Account on <span style="color:#fff">Food</span><span style="color:#ff6b00">App</span></h1>

		<%
		String message = request.getParameter("message");
		if (message != null) {
		%>
		<div
			class="<%=(message != null && message.contains("success Please Login")) ? "success-message" : "error-message"%>">
			<%=message%>
		</div>
		<%
		}
		%>

		<form action="register" method="post">
			<input type="text" name="name" placeholder="Full Name" required />
			<input type="text" name="username" placeholder="Username" required />
			<input type="email" name="email" placeholder="Email" required />
			<input type="password" name="password" placeholder="Password" required />
			<input type="text" name="phone" placeholder="Phone Number" required />
			<input type="text" name="address" placeholder="Address" required />
			<select name="role" required>
				<option value="">Select Role</option>
				<option value="user">User</option>
				<option value="customer">Customer</option>
				<option value="restaurantadmin">Restaurant Admin</option>
				<option value="superadmin">Super Admin</option>
			</select>
			<button type="submit">Register</button>
		</form>

		<div class="login-link">
			Already have an account? <a href="login.jsp">Login here</a>
		</div>
	</div>
</body>
</html>
