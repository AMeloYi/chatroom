package com.chatroom.rooms.generalroom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatroom.rooms.RoomService;

@Component
public class WorldRoom extends GeneralRoom {

	@Autowired
	private RoomService roomService;

	@PostConstruct
	public void init() {
		if (!roomService.findByName("World").isPresent()) {
			roomService.create("World");
		}
	}
}
