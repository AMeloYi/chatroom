package com.chatroom.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatroom.controllers.user.UserCommand;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

/**
 * The Class AdminUsersController.
 */
@Controller
public class AdminUsersController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Delete.
	 *
	 * @param user
	 *            the user
	 * @return the string
	 */
	@GetMapping("/admin/users/delete/{id}")
	public String delete(@PathVariable("id") User user) {
		userService.delete(user);
		return "redirect:/admin/users";
	}

	/**
	 * Disable.
	 *
	 * @param user
	 *            the user
	 * @return the string
	 */
	@GetMapping("/admin/users/disable/{id}")
	public String disable(@PathVariable("id") User user) {
		user.setEnabled(false);
		userService.save(user);
		return "redirect:/admin/users";
	}

	/**
	 * Edits the.
	 *
	 * @param user
	 *            the user
	 * @param model
	 *            the model
	 * @return the string
	 */
	@GetMapping("/admin/users/edit/{id}")
	public String edit(@PathVariable("id") User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute(new UserCommand(user));
		return "users/edit";
	}

	/**
	 * Edits the.
	 *
	 * @param user
	 *            the user
	 * @param command
	 *            the command
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model
	 * @return the string
	 */
	@PostMapping("/admin/users/edit/{id}")
	public String edit(@PathVariable("id") User user, @Valid UserCommand command, BindingResult bindingResult,
			Model model) {
		model.addAttribute("user", user);

		// check unique constraints
		Optional<User> byEmail = userService.findByEmail(command.getEmail());
		if (byEmail.isPresent() && byEmail.get().getId() != user.getId()) {
			bindingResult.rejectValue("email", "validation.email-does-exists");
		}

		Optional<User> byUsername = userService.findByUsername(command.getUsername());
		if (byUsername.isPresent() && byUsername.get().getId() != user.getId()) {
			bindingResult.rejectValue("username", "validation.username-exists");
		}

		if (bindingResult.hasErrors()) {
			return "users/edit";
		} else {
			command.update(user);
			userService.save(user);
			return "redirect:/admin/users";
		}
	}

	/**
	 * Enable.
	 *
	 * @param user
	 *            the user
	 * @return the string
	 */
	@GetMapping("/admin/users/enable/{id}")
	public String enable(@PathVariable("id") User user) {
		user.setEnabled(true);
		userService.save(user);
		return "redirect:/admin/users";
	}

	/**
	 * Home.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@GetMapping("/admin/users")
	public String home(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users/users";
	}
}
