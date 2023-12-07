package com.app.rateme.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByusername(username);
	 	User user = new User();
		if (optUser.isPresent()) {
			user = optUser.get();
		}

		System.out.println("Loaded user: " + user.getUsername() + ", Password: " + user.getPassword() + ", Roles: "
				+ user.getRoles());

		return new AuthenticatedUser(user);

	}

}
