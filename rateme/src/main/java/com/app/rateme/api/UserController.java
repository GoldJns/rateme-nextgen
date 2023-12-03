package com.app.rateme.api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.LoginRequestBody;
import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.Role;
import com.app.rateme.model.User;
import com.app.rateme.repository.RoleRepository;
import com.app.rateme.repository.UserRepository;
import com.app.rateme.services.AuthenticationService;

@RestController
@RequestMapping("user")
public class UserController {

/* 

     private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
   

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
       
    }
*/



/* 
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDto registerDto) {
       

        User user = new User();
        user.setUsername(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

       
       

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

*/










    /* 
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@AuthenticationPrincipal LoginRequestBody userdto) {
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

    */
}
