package com.app.rateme.api;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.security.LoginToken;
import com.app.rateme.services.AccessManager;

@RestController
@RequestMapping("access")
public class AccesController {

    @Autowired
    private AccessManager accessManager;

    @PostMapping
    public ResponseEntity<LoginToken> login(@RequestBody Map<String, Object> loginData) {
        try {
            String username = (String) loginData.get("userName");
            String password = (String) loginData.get("password");

            System.out.println("Try login User " + username);

            // Response OK
            ResponseEntity<LoginToken> response = accessManager.login(username, password);

            return response;
        } catch (Exception exce) {
            System.out.println("ERROR " + exce.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> logout(@RequestHeader("token") String loginToken) {
        try {
            System.out.println("Logout: " + this.accessManager.getLoginName(UUID.fromString(loginToken)));

            accessManager.logout(UUID.fromString(loginToken));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exce) {
            System.out.println("ERROR " + exce.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
