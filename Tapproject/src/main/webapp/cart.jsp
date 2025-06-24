<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.foodapp.model.*, java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Cart - FoodApp</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
body {
  margin: 0;
  font-family: 'Inter', sans-serif;
  background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.8)), url('https://images.unsplash.com/photo-1551218808-94e220e084d2?auto=format&fit=crop&w=1470&q=80') center/cover no-repeat fixed;
  color: #fff;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 40px;
}

.navbar {
  display: flex;
  justify-content: center;
  gap: 20px;
  background: rgba(0,0,0,0.7);
  width: 100%;
  padding: 12px 0;
  position: sticky;
  top: 0;
  z-index: 100;
}

.navbar a {
  color: #ff6b00;
  text-decoration: none;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 16px;
  padding: 6px 14px;
  border-radius: 8px;
  transition: background 0.3s;
}

.navbar a:hover {
  background-color: #fff;
  color: #000;
}

h1 {
  margin: 30px 0 20px;
  color: #ff6b00;
  font-size: 2rem;
}

.cart-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 24px;
  width: 90%;
  max-width: 1200px;
}

.cart-card {
  background: rgba(255,255,255,0.07);
  border-radius: 14px;
  width: 300px;
  padding: 18px;
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 25px rgba(0,0,0,0.5);
  animation: fadeIn 0.6s ease;
}

.cart-img {
  width: 100%;
  height: 180px;
  border-radius: 10px;
  object-fit: cover;
  margin-bottom: 10px;
}

.item-name {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 6px;
  color: #fff;
}

.item-desc {
  font-size: 0.9rem;
  color: #ccc;
  margin-bottom: 10px;
  min-height: 40px;
}

.price, .subtotal {
  font-size: 1rem;
  font-weight: bold;
  color: #ffb74d;
}

.quantity-control {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 10px 0;
}

.quantity-control button {
  background-color: #ff6b00;
  color: white;
  border: none;
  padding: 6px 12px;
  font-size: 1.2rem;
  border-radius: 6px;
  cursor: pointer;
}

.remove-btn {
  background-color: #e74c3c;
  padding: 8px 16px;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  width: 100%;
  margin-top: 10px;
  cursor: pointer;
}

.total-container, .order-actions {
  text-align: center;
  margin-top: 30px;
}

.total-container {
  font-size: 1.5rem;
  color: #fff;
  font-weight: bold;
  background: rgba(0,0,0,0.5);
  padding: 16px;
  border-radius: 10px;
  backdrop-filter: blur(10px);
}

.place-order-btn, .back-btn {
  margin-top: 15px;
  background-color: #28a745;
  color: white;
  padding: 12px 24px;
  font-size: 1rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.back-btn {
  background-color: #007bff;
}

.place-order-btn:hover {
  background-color: #218838;
}

.back-btn:hover {
  background-color: #0056b3;
}

.empty-msg {
  color: #fff;
  font-size: 1.2rem;
  margin-top: 50px;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>
</head>
<body>
  <div class="navbar">
    <a href="first"><i class="fas fa-home"></i> Home</a>
    <a href="<%=request.getContextPath()%>/all-menus"><i class="fas fa-utensils"></i> Menu</a>
    <a href="<%=request.getContextPath()%>/first"><i class="fas fa-store"></i> Restaurants</a>
    <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
    <a href="Profile.jsp"><i class="fas fa-user"></i> Profile</a>
    <a href="<%=request.getContextPath()%>/cart?action=view"><i class="fas fa-shopping-cart"></i> Cart</a>
  </div>

  <h1>Your Cart</h1>

  <% Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
     if (cart == null || cart.isEmpty()) { %>
     <p class="empty-msg">Your cart is empty.</p>
  <% } else {
       double total = 0.0;
  %>
  <div class="cart-container">
    <% for (CartItem item : cart.values()) {
         double subtotal = item.getMenu().getPrice() * item.getQuantity();
         total += subtotal;
    %>
    <div class="cart-card">
      <img class="cart-img" src="<%=request.getContextPath()%>/images/<%=item.getMenu().getImagePath()%>" alt="Item" />
      <div class="item-name"><%=item.getMenu().getItemName()%></div>
      <div class="item-desc"><%=item.getMenu().getDescription()%></div>
      <div class="price">Price: ₹<%=item.getMenu().getPrice()%></div>
      <div class="quantity-control">
        <form action="<%=request.getContextPath()%>/cart" method="post">
          <input type="hidden" name="action" value="decrease">
          <input type="hidden" name="menuId" value="<%=item.getMenu().getMenuId()%>">
          <button type="submit">-</button>
        </form>
        <div><%=item.getQuantity()%></div>
        <form action="<%=request.getContextPath()%>/cart" method="post">
          <input type="hidden" name="action" value="add">
          <input type="hidden" name="menuId" value="<%=item.getMenu().getMenuId()%>">
          <button type="submit">+</button>
        </form>
      </div>
      <div class="subtotal">Subtotal: ₹<%=subtotal%></div>
      <form action="<%=request.getContextPath()%>/cart" method="post">
        <input type="hidden" name="action" value="remove">
        <input type="hidden" name="menuId" value="<%=item.getMenu().getMenuId()%>">
        <button type="submit" class="remove-btn">Remove</button>
      </form>
    </div>
    <% } %>
  </div>

  <div class="total-container">
    Total: ₹<%=String.format("%.2f", total)%>
  </div>

  <div class="order-actions">
    <form action="<%=request.getContextPath()%>/order" method="post">
      <button type="submit" class="place-order-btn">Place Order</button>
    </form>
    <% Integer restaurantId = (Integer) session.getAttribute("restaurantId"); %>
    <a href="<%=request.getContextPath()%>/menu?restaurantId=<%=restaurantId != null ? restaurantId : ""%>" class="back-btn">← Back to Menu</a>
  </div>
  <% } %>
</body>
</html>
