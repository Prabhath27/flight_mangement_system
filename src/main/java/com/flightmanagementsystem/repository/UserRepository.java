package com.flightmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public User findByUserNameAndPasswordAndUserRole(String userName, String password,String userRole);

}
