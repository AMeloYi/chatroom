package com.chatroom.commands;

import com.chatroom.commands.commandstrategy.CommandStrategy;

public class Command {
	
	private CommandStrategy strategy;

	public Command(CommandStrategy strategy){
		this.strategy = strategy;
	}
	
	public CommandStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(CommandStrategy strategy) {
		this.strategy = strategy;
	}
}
