package com.app.rateme.api;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.User;
import com.app.rateme.security.LoginToken;
import com.app.rateme.services.AccessManager;
import com.app.rateme.services.UserManager;

@RestController
@CrossOrigin("*")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AccessManager accessManager;

    @PostMapping("/register")
    public ResponseEntity<LoginToken> registerUser(@RequestBody UserDto registrationData) {
        try {
            ResponseEntity<LoginToken> response;

            if (registrationData.getUserName().length() == 0) {
                System.out.println("ERROR No username provided!");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            System.out.println();
            System.out.println("Register " + registrationData.getUserName());

            userManager.register(registrationData);

            response = accessManager.login(registrationData.getUserName(), registrationData.getPassword());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<UserDto> getUser(@RequestHeader("token") String loginToken) {

        // Access-control
        UUID uuid = UUID.fromString(loginToken);
        if (!this.accessManager.isLoggedIn(uuid)) {
            System.out.println("ERROR Access not allowed");
            return ResponseEntity.status(404).body(null);
        }

        Optional<String> optUsername = accessManager.getLoginName(UUID.fromString(loginToken));
        System.err.println("OPTUSERNAME"+ optUsername);

        if (optUsername.isPresent()) {

           
            UserDto userDto = userManager.getUserDtoData(optUsername.get());
            if (userDto != null) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(404).build();
            }
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
