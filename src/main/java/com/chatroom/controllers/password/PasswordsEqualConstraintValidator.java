package com.chatroom.controllers.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Class PasswordsEqualConstraintValidator.
 */
public class PasswordsEqualConstraintValidator
		implements ConstraintValidator<PasswordsEqualConstraint, PasswordCommand> {

	/** The message. */
	private String message;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(PasswordsEqualConstraint flags) {
		message = flags.message();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(PasswordCommand candidate, ConstraintValidatorContext context) {
		if (!candidate.getPassword().equals(candidate.getPasswordConfirmation())) {
			// disable constraint on class
			context.disableDefaultConstraintViolation();
			// add constraint on field
			context.buildConstraintViolationWithTemplate(message).addPropertyNode("passwordConfirmation")
					.addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
