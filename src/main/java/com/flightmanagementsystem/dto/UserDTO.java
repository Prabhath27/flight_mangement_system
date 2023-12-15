package com.flightmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
	
	private Long userId;
	@NotBlank(message = "Username must not be null")
	private String userName;
	@NotBlank(message = "Password must not be null")
	private String password;
	@Email(message="this is not correct email")
	private String email;
	@Size(min=10,max=12, message = "Mobile number must be 12 digits")
	@NotNull(message = "Mobile number must not be null")
	private String mobileNumber;
	
	// Admin , Passenger
	@NotBlank(message = "User role must not be null")
	private String userRole;
	public UserDTO(Long userId, String userName, String password, String email, String mobileNumber, String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
	}
	public UserDTO() {}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", userRole=" + userRole + "]";
	}
	

}
