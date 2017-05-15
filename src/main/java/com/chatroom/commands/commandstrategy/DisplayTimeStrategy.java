package com.chatroom.commands.commandstrategy;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.chatroom.messages.systemmessage.SystemMessage;

@Component
public class DisplayTimeStrategy implements CommandStrategy{

	@Override
	public SystemMessage run(String string) {
		SystemMessage systemMessage = new SystemMessage();
		systemMessage = new SystemMessage("Current Time:", " " + new Date());
		return systemMessage;
	}
}
