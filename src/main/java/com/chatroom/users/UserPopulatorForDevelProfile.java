package com.chatroom.users;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * The Class UserPopulatorForDevelProfile.
 */
@Component
@Profile("devel")
class UserPopulatorForDevelProfile {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		userService.createUser("bonnie", "Bonnie Parket", "bonnie@parker.com", "rowena", Role.USER);
		userService.createUser("clyde", "Clyde Barrow", "clyde@ontheroad.us", "telico", Role.USER);
		userService.createUser("AAAA", "AAA1", "aa1a@aaa.aa", "aaaa", Role.USER);
		userService.createUser("AAA", "AAA", "aaa@aaa.aa", "aaa", Role.USER);
	}
}
