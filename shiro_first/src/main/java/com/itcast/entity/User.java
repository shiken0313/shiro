package com.itcast.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	private String id;
	private String username;
	private String password;
	private String salt;
	
	private List<Role> roles;
}
