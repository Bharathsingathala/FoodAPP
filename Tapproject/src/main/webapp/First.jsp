<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.*, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>FoodApp - Dark Mode</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background: #0f1117 url('https://images.unsplash.com/photo-1600891964599-f61ba0e24092?auto=format&fit=crop&w=1470&q=80') center/cover no-repeat fixed;
      color: #f1f5f9;
      margin: 0;
    }
    .navbar {
      background: rgba(24, 26, 32, 0.85);
      padding: 15px 40px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2px 8px rgba(0,0,0,0.7);
      backdrop-filter: blur(8px);
    }
    .nav-links a {
      margin-left: 20px;
      text-decoration: none;
      color: #d1d5db;
      transition: color 0.3s ease;
    }
    .nav-links a:hover {
      color: #ff6b00;
    }
    .banner {
      background: rgba(0, 0, 0, 0.5);
      backdrop-filter: blur(6px);
      height: 320px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
      color: #ffffff;
      padding: 20px;
    }
    .banner h1 {
      font-size: 42px;
      font-weight: 600;
      margin-bottom: 10px;
    }
    .banner p {
      font-size: 16px;
      color: #e2e8f0;
    }
    .search-bar {
      text-align: center;
      margin: 30px 0;
    }
    .search-bar input {
      padding: 12px 20px;
      width: 90%;
      max-width: 520px;
      border-radius: 12px;
      border: none;
      background: #1f2937;
      color: #f8fafc;
      font-size: 16px;
    }
    .container {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: 24px;
      padding: 30px;
    }
    .card {
      background: rgba(30, 31, 36, 0.9);
      border-radius: 12px;
      overflow: hidden;
      box-shadow: 0 4px 12px rgba(0,0,0,0.6);
      transition: transform 0.2s;
      color: #f1f5f9;
      animation: fadeIn 0.5s ease-in-out;
      backdrop-filter: blur(4px);
    }
    .card:hover {
      transform: scale(1.02);
    }
    .card img {
      width: 100%;
      height: 200px;
      object-fit: cover;
    }
    .card-body {
      padding: 16px;
    }
    .card-body h3 {
      margin: 0;
      font-size: 18px;
      color: #ffffff;
    }
    .desc {
      font-size: 14px;
      color: #cbd5e1;
      margin: 6px 0;
    }
    .rating {
      background: #16a34a;
      color: #ffffff;
      padding: 4px 10px;
      border-radius: 6px;
      display: inline-block;
      margin-top: 10px;
      font-size: 13px;
    }
    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(10px); }
      to { opacity: 1; transform: translateY(0); }
    }
  </style>
</head>
<body>
  <div class="navbar">
    <div><strong style="color:#ff6b00; font-size: 20px">FoodApp</strong></div>
    <div class="nav-links">
      <a href="first">Home</a>
      <a href="<%=request.getContextPath()%>/all-menus">Menu</a>
      <a href="login.jsp">Login</a>
      <a href="Profile.jsp">Profile</a>
      <a href="<%=request.getContextPath()%>/cart?action=view">Cart</a>
    </div>
  </div>

  <div class="banner">
    <h1>Discover Great Food Near You</h1>
    <p>Fast delivery from top-rated restaurants</p>
  </div>

  <div class="search-bar">
    <input type="text" id="searchInput" placeholder="Search by name or cuisine...">
  </div>

  <div class="container" id="restaurantContainer">
    <% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("allRestaurants");
       if (restaurants != null && !restaurants.isEmpty()) {
         for (Restaurant r : restaurants) {
    %>
    <a class="card" data-name="<%=r.getName().toLowerCase()%>" data-cuisine="<%=r.getCuisine().toLowerCase()%>" href="menu?restaurantId=<%=r.getRestaurantId()%>">
      <img src="<%=r.getImagepath()%>" alt="<%=r.getName()%>">
      <div class="card-body">
        <h3><%=r.getName()%></h3>
        <p class="desc"><strong>Cuisine:</strong> <%=r.getCuisine()%></p>
        <p class="desc"><strong>Address:</strong> <%=r.getAddress()%></p>
        <p class="desc"><strong>Delivery Time:</strong> <%=r.getDeliveryTime()%> mins</p>
        <div class="rating"><%=r.getRating()%> ★</div>
      </div>
    </a>
    <% }} else { %>
      <p style="text-align: center; color: #ccc; font-size: 18px;">No restaurants found.</p>
    <% } %>
  </div>

  <script>
    const searchInput = document.getElementById("searchInput");
    const cards = document.querySelectorAll(".card");

    searchInput.addEventListener("input", () => {
      const query = searchInput.value.toLowerCase();
      cards.forEach(card => {
        const name = card.getAttribute("data-name");
        const cuisine = card.getAttribute("data-cuisine");
        const isVisible = name.includes(query) || cuisine.includes(query);
        card.style.display = isVisible ? "block" : "none";
      });
    });

    document.querySelectorAll('a.card').forEach(link => {
      link.addEventListener('click', (e) => {
        document.body.style.transition = 'opacity 0.5s';
        document.body.style.opacity = '0.5';
      });
    });
  </script>
</body>
</html>