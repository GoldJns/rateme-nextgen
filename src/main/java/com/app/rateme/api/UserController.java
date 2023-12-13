package com.app.rateme.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.UserResponseDto;
import com.app.rateme.api.dto.UserModelAssembler;
import com.app.rateme.model.User;
import com.app.rateme.services.UserService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserModelAssembler userModelAssembler;

    @GetMapping("/{userId}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") Integer userId) {
		final Optional<User> userById = userService.getUserById(userId.intValue());
		if(userById.isPresent()) {
			User user = userById.get();
			UserResponseDto userResponse = userModelAssembler.toModelResponse(user);
			
			return new ResponseEntity<>(userResponse,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    
    
}
