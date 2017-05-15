package com.chatroom.controllers.password;

import org.hibernate.validator.constraints.Length;

/**
 * The Class PasswordCommand.
 */
@PasswordsEqualConstraint
public class PasswordCommand {

	/** The password. */
	@Length(min = 5, max = 20)
	@PasswordStrength
	private String password;

	/** The password confirmation. */
	private String passwordConfirmation;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the password confirmation.
	 *
	 * @return the password confirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the password confirmation.
	 *
	 * @param passwordConfirmation
	 *            the new password confirmation
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

}
