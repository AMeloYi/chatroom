package com.chatroom.messages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Message {

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String content;

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Message() {
	}

	public Message(Date date, String content) {
		this.date = date;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
