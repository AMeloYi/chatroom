package com.chatroom.users;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class AdminUserPopulator.
 */
@Component
class AdminUserPopulator {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		if (!userService.findByUsername("admin").isPresent()) {
			// we create the default admin account if it didn't exist
			userService.createUser("admin", "Administrator", "admin@localhost", "password", Role.ADMIN);
		}
	}
}
