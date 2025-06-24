<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.Menu, java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Food App - Menu</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<style>
* { margin: 0; padding: 0; box-sizing: border-box; }

body {
	font-family: 'Poppins', sans-serif;
	background: url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=1950&q=80') no-repeat center center fixed;
	background-size: cover;
	color: #f0f0f0;
	padding-bottom: 50px;
	position: relative;
}

body::before {
	content: '';
	position: fixed;
	top: 0; left: 0;
	width: 100%; height: 100%;
	background: rgba(0, 0, 0, 0.85);
	z-index: -1;
}

.nav-links {
	display: flex;
	justify-content: center;
	background: #111;
	padding: 14px 0;
	position: sticky;
	top: 0;
	z-index: 1000;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.6);
}

.nav-links a {
	margin: 0 15px;
	text-decoration: none;
	color: #ccc;
	font-weight: 600;
	display: flex;
	align-items: center;
	padding: 10px 18px;
	border-radius: 8px;
	transition: all 0.3s ease;
}

.nav-links a:hover, .nav-links a.active {
	background-color: #ff5722;
	color: #fff;
}

h1 {
	color: #ff5722;
	text-align: center;
	margin: 30px 0;
	font-size: 36px;
}

.filters {
	text-align: center;
	margin-bottom: 20px;
}

.filters input, .filters select {
	padding: 10px 14px;
	border-radius: 6px;
	border: 1px solid #666;
	margin: 0 8px;
	width: 200px;
	background: #222;
	color: #fff;
}

.menu-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
	gap: 25px;
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

.card {
	background: #1e1e1e;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(255, 255, 255, 0.05);
	transition: transform 0.3s;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	opacity: 0;
	transform: translateY(40px);
}

.card.animate {
	opacity: 1;
	transform: translateY(0);
}

.card:hover {
	transform: scale(1.03);
	box-shadow: 0 8px 24px rgba(255, 255, 255, 0.1);
}

.card img {
	width: 100%;
	height: 180px;
	object-fit: cover;
	border-top-left-radius: 12px;
	border-top-right-radius: 12px;
}

.card-content {
	padding: 16px;
	text-align: center;
}

