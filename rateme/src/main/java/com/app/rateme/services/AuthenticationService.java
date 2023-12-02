package com.app.rateme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.rateme.api.userModelAssembler;
import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.LoginRequestBody;
import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    

   // private static final String ADMIN_ROLE = "ADMIN";
    //private static final String USER_ROLE = "USER";

    public User registerUser(UserDto userDto) {

        
        User nUser = userModelAssembler.toEntity(userDto);
        

        nUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        nUser.setRoles("USER_ROLE");

        return userRepository.save(nUser);
    }

    public Authentication loginUser(LoginRequestBody loginRequest) {

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        


        return authenticationResponse;
    }
}
