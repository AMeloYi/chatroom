package com.chatroom.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chatroom.users.User;
import com.chatroom.users.UserService;

/**
 * The Class GlobalControllerAdvice.
 */
@ControllerAdvice
class GlobalControllerAdvice {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Adds the active user.
	 *
	 * @param model
	 *            the model
	 */
	@ModelAttribute
	void addActiveUser(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			System.err.println(auth);
			Optional<User> user = userService.findByUsername(auth.getName());
			if (user.isPresent()) {
				model.addAttribute("authUser", user.get());
			}
		}
	}

	/**
	 * Exception.
	 *
	 * @param throwable
	 *            the throwable
	 * @param model
	 *            the model
	 * @return the string
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model) {
		logger.error("Exception during execution of application", throwable);
		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}

}
