package com.itcast.service;

import java.util.List;

import com.itcast.entity.Perms;

public interface RoleService {
	List<Perms> findPermsByRoleId(String id);
}
