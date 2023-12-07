package com.app.rateme.api;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.AuthenticationResponse;
import com.app.rateme.api.dto.LoginRequestBody;
import com.app.rateme.api.dto.UserDto;
import com.app.rateme.api.dto.userModelAssembler;
import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;
import com.app.rateme.security.AppUserDetailsService;
import com.app.rateme.security.JWTGenerator;
import com.app.rateme.security.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    /*
     * @Autowired
     * private AuthenticationManager authenticationManager;
     * 
     * @Autowired
     * private UserRepository userRepository;
     * 
     * @Autowired
     * private PasswordEncoder passwordEncoder;
     * 
     * @Autowired
     * private JWTGenerator jwtGenerator;
     * 
     * @PostMapping("login")
     * public ResponseEntity<String> login(@RequestBody LoginRequestBody loginDto) {
     * Authentication authentication = authenticationManager
     * .authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(),
     * loginDto.password()));
     * 
     * SecurityContextHolder.getContext().setAuthentication(authentication);
     * String token = jwtGenerator.generateToken(authentication);
     * return new ResponseEntity<>(token, HttpStatus.OK);
     * }
     * 
     * @PostMapping("register")
     * public ResponseEntity<String> register(@RequestBody UserDto registerDto) {
     * 
     * Optional<User> optUser =
     * userRepository.findByusername(registerDto.getUserName());
     * boolean exists = optUser.isPresent();
     * 
     * if (exists) {
     * return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
     * } else {
     * User user = userModelAssembler.toEntity(registerDto);
     * user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
     * user.setRoles("USER");
     * 
     * userRepository.save(user);
     * 
     * return new ResponseEntity<>("User registered success!", HttpStatus.OK);
     * }
     * 
     * }
     */
    /*
     * @PostMapping("logout")
     * public ResponseEntity<String> logout(HttpServletRequest request)
     * {
     * Authentication authentication =
     * SecurityContextHolder.getContext().getAuthentication();
     * 
     * if(authentication != null){
     * SecurityContextLogoutHandler scl = new SecurityContextLogoutHandler();
     * scl.setInvalidateHttpSession(true);
     * scl.logout(request, null, authentication);
     * }
     * return new ResponseEntity<>("User logged out!", HttpStatus.OK);
     * }
     */

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody LoginRequestBody authenticationDTO,
            HttpServletResponse response)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.username(),
                    authenticationDTO.password()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.username());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDto registerDto) {

        Optional<User> optUser = userRepository.findByusername(registerDto.getUserName());
        boolean exists = optUser.isPresent();

        if (exists) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        } else {
            User user = userModelAssembler.toEntity(registerDto);
            user.setPassword(new BCryptPasswordEncoder().encode((registerDto.getPassword())));
            user.setRoles("USER");

            userRepository.save(user);

            return new ResponseEntity<>("User registered success!", HttpStatus.OK);
        }
    }

}
