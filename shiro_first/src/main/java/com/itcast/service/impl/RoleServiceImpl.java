package com.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.dao.RoleDao;
import com.itcast.entity.Perms;
import com.itcast.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Perms> findPermsByRoleId(String id) {
		
		return roleDao.findPermsByRoleId(id);
	}

}
