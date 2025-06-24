package com.foodapp.model;


public class Employee {

	private String fullname;
	private String username;
	private String phoneNumber;
	private String email;
	private String password;
	private String confirmPassword;

	public Employee() {
	}

	public Employee(String fullname, String username, String phoneNumber, String email, String password,
			String confirmPassword) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "Employee [fullname=" + fullname + ", username=" + username + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

}
