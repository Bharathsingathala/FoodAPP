package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Restaurant;

public interface RestaurantDAO {

	void addRestaurant(Restaurant restaurant);

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int restaurantId);

	List<Restaurant> getAllRestaurants();

	Restaurant getRestaurantById(int restaurantId);

}
