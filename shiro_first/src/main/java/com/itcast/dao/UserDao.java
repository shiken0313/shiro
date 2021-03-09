package com.itcast.dao;





import org.apache.ibatis.annotations.Mapper;


import com.itcast.entity.User;

@Mapper
public interface UserDao {

	
	void save(User user);
	
	User findByUserName(String username);
	
	User findRolesByUserName(String userName);
	
}
