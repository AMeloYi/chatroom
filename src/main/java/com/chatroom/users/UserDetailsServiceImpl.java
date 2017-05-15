package com.chatroom.users;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The Class UserDetailsServiceImpl.
 */
@Service("userDetailsService")
class UserDetailsServiceImpl implements UserDetailsService {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The role hierarchy. */
	@Autowired
	private RoleHierarchy roleHierarchy;

	/**
	 * Instantiates a new user details service impl.
	 *
	 * @param userService
	 *            the user service
	 */
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("loadUserByUsername " + username);
		// lookup user
		Optional<User> result = userService.findByUsername(username);

		if (result.isPresent()) {
			User user = result.get();
			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getEncryptedPassword(), user.isEnabled(), true, true, true,
					roleHierarchy.getReachableGrantedAuthorities(Collections
							.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()))));
		} else {
			throw new UsernameNotFoundException("User '" + username + "' not found.");
		}
	}

}
