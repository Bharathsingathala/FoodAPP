package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.User;

public interface UserDAO {

	boolean addUser(User user);

	User getUser(int UserId);

	void updateUser(User user);

	void deleteUser(int userId);

	List<User> getAllUsers();

	User login(String usernameOrEmail, String password);

	User getUserById(int userId);


}
