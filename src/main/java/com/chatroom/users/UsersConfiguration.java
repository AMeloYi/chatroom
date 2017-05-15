package com.chatroom.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class UsersConfiguration.
 */
@Configuration
class UsersConfiguration {

	/**
	 * Provide strong password encryption using BCrypt.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Provide hierarchical roles.
	 *
	 * @return the role hierarchy
	 */
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl rh = new RoleHierarchyImpl();
		rh.setHierarchy("ROLE_ADMIN > ROLE_MANAGER ROLE_ADMIN > ROLE_USER");
		return rh;
	}

}
