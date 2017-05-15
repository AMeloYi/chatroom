package com.chatroom.rooms;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.messages.Message;
import com.chatroom.users.User;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository repository;

	public RoomServiceImpl(RoomRepository repository) {
		this.repository = repository;
	}

	@Override
	public Room create() {
		return repository.save(new Room());
	}

	@Override
	public Room create(String name) {
		return repository.save(new Room(name));
	}

	@Override
	public Room create(Room room) {
		return repository.save(room);
	}

	@Override
	public List<Room> findAll() {
		return repository.findAll();
	}

	@Override
	public Room findByID(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Optional<Room> findByName(String roomName) {
		return repository.findByRoomName(roomName);
	}

	@Override
	public void addUser(Room room, User user) {
		room.getUsers().add(user);
		repository.save(room);
	}

	@Override
	public void removeUser(Room room, User user) {
		room.getUsers().remove(user);
		repository.save(room);
	}

	@Override
	public void addMessage(Room room, Message message) {
		room.getMessages().add(message);
		repository.save(room);
	}

	@Override
	public void remove(String name) {
		Room room = repository.findByRoomName(name).get();
		repository.delete(room);
	}
}
