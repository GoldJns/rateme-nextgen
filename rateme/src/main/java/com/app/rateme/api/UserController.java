package com.app.rateme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.LoginRequestBody;
import com.app.rateme.services.AuthenticationService;

@RestController
@CrossOrigin("*")
@RequestMapping("user")
public class UserController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestBody LoginRequestBody userdto) {
        Authentication token = authenticationService.loginUser(userdto);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto){

        return new ResponseEntity<>(userModelAssembler.toModel(authenticationService.registerUser(userdto)), HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestHeader("token") String loginToken) {

        // gucken ob user eingeloggt ist (oder laufende session hat?)
        return null;
    }
}