.card h3 { font-size: 20px; margin: 10px 0 6px; color: #fff; }
.card p { font-size: 14px; color: #ccc; margin: 5px 0; }
.price { font-size: 18px; font-weight: bold; color: #f1f1f1; }
.availability { color: #4caf50; font-size: 13px; }
.unavailable { color: #f44336; font-size: 13px; }
.rating { color: #ff9800; font-size: 14px; }

.stars i {
	color: #555;
	cursor: pointer;
	transition: color 0.3s;
}
.stars i.selected {
	color: #ffc107;
}

.add-cart {
	margin-top: 10px;
	padding: 10px 20px;
	background: #ff5722;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-weight: bold;
	transition: background 0.3s;
}
.add-cart:hover {
	background: #e64a19;
}

.back-to-menu-btn {
	background: #ff5722;
	color: white;
	border: none;
	padding: 12px 24px;
	font-size: 16px;
	font-weight: bold;
	border-radius: 8px;
	cursor: pointer;
	margin-top: 30px;
}
.back-to-menu-btn:hover {
	background: #e64a19;
}
</style>
</head>
<body>

<!-- Navigation -->
<div class="nav-links">
	<a href="first" class="<%=request.getRequestURI().endsWith("first") ? "active" : ""%>">Home</a>
	<a href="<%=request.getContextPath()%>/all-menus" class="<%=request.getRequestURI().contains("all-menus") ? "active" : ""%>">Menu</a>
	<a href="login.jsp" class="<%=request.getRequestURI().contains("login.jsp") ? "active" : ""%>">Login</a>
	<a href="Profile.jsp" class="<%=request.getRequestURI().contains("Profile.jsp") ? "active" : ""%>">Profile</a>
	<a href="<%=request.getContextPath()%>/cart?action=view" class="<%=request.getRequestURI().contains("cart") ? "active" : ""%>">Cart</a>
</div>

<h1>Explore Our Delicious Menu</h1>

<!-- Filters -->
<div class="filters">
	<input type="text" id="searchInput" placeholder="Search for a dish..." onkeyup="filterMenu()" />
	<select id="categoryFilter" onchange="filterMenu()">
		<option value="all">All</option>
		<option value="veg">Veg</option>
		<option value="non-veg">Non-Veg</option>
	</select>
</div>

<%
List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
if (menuList != null && !menuList.isEmpty()) {
%>
<div class="menu-container" id="menuGrid">
	<%
	for (Menu menu : menuList) {
	%>
	<div class="card" data-name="<%=menu.getItemName().toLowerCase()%>" data-desc="<%=menu.getDescription().toLowerCase()%>">
		<img src="<%=menu.getImagePath()%>" alt="<%=menu.getItemName()%>">
		<div class="card-content">
			<h3><%=menu.getItemName()%></h3>
			<p><%=menu.getDescription()%></p>
			<p class="price">₹<%=String.format("%.2f", menu.getPrice())%></p>
			<p class="<%=menu.isAvailable() ? "availability" : "unavailable"%>">
				<%=menu.isAvailable() ? "Available" : "Out of Stock"%>
			</p>
			<p class="rating"><%=menu.getRating() > 0 ? menu.getRating() + " ★" : "No rating"%></p>

			<div class="stars">
				<i class="fas fa-star" onclick="setRating(this, 1)"></i>
				<i class="fas fa-star" onclick="setRating(this, 2)"></i>
				<i class="fas fa-star" onclick="setRating(this, 3)"></i>
				<i class="fas fa-star" onclick="setRating(this, 4)"></i>
				<i class="fas fa-star" onclick="setRating(this, 5)"></i>
			</div>

			<form action="<%=request.getContextPath()%>/cart" method="get">
				<input type="hidden" name="action" value="add" />
				<input type="hidden" name="menuId" value="<%=menu.getMenuId()%>" />
				<button type="submit" class="add-cart" <%=!menu.isAvailable() ? "disabled" : ""%>>Add to Cart</button>
			</form>
		</div>
	</div>
	<%
	}
	%>
</div>
<%
} else {
%>
<p style="text-align: center;">No menu items available.</p>
<%
}
%>

<div style="text-align: center;">
	<form action="<%=request.getContextPath()%>/first" method="get">
		<button type="submit" class="back-to-menu-btn">&#8592; Back to Restaurants</button>
	</form>
</div>

<!-- Scripts -->
<script>
function filterMenu() {
	const searchValue = document.getElementById("searchInput").value.toLowerCase();
	const category = document.getElementById("categoryFilter").value;
	const cards = document.querySelectorAll('.card');

	cards.forEach(card => {
		const name = card.getAttribute('data-name');
		const desc = card.getAttribute('data-desc');

		const matchesSearch = name.includes(searchValue);
		const matchesCategory =
			category === "all" ||
			(category === "veg" && desc.includes("veg")) ||
			(category === "non-veg" && desc.includes("non-veg"));

		card.style.display = (matchesSearch && matchesCategory) ? "flex" : "none";
	});
}

function setRating(star, rating) {
	const stars = star.parentElement.querySelectorAll("i");
	stars.forEach((s, idx) => {
		s.classList.toggle("selected", idx < rating);
	});
}

const cards = document.querySelectorAll('.card');
const observer = new IntersectionObserver((entries) => {
	entries.forEach(entry => {
		if (entry.isIntersecting) {
			entry.target.classList.add('animate');
		}
	});
}, { threshold: 0.1 });
cards.forEach(card => observer.observe(card));

let currentIndex = 0;
const step = 8;
function loadMoreItems() {
	const allCards = document.querySelectorAll('.card');
	for (let i = currentIndex; i < currentIndex + step && i < allCards.length; i++) {
		allCards[i].style.display = "flex";
	}
	currentIndex += step;
}
window.addEventListener('scroll', () => {
	if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 100) {
		loadMoreItems();
	}
});
document.addEventListener("DOMContentLoaded", loadMoreItems);
</script>

</body>
</html>
