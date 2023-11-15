package com.app.rateme.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rateme.api.dto.UserDto;
import com.app.rateme.dao.UserRepository;
import com.app.rateme.model.User;
import com.app.rateme.util.PasswordTools;

import jakarta.persistence.NoResultException;

@Service
public class UserManager {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> lookupUser(String username) {

		User user = userRepository.getByusername(username);
		return Optional.of(user);
	}

	public UserDto getUserDtoData(String username) {

		UserDto userDto = new UserDto();
		Optional<User> optUser = lookupUser(username);

		if (optUser.isPresent()) {
			User user = optUser.get();
			userDto.setCity(user.getCity());
			userDto.setEmail(user.getMail());
			userDto.setFirstName(user.getFirstname());
			userDto.setLastName(user.getLastname());
			userDto.setStreet(user.getStreet());
			userDto.setStreetNr(user.getStreetNr());
			userDto.setZip(user.getZip());
			userDto.setUserName(user.getUsername());
			userDto.setPassword(""); // placeholder
		} else {
			userDto = null;
		}
		return userDto;
	}

	public Optional<User> register(UserDto data) throws Exception {
		User user = new User();
		user.setCity(data.getCity());
		user.setMail(data.getEmail());
		user.setFirstname(data.getFirstName());
		user.setLastname(data.getLastName());
		user.setStreet(data.getStreet());
		user.setStreetNr(data.getStreetNr());
		user.setUsername(data.getUserName());
		user.setZip(data.getZip());

		byte[] salt;
		byte[] passwordHash;
		try {
			final User userName = userRepository.getByusername(user.getUsername());
			if (userName != null) {
				throw new Exception("User already exists");
			}
		} catch (NoResultException ex) {

		}

		try {

			salt = PasswordTools.generateSalt();
			passwordHash = PasswordTools.generatePasswordHash(data.getPassword(), salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR User creation failed: " + e.getMessage());
		}

		user.setPasswordSalt(salt);
		user.setPasswordHash(passwordHash);

		final User createdUser = userRepository.save(user);

		return Optional.of(createdUser);
	}
}
