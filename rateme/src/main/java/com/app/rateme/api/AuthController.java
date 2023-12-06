package com.app.rateme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.LoginRequestBody;
import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

 

    @Autowired
    private PasswordEncoder passwordEncoder;

  
   

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestBody loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User logged in!", HttpStatus.OK);
    }

    

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDto registerDto){

        User user = userModelAssembler.toEntity(registerDto);
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        
        user.setRoles("USER");

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
        
    }
/* 
    @PostMapping("logout")
 public ResponseEntity<String> logout(HttpServletRequest request)
 {    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

    if(authentication != null){
    SecurityContextLogoutHandler scl = new SecurityContextLogoutHandler();
    scl.setInvalidateHttpSession(true);
    scl.logout(request, null, authentication);
    }
    return new ResponseEntity<>("User logged out!", HttpStatus.OK);
 }  
 */
}
