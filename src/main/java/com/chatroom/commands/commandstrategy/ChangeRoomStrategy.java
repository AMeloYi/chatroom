package com.chatroom.commands.commandstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.Room;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

@Component
public class ChangeRoomStrategy implements CommandStrategy{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	
    public ChangeRoomStrategy(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }
	
	@Override
	public SystemMessage run(String string) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		SystemMessage systemMessage = new SystemMessage();
		if(!roomService.findByName(string).isPresent()){
			systemMessage = new SystemMessage("System Message:", "Change Room Failed! Room does not exist!");
		}
		else{	
			Room oldRoom = user.getCurrentRoom();
			roomService.removeUser(oldRoom, user);
			Room newRoom = roomService.findByName(string).get();
			userService.changeCurrentRoom(user, newRoom);
			roomService.addUser(newRoom, user);
		systemMessage = new SystemMessage("System Message:", "Change Room Success!");
		}
		return systemMessage;
	}
}
