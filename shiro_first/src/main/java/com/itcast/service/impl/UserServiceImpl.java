package com.itcast.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itcast.dao.UserDao;
import com.itcast.entity.Perms;
import com.itcast.entity.Role;
import com.itcast.entity.User;
import com.itcast.service.UserService;
import com.itcast.utils.SaltUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void register(User user) {
		//md5+salt+hash
		String salt = SaltUtils.getSalt(8);
		user.setSalt(salt);
		Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
		user.setPassword(md5Hash.toHex());
		userDao.save(user);
	}

	@Override
	public User findByUserName(String username) {
	
		return userDao.findByUserName(username);
	}

	@Override
	public User findRolesByUserName(String userName) {
		
		return userDao.findRolesByUserName(userName);
	}



}
