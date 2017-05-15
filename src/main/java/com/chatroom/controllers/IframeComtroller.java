package com.chatroom.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.chatroom.messages.Message;
import com.chatroom.messages.chatmessage.privatemessage.PrivateMessage;
import com.chatroom.messages.chatmessage.privatemessage.PrivateMessageService;
import com.chatroom.messages.chatmessage.publicmessage.PublicMessage;
import com.chatroom.messages.chatmessage.publicmessage.PublicMessageService;
import com.chatroom.rooms.Room;
import com.chatroom.users.User;

@Controller
public class IframeComtroller {

	@Autowired
	private PrivateMessageService privateMessageService;

	@Autowired
	private PublicMessageService publicMessageService;

	@GetMapping("/publicmessage")
	public String showPublicMessage(Model model, @ModelAttribute("authUser") User user) {
		Room room = user.getCurrentRoom();
		List<String> listString = new ArrayList<String>();
		List<Message> listMessage = room.getMessages();
		if (!listMessage.isEmpty()) {
			Iterator<Message> it = listMessage.iterator();
			String string = null;
			PublicMessage message = null;
			while (it.hasNext()) {
				message = publicMessageService.findById(it.next().getId());
				if (message != null) {
					string = message.getSender().getName() + " : " + message.getContent() + "   ----"
							+ message.getDate();
					listString.add(string);
				}
			}
			model.addAttribute("publicmessages", listString);
		}
		return "publicmessage";
	}

	@GetMapping("/privatemessage")
	public String showPrivateMessage(Model model, @ModelAttribute("authUser") User user) {
		Room room = user.getCurrentRoom();
		List<String> listString = new ArrayList<String>();
		List<Message> listMessage = room.getMessages();
		if (!listMessage.isEmpty()) {
			Iterator<Message> it = listMessage.iterator();
			String string = null;
			PrivateMessage message = null;
			while (it.hasNext()) {
				message = privateMessageService.findById(it.next().getId());
				if (message != null) {
					if (message.getSender().getName().equals(user.getName())) {
						string = "You to " + message.getReceiver().getName() + " : " + message.getContent() + "   ----"
								+ message.getDate();
						listString.add(string);
					} else if (message.getReceiver().getName().equals(user.getName())) {
						string = message.getSender().getName() + " to you : " + message.getContent() + "   ----"
								+ message.getDate();
						listString.add(string);
					}
				}
			}
			model.addAttribute("privatemessages", listString);
		}
		return "privatemessage";
	}

	@GetMapping("/systemmessage")
	public String showSystemMessage(Model model, @ModelAttribute("authUser") User user) {
		model.addAttribute("users", user.getCurrentRoom().getUsers());
		return "systemmessage";
	}
}
