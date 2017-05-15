package com.chatroom.commands.commandstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

@Component
public class LogOutStrategy implements CommandStrategy{

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public SystemMessage run(String string) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		
		roomService.removeUser(user.getCurrentRoom(), user);
		userService.leave(user);
		SystemMessage systemMessage = new SystemMessage("System Message:","/logout");
		return systemMessage;
	}
}
