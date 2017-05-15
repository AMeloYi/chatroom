package com.chatroom.commands.commandstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

@Component
public class ChangePasswordStrategy implements CommandStrategy{

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public SystemMessage run(String string) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		user.setEncryptedPassword(passwordEncoder.encode(string));
		userService.save(user);
		SystemMessage systemMessage = new SystemMessage("System Message:", "Change Password Success!");
		return systemMessage;
	}
}