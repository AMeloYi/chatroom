package com.chatroom.messages.chatmessage.privatemessage;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.chatroom.messages.Message;
import com.chatroom.users.User;

@Entity
public class PrivateMessage extends Message {

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@OneToOne(cascade = { CascadeType.MERGE })
	private User receiver;

	@ManyToOne(cascade = { CascadeType.MERGE })
	private User sender;

	public PrivateMessage() {
		super();
	}

	public PrivateMessage(User sender, User receiver, String content) {
		super(new Date(), content);
		this.receiver = receiver;
		this.sender = sender;
		this.setType(1);
	}
}
