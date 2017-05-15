package com.chatroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatroom.commands.commandstrategy.ChangePasswordStrategy;
import com.chatroom.commands.commandstrategy.ChangeRoomStrategy;
import com.chatroom.commands.commandstrategy.CloseRoomStrategy;
import com.chatroom.commands.commandstrategy.CreateRoomStrategy;
import com.chatroom.commands.commandstrategy.DisplayMeStrategy;
import com.chatroom.commands.commandstrategy.DisplayTimeStrategy;
import com.chatroom.commands.commandstrategy.InviteUserStrategy;
import com.chatroom.commands.commandstrategy.ListRoomsStrategy;
import com.chatroom.commands.commandstrategy.LogOutStrategy;
import com.chatroom.messages.chatmessage.privatemessage.PrivateMessageService;
import com.chatroom.messages.chatmessage.publicmessage.PublicMessageService;
import com.chatroom.messages.systemmessage.SystemMessage;
import com.chatroom.rooms.Room;
import com.chatroom.rooms.RoomService;
import com.chatroom.users.User;
import com.chatroom.users.UserService;

@Controller
public class ChatroomController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private PrivateMessageService privateMessageService;

	@Autowired
	private PublicMessageService publicMessageService;

	@Autowired
	private SystemMessage systemMessage;

	@Autowired
	private CreateRoomStrategy createRoomStrategy;
	@Autowired
	private CloseRoomStrategy closeRoomStrategy;
	@Autowired
	private ChangeRoomStrategy changeRoomStrategy;
	@Autowired
	private InviteUserStrategy inviteUserStrategy;
	@Autowired
	private ChangePasswordStrategy changePasswordStrategy;
	@Autowired
	private DisplayMeStrategy displayMeStrategy;
	@Autowired
	private DisplayTimeStrategy displayTimeStrategy;
	@Autowired
	private ListRoomsStrategy listRoomsStrategy;
	@Autowired
	private LogOutStrategy logOutStrategy;

	public ChatroomController(UserService userService, RoomService roomService,
			PrivateMessageService privateMessageService, PublicMessageService publicMessageService) {
		this.userService = userService;
		this.roomService = roomService;
		this.privateMessageService = privateMessageService;
		this.publicMessageService = publicMessageService;
	}

	@GetMapping("/chatroom")
	public String chatroom(Model model, @ModelAttribute("authUser") User user) {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			if (user.getCurrentRoom() == null || roomService.findByName(user.getCurrentRoom().getRoomName()) == null) {
				Room room = roomService.findByName("World").get();
				userService.changeCurrentRoom(user, room);
				roomService.addUser(room, user);
			}
			model.addAttribute("SystemTitle", systemMessage.getTitle());
			model.addAttribute("SystemMessage", systemMessage.getContent());
			model.addAttribute("users", user.getCurrentRoom().getUsers());
			model.addAttribute("currentroom", user.getCurrentRoom().getRoomName());
			return "chatroom";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/chatroom")
	public String sendMessage(Model model, @RequestParam("inputText") String message,
			@RequestParam("selectReceiver") String receiver, @ModelAttribute("authUser") User user) {

		if(message.startsWith("\\")){
			char[] c = message.toCharArray();
			String bbb = "";
			for(int i=1;i<c.length;i++){
				bbb = bbb + c[i];
			}
			systemMessage = new SystemMessage("Current Time:", bbb);
			String[] commandMessage = bbb.split(" ");
			if (commandMessage.length == 1) {
				if (commandMessage[0].equals("logout")) {
					systemMessage = logOutStrategy.run(null);
					return "redirect:" + systemMessage.getContent();
				} else if (commandMessage[0].equals("displaytime")) {
					systemMessage = displayTimeStrategy.run(null);
				} else if (commandMessage[0].equals("displayme")) {
					systemMessage = displayMeStrategy.run(null);
				} else if (commandMessage[0].equals("listrooms")) {
					systemMessage = listRoomsStrategy.run(null);
				}
			} else if (commandMessage.length == 2) {
				if (commandMessage[0].equals("changepassword")) {
					systemMessage = changePasswordStrategy.run(commandMessage[1]);
					return "redirect:/logout";
				} else if (commandMessage[0].equals("createroom")) {
					systemMessage = createRoomStrategy.run(commandMessage[1]);	
				} else if (commandMessage[0].equals("closeroom")) {
					systemMessage = closeRoomStrategy.run(commandMessage[1]);
				} else if (commandMessage[0].equals("changeroom")) {
					systemMessage = changeRoomStrategy.run(commandMessage[1]);
				} else if (commandMessage[0].equals("inviteuser")) {
					systemMessage = inviteUserStrategy.run(commandMessage[1]);
				}
			}

		} else {
			Room room = user.getCurrentRoom();
			if (receiver.equals(room.getRoomName()))
				userService.addMessage(user, publicMessageService.addMessage(message, user, room));
			else
				userService.addMessage(user,
						privateMessageService.addMessage(message, user, userService.findByName(receiver).get(), room));
		}
		return "redirect:/chatroom";
	}
}
