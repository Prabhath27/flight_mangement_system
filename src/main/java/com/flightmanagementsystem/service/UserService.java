package com.flightmanagementsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.UserDao;
import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.entity.User;
import com.flightmanagementsystem.exception.UserManagementException;
import com.flightmanagementsystem.repository.UserRepository;

@Service
public class UserService implements UserDao {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO registerUser(UserDTO userDto){
		
		//convert DTO to Entity
    	User userRequest=modelMapper.map(userDto, User.class);
    	if(userRepository.save(userRequest)==null) {
    		throw new UserManagementException("Invlaid user");
    		}else {
		User user=userRepository.save(userRequest);
		
		//convert Entity to DTO
		 UserDTO userResponse=modelMapper.map(user, UserDTO.class);
		return userResponse ;
    		}
	}

	@Override
	public UserDTO signIn(String userName, String password, String userRole) {
		// TODO Auto-generated method stubs
		UserDTO userDto=null;
		 if( userRepository.findByUserNameAndPasswordAndUserRole(userName, password,userRole)==null)
		 {
		 throw new UserManagementException("you are not yet registered");
		 }
		 else {
			 userDto=modelMapper.map(userRepository.findByUserNameAndPasswordAndUserRole(userName, password,userRole),UserDTO.class);
			 return userDto;
			 
		 }
	}

	@Override
	public String signOut() {
		// TODO Auto-generated method stub
		return "You have been logged out.";
	}

}
