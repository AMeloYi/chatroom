package com.chatroom.messages.systemmessage;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SystemMessage {

	private String title;
	private Date date;
	private String content;

	public SystemMessage(String title, String content) {
		this.title = title;
		this.content = content;
		this.date = new Date();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SystemMessage() {
	}

	public SystemMessage(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
