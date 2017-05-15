package com.chatroom.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Class LoginController.
 */
@Controller
public class LoginController {
	/**
	 * Login.
	 *
	 * @return the string
	 */
	@GetMapping("/login")
	public String login() {
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return "login";
		} else {
			return "redirect:/";
		}
	}

	/**
	 * Login error.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "redirect:login";
	}

}
