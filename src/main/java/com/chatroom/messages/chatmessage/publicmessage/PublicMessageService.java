package com.chatroom.messages.chatmessage.publicmessage;

import java.util.List;

import com.chatroom.rooms.Room;
import com.chatroom.users.User;

public interface PublicMessageService {

	PublicMessage addMessage(String message, User user, Room room);

	PublicMessage save(PublicMessage publicMessage);

	List<PublicMessage> findAll();

	PublicMessage findById(Long id);
}
