package com.chatroom.controllers.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.chatroom.users.User;

/**
 * The Class UserCommand.
 */
public class UserCommand {

	/** The username. */
	@Length(min = 3, max = 50)
	@NotBlank
	@NotNull
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "{validation.username.invalid-char}")
	private String username;

	/** The name. */
	@Length(min = 3, max = 50)
	@NotBlank
	private String name;

	/** The email. */
	@NotBlank
	@Email
	private String email;

	/**
	 * Instantiates a new user command.
	 */
	public UserCommand() {
	}

	/**
	 * Instantiates a new user command.
	 *
	 * @param user
	 *            the user
	 */
	public UserCommand(User user) {
		username = user.getUsername();
		name = user.getName();
		email = user.getEmail();
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Update.
	 *
	 * @param user
	 *            the user
	 */
	public void update(User user) {
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
	}
}
