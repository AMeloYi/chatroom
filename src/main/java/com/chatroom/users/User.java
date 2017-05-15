package com.chatroom.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.chatroom.messages.Message;
import com.chatroom.rooms.Room;

/**
 * The Class User.
 */
@Entity
public class User {

	/** The id. */
	@Id
	@GeneratedValue
	private Long id;

	/** The username. */
	@Column(unique = true)
	private String username;

	/** The name. */
	private String name;

	/** The email. */
	@Column(unique = true)
	private String email;

	/** The encrypted password. */
	private String encryptedPassword;

	/** The enabled. */
	private boolean enabled = true;

	/** The role. */
	@Enumerated(EnumType.STRING) // stored as a string
	private Role role;

	/** The messages. */
	@OneToMany
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/** The room. */
	@ManyToOne
	private Room currentRoom;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the encrypted password.
	 *
	 * @return the encrypted password
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */

	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Sets the encrypted password.
	 *
	 * @param password
	 *            the new encrypted password
	 */
	public void setEncryptedPassword(String password) {
		this.encryptedPassword = password;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
