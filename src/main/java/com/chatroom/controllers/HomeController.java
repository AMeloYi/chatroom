package com.chatroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Class HomeController.
 */
@Controller
public class HomeController {

	/**
	 * Index.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	String index() {
		return "index";
	}
}
