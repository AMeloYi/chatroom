package com.chatroom.messages.chatmessage.publicmessage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.chatroom.messages.Message;
import com.chatroom.users.User;

@Entity
public class PublicMessage extends Message {

	/** The sender. */
	@ManyToOne
	private User sender;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public PublicMessage() {
		super();
	}

	public PublicMessage(String content, User sender) {
		super(new Date(), content);
		this.sender = sender;
		this.setType(2);
	}
}
