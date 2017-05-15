package com.chatroom.commands.commandstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.UserService;

@Component
public class CreateRoomStrategy implements CommandStrategy{
	
	@Autowired
	private RoomService roomService;
	@Override
	public SystemMessage run(String string) {
		SystemMessage systemMessage = new SystemMessage();
		if(roomService.findByName(string).isPresent()){
			systemMessage = new SystemMessage("System Message:", "Room already exists!");
		}
		else{
			roomService.create(string);
			systemMessage = new SystemMessage("System Message:", "Create Room Success!");
		}
		return systemMessage;
	}
}
