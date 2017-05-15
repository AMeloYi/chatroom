package com.chatroom.commands.commandstrategy;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

@Component
public class CloseRoomStrategy implements CommandStrategy{

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public SystemMessage run(String string) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName()).get();
		SystemMessage systemMessage = new SystemMessage();
		if(roomService.findByName(string).isPresent()){
			
			List<User> userList = userService.getAllUsers();
			Iterator<User> it = userList.iterator();
			while(it.hasNext()){
				User c_user = it.next();
				if(c_user.getCurrentRoom()!=null && c_user.getCurrentRoom().getRoomName().equals(string)){
					roomService.removeUser(roomService.findByName(string).get(), c_user);
					userService.changeCurrentRoom(c_user, roomService.findByName("World").get());
					roomService.addUser(roomService.findByName("World").get(), c_user);
				}
			}

			roomService.remove(string);
			systemMessage = new SystemMessage("System Message:", "Close Room Success!");
			}
		else{
			systemMessage = new SystemMessage("System Message:", "Close Room Failed! Room does not exist!");
		}
		return systemMessage;
		}

}
