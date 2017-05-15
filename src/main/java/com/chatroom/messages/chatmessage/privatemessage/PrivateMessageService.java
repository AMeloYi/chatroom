package com.chatroom.messages.chatmessage.privatemessage;

import com.chatroom.rooms.Room;
import com.chatroom.users.User;

public interface PrivateMessageService {

	PrivateMessage addMessage(String message, User sender, User receiver, Room room);

	PrivateMessage save(PrivateMessage privateMessage);

	PrivateMessage findById(Long id);
}
