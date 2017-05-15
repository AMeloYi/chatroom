package com.chatroom.commands.commandstrategy;

import com.chatroom.messages.systemmessage.SystemMessage;

public interface CommandStrategy {
	SystemMessage run(String string);
}