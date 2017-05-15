package com.chatroom.messages.chatmessage.privatemessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.rooms.Room;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {
	@Autowired
	private PrivateMessageRepository repository;

	@Autowired
	private RoomService roomService;

	@Override
	public PrivateMessage addMessage(String message, User sender, User receiver, Room room) {
		PrivateMessage privateMessage = new PrivateMessage(sender, receiver, message);
		save(privateMessage);
		roomService.addMessage(room, privateMessage);
		return privateMessage;
	}

	@Override
	public PrivateMessage save(PrivateMessage privateMessage) {
		return repository.save(privateMessage);
	}

	@Override
	public PrivateMessage findById(Long id) {
		return repository.findOne(id);
	}
}
