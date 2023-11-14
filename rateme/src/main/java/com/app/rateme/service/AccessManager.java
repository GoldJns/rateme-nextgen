package com.app.rateme.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.rateme.model.User;
import com.app.rateme.security.LoginToken;
import com.app.rateme.util.PasswordTools;

@Service
public class AccessManager {

    @Autowired
    private UserManager userManager;

    private Map<UUID, String> logins = new ConcurrentHashMap<>();

    public boolean isLoggedIn(UUID loginID) {
        return this.logins.containsKey(loginID);
    }

    public Optional<String> getLoginName(UUID loginID) {

        String loginname = this.logins.get(loginID);
        if (loginname != null) {
            return Optional.of(loginname);
        } else {
            return Optional.empty();
        }
    }

    public ResponseEntity<LoginToken> login(String loginname, String password) {

        ResponseEntity<LoginToken> response;

        if (this.logins.containsValue(loginname)) {
            RuntimeException exce = new RuntimeException("ERROR: User is already logged in");
            throw exce;
        }

        try {
            Optional<User> optUser = userManager.lookupUser(loginname);
            if (optUser.isPresent()) {
                // Check Password

                byte[] loginPasswordHash = PasswordTools
                        .generatePasswordHash(password, optUser.get().getPasswordSalt());
                byte[] originalPasswordHash = optUser.get().getPasswordHash();

                if (Arrays.equals(loginPasswordHash, originalPasswordHash) == false) {
                    System.err.println("Wrong Password");
                    throw new RuntimeException("Wrong Password");
                }

                // Login
                UUID utoken = UUID.randomUUID();
                this.logins.put(utoken, loginname);
                LoginToken token = new LoginToken(utoken.toString());
                

                
                response = new ResponseEntity<>(token,HttpStatus.OK);
                
            }else{
                System.err.println("User " + loginname + " not known");
				throw new RuntimeException("User not known");
            }

        } catch (Exception exce) {
            System.out.println("ERROR " + exce.getMessage());
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;

    }

    public void logout(UUID loginID) {
        if (this.logins.containsKey(loginID) == false) {
            RuntimeException exce = new RuntimeException("User was not logged in");
            throw exce;
        }

        this.logins.remove(loginID);
        return;
    }

}
