package com.chatroom.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatroom.users.Role;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

/**
 * The Class RegisterController.
 */
@Controller
public class RegisterController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Register.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute(new RegisterUserCommand());
		return "register";
	}

	/**
	 * Register.
	 *
	 * @param command
	 *            the command
	 * @param bindingResult
	 *            the binding result
	 * @return the string
	 */
	@PostMapping("/register")
	public String register(@Valid RegisterUserCommand command, BindingResult bindingResult) {
		// check unique constraints
		Optional<User> byEmail = userService.findByEmail(command.getUser().getEmail());
		if (byEmail.isPresent()) {
			bindingResult.rejectValue("user.email", "validation.email-does-exists");
		}

		Optional<User> byUsername = userService.findByUsername(command.getUser().getUsername());
		if (byUsername.isPresent()) {
			bindingResult.rejectValue("user.username", "validation.username-exists");
		}

		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			userService.createUser(command.getUser().getUsername(), command.getUser().getName(),
					command.getUser().getEmail(), command.getPassword().getPassword(), Role.USER);
			return "redirect:/login";
		}
	}
}
