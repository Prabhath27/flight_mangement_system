package com.flightmanagementsystem.dao;

import com.flightmanagementsystem.dto.UserDTO;

public interface UserDao {
	public UserDTO registerUser(UserDTO userDto);

	public UserDTO signIn(String userName, String password,String userRole);

	// use session management accordingly
	public String signOut();

}
