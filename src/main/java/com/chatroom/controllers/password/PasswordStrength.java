package com.chatroom.controllers.password;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * The Interface PasswordStrength.
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordStrengthConstraintValidator.class)
public @interface PasswordStrength {

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
	String message() default "{validation.password-strength}";

	/**
	 * Payload.
	 *
	 * @return the class[]
	 */
	Class[] payload() default {};
}
