package com.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itcast.entity.Perms;

@Mapper
public interface RoleDao {

	List<Perms> findPermsByRoleId(String id);
}
