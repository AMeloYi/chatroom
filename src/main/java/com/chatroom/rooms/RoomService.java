package com.chatroom.rooms;

import java.util.List;
import java.util.Optional;

import com.chatroom.messages.Message;
import com.chatroom.users.User;

public interface RoomService {

	Room create();

	Room create(String name);

	Room create(Room room);

	void remove(String name);

	List<Room> findAll();

	Room findByID(Long id);

	Optional<Room> findByName(String roomName);

	void addUser(Room room, User user);

	void removeUser(Room room, User user);

	void addMessage(Room room, Message message);
}
