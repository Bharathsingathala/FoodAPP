<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.foodapp.model.*, java.util.Map, com.foodapp.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Confirm Your Order</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f5f7fa;
	margin: 0;
	padding: 20px;
	color: #333;
}

.nav-links {
	padding: 10px 30px;
	display: flex;
	gap: 20px;
	margin: 0 auto 30px auto;
	width: fit-content;
	justify-content: center;
}

.nav-links a {
	color: #555;
	text-decoration: none;
	font-weight: 600;
	display: flex;
	align-items: center;
	gap: 6px;
	padding: 8px 20px;
	border-radius: 30px;
	transition: background-color 0.3s, color 0.3s, transform 0.2s;
	background-color: #ffffff;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.nav-links a:hover {
	background-color: orange;
	color: white;
	transform: scale(1.05);
	box-shadow: 0 4px 12px rgba(0, 123, 255, 0.4);
}

.nav-links a.active {
	background-color: #0056b3;
	color: white;
	transform: none;
	box-shadow: none;
}

.nav-icon {
	width: 18px;
	height: 18px;
}

h1 {
	text-align: center;
	color: #222;
	margin-bottom: 30px;
	font-weight: 700;
}

.order-container {
	max-width: 650px;
	background-color: white;
	padding: 25px 30px;
	border-radius: 12px;
	box-shadow: 0 5px 15px rgb(0 0 0/ 0.1);
	margin: 0 auto 40px auto;
}

.order-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #eee;
	padding: 12px 0;
	font-size: 16px;
}

.order-item:last-child {
	border-bottom: none;
}

.item-name {
	flex: 2;
	font-weight: 600;
	color: #333;
}

.item-qty, .item-price, .item-subtotal {
	flex: 1;
	text-align: right;
	color: #555;
}

.total {
	text-align: right;
	font-size: 20px;
	font-weight: 700;
	margin-top: 15px;
	color: #007bff;
}

label {
	display: block;
	margin-top: 18px;
	font-weight: 600;
	color: #444;
}

input[type="text"], select {
	width: 100%;
	padding: 10px 12px;
	margin-top: 6px;
	border: 1.8px solid #ccc;
	border-radius: 8px;
	font-size: 16px;
	transition: border-color 0.3s;
}

input[type="text"]:focus, select:focus {
	outline: none;
	border-color: #007bff;
	box-shadow: 0 0 6px rgba(0, 123, 255, 0.3);
}

.confirm-section {
	margin-top: 30px;
	text-align: center; /* Center contents horizontally */
}

.confirm-btn {
	margin-top: 25px;
	width: auto; /* Let the button width fit content */
	padding: 14px 50px; /* More horizontal padding */
	font-size: 18px;
	font-weight: 700;
	color: white;
	background-color: #007bff;
	border: none;
	border-radius: 10px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	display: inline-block;
	/* Make button inline to respect text-align center */
}

.confirm-btn:hover {
	background-color: green;
}

p {
	text-align: center;
	font-size: 18px;
	color: #666;
}
</style>

</head>
<body>

	<div class="nav-links">
		<!-- Navbar -->
		<div class="nav-links">
			<a href="first"
				class="<%=request.getRequestURI().endsWith("first") ? "active" : ""%>">
				<img src="https://img.icons8.com/ios-filled/24/000000/home.png"
				alt="Home Icon" class="nav-icon" /> Home
			</a> <a href="<%=request.getContextPath()%>/all-menus"
				class="<%=request.getRequestURI().contains("all-menus") ? "active" : ""%>">
				<img src="https://img.icons8.com/ios-filled/24/000000/menu.png"
				alt="Menu Icon" class="nav-icon" /> Menu
			</a> <a href="login.jsp"
				class="<%=request.getRequestURI().contains("login.jsp") ? "active" : ""%>">
				<img
				src="https://img.icons8.com/ios-filled/24/000000/login-rounded-right.png"
				alt="Login Icon" class="nav-icon" /> Login
			</a> <a href="Profile.jsp"
				class="<%=request.getRequestURI().contains("Profile.jsp") ? "active" : ""%>">
				<img src="https://img.icons8.com/ios-filled/24/000000/user.png"
				alt="Profile Icon" class="nav-icon" /> Profile
			</a> <a href="<%=request.getContextPath()%>/cart?action=view"
				class="<%=request.getRequestURI().contains("cart") ? "active" : ""%>">
				<img
				src="https://img.icons8.com/ios-filled/24/000000/shopping-cart.png"
				alt="Cart Icon" class="nav-icon" /> Cart
			</a>
		</div>
	</div>

	<h1>Confirm Your Order</h1>

	<%
	Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
	User user = (User) session.getAttribute("user");
	if (cart == null || cart.isEmpty()) {
	%>
	<p>Your cart is empty.</p>
	<%
	} else {
	double total = 0.0;
	%>

	<div class="order-container">
		<%
		for (CartItem item : cart.values()) {
			double subtotal = item.getMenu().getPrice() * item.getQuantity();
			total += subtotal;
		%>
		<div class="order-item">
			<span class="item-name"><%=item.getMenu().getItemName()%></span> <span
				class="item-qty">x <%=item.getQuantity()%></span> <span
				class="item-price"> @ ₹<%=String.format("%.2f", item.getMenu().getPrice())%></span>
			<span class="item-subtotal">= ₹<%=String.format("%.2f", subtotal)%></span>
		</div>
		<%
		}
		%>

		<div class="total">
			Total: ₹<%=String.format("%.2f", total)%>
		</div>

		<form action="<%=request.getContextPath()%>/order" method="post">
			<div class="confirm-section">
				<label for="paymentMethod">Payment Method:</label> <select
					name="paymentMethod" required>
					<option value="Cash on Delivery">Cash on Delivery</option>
					<option value="UPI">UPI</option>
					<option value="Card">Credit/Debit Card</option>
				</select> <label for="address">Delivery Address:</label> <input type="text"
					name="address" required
					value="<%=user != null ? user.getAddress() : ""%>" /> <a
					href="blast.html" class="confirm-btn">Confirm Order</a>
			</div>
		</form>
	</div>

	<%
	}
	%>

</body>
</html>
