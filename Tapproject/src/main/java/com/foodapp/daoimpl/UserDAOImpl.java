package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;
import com.foodapp.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER_QUERY = "INSERT INTO User (name, username, password, email, phone, address, role, createDate, lastLoginDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHECK_USER_EXISTS_QUERY = "SELECT * FROM User WHERE username = ? OR email = ?";
	private static final String GET_USER_QUERY = "SELECT * FROM User WHERE userId = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE User SET name = ?, password = ?, phone = ?, address = ?, role = ? WHERE userId = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM User WHERE userId = ?";
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM User";
	private static final String LOGIN_QUERY = "SELECT * FROM User WHERE (username = ? OR email = ?) AND password = ?";

	@Override
	public boolean addUser(User user) {
		try (Connection connection = DBConnection.getConnection()) {

			try (PreparedStatement checkStmt = connection.prepareStatement(CHECK_USER_EXISTS_QUERY)) {
				checkStmt.setString(1, user.getUsername());
				checkStmt.setString(2, user.getEmail());
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next()) {
					return false;
				}
			}

			try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)) {
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getUsername());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getEmail());
				preparedStatement.setString(5, user.getPhone());
				preparedStatement.setString(6, user.getAddress());
				preparedStatement.setString(7, user.getRole());
				preparedStatement.setTimestamp(8, user.getCreateDate());
				preparedStatement.setTimestamp(9, user.getLastLoginDate());

				return preparedStatement.executeUpdate() > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(int userId) {
		User user = null;
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_USER_QUERY)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_USER_QUERY)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getRole());
			ps.setInt(6, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_USER_QUERY)) {
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(GET_ALL_USERS_QUERY)) {
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User login(String usernameOrEmail, String password) {
		User user = null;
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(LOGIN_QUERY)) {
			ps.setString(1, usernameOrEmail);
			ps.setString(2, usernameOrEmail);
			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		String sql = "SELECT * FROM User WHERE userId = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setAddress(rs.getString("address"));
		user.setRole(rs.getString("role"));
		user.setCreateDate(rs.getTimestamp("createDate"));
		user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));
		return user;
	}
}