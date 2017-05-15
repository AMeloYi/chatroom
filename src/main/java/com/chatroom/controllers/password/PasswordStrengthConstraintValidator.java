package com.chatroom.controllers.password;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Class PasswordStrengthConstraintValidator.
 */
public class PasswordStrengthConstraintValidator implements ConstraintValidator<PasswordStrength, String> {

	/**
	 * Constains any.
	 *
	 * @param value
	 *            the value
	 * @param chars
	 *            the chars
	 * @return true, if successful
	 */
	private boolean constainsAny(String value, String chars) {
		for (int i = 0; i < chars.length(); ++i) {
			if (value.indexOf(chars.charAt(i)) != -1) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(PasswordStrength constraintAnnotation) {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return constainsAny(value, "+-.=") && constainsAny(value, "0123456789")
				&& (!Objects.equals(value, value.toLowerCase())) && (!Objects.equals(value, value.toUpperCase()));
	}

}
