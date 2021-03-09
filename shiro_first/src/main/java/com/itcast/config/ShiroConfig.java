package com.itcast.config;

import java.util.HashMap;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itcast.shiro.CustomerRealm;
import com.itcast.shiro.cache.RedisCacheManger;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Bean(name = "shiroDialect")
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("/user/login", "anon");
		map.put("login.html", "anon");
		map.put("register.html", "anon");
		map.put("/register", "anon");
		map.put("/user/register", "anon");
		map.put("/**", "authc");
		shiroFilterFactoryBean.setLoginUrl("/user/loginview");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(realm);
		return defaultWebSecurityManager;
	}

	@Bean
	public Realm realm() {
		CustomerRealm customerRealm = new CustomerRealm();
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

		// 開啟緩存管理
		customerRealm.setCacheManager(new RedisCacheManger());
		customerRealm.setCachingEnabled(true);// 全局缓存
		customerRealm.setAuthenticationCachingEnabled(true);// 认证缓存
		customerRealm.setAuthenticationCacheName("authenticationCache");
		customerRealm.setAuthorizationCachingEnabled(true);// 授权缓存
		customerRealm.setAuthorizationCacheName("authorizationCache");
		return customerRealm;
	}
}
