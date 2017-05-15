package com.chatroom.commands.commandstrategy;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.Room;
import com.chatroom.rooms.RoomService;

@Component
public class ListRoomsStrategy implements CommandStrategy{

	private String rooms = "";
	
	@Autowired
	private RoomService roomService;
	
	@Override
	public SystemMessage run(String string) {
		this.rooms="";
		SystemMessage systemMessage = new SystemMessage();
		List<Room> list = roomService.findAll();
		Iterator<Room> it = list.iterator();
		while(it.hasNext()){
			Room room = it.next();
			rooms = rooms + room.getRoomName() + ',' +"\r\n";
		}
		systemMessage = new SystemMessage("List of rooms:", rooms);
		return systemMessage;
	}
}
