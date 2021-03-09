package com.itcast.shiro;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import com.itcast.entity.Perms;
import com.itcast.entity.User;
import com.itcast.salt.MyByteSource;
import com.itcast.service.RoleService;
import com.itcast.service.UserService;
import com.itcast.utils.ApplicationContextUtils;

public class CustomerRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//獲取身分信息
		String primaryPrincipal = (String)principals.getPrimaryPrincipal();
		UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
		RoleService roleService =(RoleService) ApplicationContextUtils.getBean("roleServiceImpl");
		User user = userService.findRolesByUserName(primaryPrincipal);
		if(!CollectionUtils.isEmpty(user.getRoles())) {
			 SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			 user.getRoles().forEach(role->{simpleAuthorizationInfo.addRole(role.getName());
				
			 List<Perms> perms = roleService.findPermsByRoleId(role.getId());
			 if(!CollectionUtils.isEmpty(perms)) {
				 perms.forEach(perm->{
					 simpleAuthorizationInfo.addStringPermission(perm.getName());
					 System.out.println(perm.getName());
				 });
			 }
			 });
			return simpleAuthorizationInfo;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	
		String principal = (String) token.getPrincipal();
		UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");

		User user = userService.findByUserName(principal);
		if (!ObjectUtils.isEmpty(user)) {
			return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),new MyByteSource(user.getSalt()),this.getName());
		}

		return null;
	}

}
