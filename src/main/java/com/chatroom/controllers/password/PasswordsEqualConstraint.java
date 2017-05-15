package com.chatroom.controllers.password;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * The Interface PasswordsEqualConstraint.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)
public @interface PasswordsEqualConstraint {

	/**
	 * Groups.
	 *
	 * @return the class[]
	 */
	Class[] groups() default {};

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "{validation.passwords-do-not-match}";

	/**
	 * Payload.
	 *
	 * @return the class[]
	 */
	Class[] payload() default {};
}
