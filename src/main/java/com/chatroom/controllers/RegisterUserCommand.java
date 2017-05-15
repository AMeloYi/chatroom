package com.chatroom.controllers;

import javax.validation.Valid;

import com.chatroom.controllers.password.PasswordCommand;
import com.chatroom.controllers.user.UserCommand;

/**
 * The Class RegisterUserCommand.
 */
class RegisterUserCommand {

	/** The user. */
	@Valid
	private UserCommand user;

	/** The password. */
	@Valid
	private PasswordCommand password;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public PasswordCommand getPassword() {
		return password;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserCommand getUser() {
		return user;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(PasswordCommand password) {
		this.password = password;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(UserCommand user) {
		this.user = user;
	}
}
