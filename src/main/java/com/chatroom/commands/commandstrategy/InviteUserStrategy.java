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
public class InviteUserStrategy implements CommandStrategy{

	@Autowired
	private UserService userService;
	@Autowired
	private RoomService roomService;
	
	@Override
	public SystemMessage run(String string) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		SystemMessage systemMessage = new SystemMessage();
		
		if(userService.findByName(string).isPresent()){
			User newUser = userService.findByName(string).get();
			if(newUser.getCurrentRoom()!=null){
				roomService.removeUser(newUser.getCurrentRoom(),newUser);
			}
			roomService.addUser(user.getCurrentRoom(), newUser);
			userService.changeCurrentRoom(newUser, user.getCurrentRoom());
			systemMessage = new SystemMessage("System Message:", "Invite user Success!");
		}
		else{
			systemMessage = new SystemMessage("System Message:", "Invite user Failed! User does not exist!");
		}
		return systemMessage;
	}
}
