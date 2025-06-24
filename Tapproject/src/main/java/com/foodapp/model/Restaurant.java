package com.foodapp.model;

public class Restaurant {

	private int restaurantId;
	private String name;
	private String address;
	private String phonenumber;
	private String cuisine;
	private int deliveryTime;
	private double rating;
	private int adminUserId;
	private boolean isActive;
	private String imagepath;

	public Restaurant() {
	}

	public Restaurant(String name, String address, String phonenumber, String cuisine, int deliveryTime,
			int adminUserId, double rating, boolean isActive, String imagepath) {
		super();
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.cuisine = cuisine;
		this.deliveryTime = deliveryTime;
		this.rating = rating;
		this.adminUserId = adminUserId;
		this.isActive = isActive;
		this.imagepath = imagepath;
	}

	public Restaurant(int restaurantId, String name, String address, String phonenumber, String cuisine,
			int deliveryTime, int adminUserId, double rating, boolean isActive, String imagepath) {
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.cuisine = cuisine;
		this.deliveryTime = deliveryTime;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagepath = imagepath;
	}

	// Getters and setters
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", phonenumber="
				+ phonenumber + ", cuisine=" + cuisine + ", deliveryTime=" + deliveryTime + ", adminUserId="
				+ adminUserId + ", rating=" + rating + ", isActive=" + isActive + ", imagepath=" + imagepath + "]";
	}
}