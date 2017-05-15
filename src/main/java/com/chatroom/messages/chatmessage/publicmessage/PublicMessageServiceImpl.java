package com.chatroom.messages.chatmessage.publicmessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.rooms.Room;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;

@Service
public class PublicMessageServiceImpl implements PublicMessageService {
	@Autowired
	private PublicMessageRepository repository;

	@Autowired
	private RoomService roomService;

	@Override
	public PublicMessage addMessage(String message, User user, Room room) {
		PublicMessage publicMessage = new PublicMessage(message, user);
		save(publicMessage);
		roomService.addMessage(room, publicMessage);
		return publicMessage;
	}

	@Override
	public PublicMessage save(PublicMessage publicMessage) {
		return repository.save(publicMessage);
	}

	@Override
	public List<PublicMessage> findAll() {
		return repository.findAll();
	}

	@Override
	public PublicMessage findById(Long id) {
		return repository.findOne(id);
	}
}
