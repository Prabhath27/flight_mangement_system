package com.flightmanagementsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.UserDao;
import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.entity.User;
import com.flightmanagementsystem.exception.UserManagementException;
import com.flightmanagementsystem.repository.UserRepository;

//The @Service annotation indicates that this class is a service component in the Spring framework.

@Service
public class UserService implements UserDao
{
  // The @Autowired annotation injects the UserRepository and ModelMapper beans into this service.

	@Autowired
	private UserRepository userRepository;
  // ModelMapper is a library for mapping objects, used for converting between DTO and Entity objects.

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDTO registerUser(UserDTO userDto){
      // Method to register a new user by converting a UserDTO to an Entity and saving it to the repository.

		//convert DTO to Entity
  	User userRequest=modelMapper.map(userDto, User.class);
  	if(userRepository.save(userRequest)==null) 
  	{
  		throw new UserManagementException("Invlaid user");
  	}
  	else 
  	{
		     User user=userRepository.save(userRequest);
	           // Convert the registered Entity back to a DTO for the response.
		     UserDTO userResponse=modelMapper.map(user, UserDTO.class);
		     return userResponse ;
  	}
	}

	@Override
	public UserDTO signIn(String userName, String password, String userRole) {
      // Method to authenticate a user by finding a matching record in the repository.
		UserDTO userDto=null;
		 if( userRepository.findByUserNameAndPasswordAndUserRole(userName, password,userRole)==null)
		 {
		 throw new UserManagementException("you are not yet registered");
		 }
		 else
		 {
	            // Convert the authenticated Entity to a DTO for the response.

			 userDto=modelMapper.map(userRepository.findByUserNameAndPasswordAndUserRole(userName, password,userRole),UserDTO.class);
			 return userDto;
		 }
	}

	@Override
	public String signOut() {
      // Method to handle user sign-out.
		return "You have been logged out.";
	}
}

