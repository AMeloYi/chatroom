package com.chatroom.commands;

import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService{

	@Override
	public void run(Command command, String string) {
		command.getStrategy().run(string);
	}
}
