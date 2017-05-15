package com.chatroom.rooms;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.chatroom.messages.Message;
import com.chatroom.users.User;

/**
 * The Class User.
 */
@Entity
public class Room {
	/** The id. */
	@Id
	@GeneratedValue
	private Long id;

	/** The roomname. */
	@Column(unique = true)
	private String roomName;

	/** The users in the room. */
	@OneToMany
	private List<User> users;

	/** The messages. */
	@OneToMany
	private List<Message> messages;

	public Room() {
	}

	public Room(String name) {
		this.roomName = name;
	}

	public Room(String name, List<User> users, List<Message> messages) {
		this.roomName = name;
		this.users = users;
		this.messages = messages;
	}

	public Long getId() {
		return id;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public String getRoomName() {
		return roomName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
