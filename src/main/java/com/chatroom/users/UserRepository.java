package com.chatroom.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface UserRepository.
 */
interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the optional
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Find by username.
	 *
	 * @param username
	 *            the username
	 * @return the optional
	 */
	Optional<User> findByUsername(String username);

	Optional<User> findByName(String name);

}
