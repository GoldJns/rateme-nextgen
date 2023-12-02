package com.app.rateme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.rateme.services.UserService;

@Service
public class AppUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
	private UserService userService;

	

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		return this.userService
				.findByUsername(user.getUsername())
				.map(u -> {
					u.setPassword(newPassword);
					
					final var updatedUser = this.userService.save(u);
					return new AuthenticatedUser(updatedUser);
				})
				.orElseThrow(() -> new UsernameNotFoundException("The user with user name " + user.getUsername() + " could not be found"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userService
				.findByUsername(username)
				.map(AuthenticatedUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("The user with user name " + username + " could not be found"));
	}

}
