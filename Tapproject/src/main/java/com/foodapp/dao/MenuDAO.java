package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);

	Menu getMenu(int menuId);

	void updateMenu(Menu menu);

	void deleteMenu(int menuId);

	List<Menu> getAllMenuItems();

	List<Menu> getAllMenuByRestaurant(int restaurantId);

	Menu getMenuById(int menuId);
}