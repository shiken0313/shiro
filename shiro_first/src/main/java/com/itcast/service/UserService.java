package com.itcast.service;



import com.itcast.entity.User;


public interface UserService {
	
	void register(User user);
	
	User findByUserName(String username);
	
	User findRolesByUserName(String userName);
	
}
