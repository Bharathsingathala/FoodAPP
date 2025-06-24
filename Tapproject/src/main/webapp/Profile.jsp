<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.*"%>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp?message=Please+login+first");
	return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>User Profile</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
<link rel="icon" href="images/foodapp-logo.png" type="image/png">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
body {
  font-family: 'Inter', sans-serif;
  margin: 0;
  padding: 0;
  background: linear-gradient(135deg, #fbc2eb 0%, #a6c1ee 100%);
  animation: gradientAnimation 15s ease infinite;
  background-size: 400% 400%;
  color: #333;
}

@keyframes gradientAnimation {
  0% {background-position: 0% 50%;}
  50% {background-position: 100% 50%;}
  100% {background-position: 0% 50%;}
}

.header-image {
  background-color: #fff;
  padding: 20px 40px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
}

.logo img {
  height: 60px;
}

.nav-links {
  display: flex;
  gap: 30px;
}

.nav-links a {
  text-decoration: none;
  color: #333;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 10px 16px;
  border-radius: 8px;
  transition: all 0.3s;
}

.nav-links a:hover, .nav-links a.active {
  background-color: #fc8019;
  color: white;
  transform: scale(1.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}

.nav-icon {
  width: 20px;
  height: 20px;
}

.profile-container {
  width: 90%;
  max-width: 700px;
  margin: 60px auto;
  background: rgba(255, 255, 255, 0.95);
  padding: 35px 40px;
  border-radius: 18px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
  backdrop-filter: blur(14px);
  animation: fadeInUp 0.9s ease;
}

@keyframes fadeInUp {
  from {opacity: 0; transform: translateY(20px);}
  to {opacity: 1; transform: translateY(0);}
}

h1 {
  color: #fc8019;
  margin-bottom: 30px;
  text-align: center;
  font-size: 32px;
  font-weight: 700;
}

.profile-pic {
  text-align: center;
  margin-bottom: 30px;
  position: relative;
}

.profile-pic img {
  width: 140px;
  height: 140px;
  border-radius: 50%;
  object-fit: cover;
  border: 5px solid #fc8019;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease;
}

.profile-pic img:hover {
  transform: scale(1.05);
}

.profile-pic form {
  margin-top: 10px;
}

.profile-info label {
  display: block;
  margin-top: 18px;
  font-weight: bold;
  font-size: 16px;
  color: #444;
}

.profile-info input, .profile-info textarea {
  width: 100%;
  padding: 12px;
  margin-top: 6px;
  border: 1px solid #ccc;
  border-radius: 10px;
  font-size: 15px;
  background-color: #f9f9f9;
  transition: border 0.3s;
}

.profile-info input:focus, .profile-info textarea:focus {
  border-color: #fc8019;
  outline: none;
  box-shadow: 0 0 8px rgba(252, 128, 25, 0.3);
}

button, input[type="submit"] {
  margin-top: 25px;
  background: linear-gradient(135deg, #fc8019, #ff944d);
  border: none;
  padding: 14px 28px;
  color: white;
  font-weight: bold;
  cursor: pointer;
  border-radius: 10px;
  font-size: 16px;
  box-shadow: 0 4px 15px rgba(252, 128, 25, 0.4);
  transition: all 0.3s ease;
}

button:hover, input[type="submit"]:hover {
  background: linear-gradient(135deg, #ff944d, #fc8019);
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(252, 128, 25, 0.6);
}

.logout-btn {
  text-align: center;
  margin-top: 35px;
}

@media (max-width: 768px) {
  .profile-container {
    padding: 25px 20px;
  }
  .profile-pic img {
    width: 100px;
    height: 100px;
  }
  h1 {
    font-size: 24px;
  }
  .profile-info label,
  .profile-info input,
  .profile-info textarea {
    font-size: 14px;
    padding: 10px;
  }
}
</style>
</head>
<body>

	<!-- Header -->
	<div class="header-image">
		<div class="navbar">
			<div class="logo">
				<a href="first"> <img src="images/foodapp-logo.png" alt="Food App Logo" /></a>
			</div>
			<div class="nav-links">
				<a href="first"> <i class="fas fa-home nav-icon"></i> Home</a>
				<a href="<%=request.getContextPath()%>/all-menus"> <i class="fas fa-utensils nav-icon"></i> Menu</a>
				<a href="login.jsp"> <i class="fas fa-sign-in-alt nav-icon"></i> Login</a>
				<a href="Profile.jsp"> <i class="fas fa-user nav-icon"></i> Profile</a>
				<a href="<%=request.getContextPath()%>/cart?action=view"> <i class="fas fa-shopping-cart nav-icon"></i> Cart</a>
			</div>
		</div>
	</div>

	<!-- Profile Content -->
	<div class="profile-container">
		<h1>
			Welcome, <%=user.getName() != null ? user.getName() : user.getUsername()%>!
		</h1>

		<div class="profile-pic">
			<img id="preview" src="https://static.vecteezy.com/system/resources/previews/006/828/445/non_2x/face-emojis-express-doubts-or-thoughts-free-vector.jpg" alt="Profile Picture" />
			<form action="uploadProfilePic" method="post" enctype="multipart/form-data">
				<input type="file" name="profilePic" accept="image/*" onchange="previewImage(event)" />
				<input type="submit" value="Upload Picture" />
			</form>
		</div>

		<form action="updateProfile" method="post" class="profile-info">
			<label>User ID:</label>
			<input type="text" name="userId" value="<%=user.getUserId()%>" readonly />

			<label>Full Name:</label>
			<input type="text" name="name" value="<%=user.getName() != null ? user.getName() : ""%>" />

			<label>Email:</label>
			<input type="email" name="email" value="<%=user.getEmail() != null ? user.getEmail() : ""%>" />

			<label>Phone:</label>
			<input type="text" name="phone" value="<%=user.getPhone() != null ? user.getPhone() : ""%>" />

			<label>Role:</label>
			<input type="text" name="role" value="<%=user.getRole() != null ? user.getRole() : ""%>" readonly />

			<label>Created On:</label>
			<input type="text" name="createDate" value="<%=user.getCreateDate() != null ? user.getCreateDate().toString() : "N/A"%>" readonly />

			<label>Last Login Date:</label>
			<input type="text" name="lastLoginDate" value="<%=user.getLastLoginDate() != null ? user.getLastLoginDate().toString() : "N/A"%>" readonly />

			<label>Address:</label>
			<textarea name="address" rows="2"><%=user.getAddress() != null ? user.getAddress() : ""%></textarea>

			<input type="submit" value="Update Profile" />
		</form>

		<div class="logout-btn">
			<form action="logout" method="post">
				<input type="submit" value="Logout" />
			</form>
		</div>
	</div>

	<script>
	function previewImage(event) {
		const reader = new FileReader();
		reader.onload = function() {
			document.getElementById("preview").src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	</script>
</body>
</html>
