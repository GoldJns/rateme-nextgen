package com.app.rateme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.AuthenticationDto;
import com.app.rateme.api.dto.AuthenticationResponse;
import com.app.rateme.api.dto.UserDto;
import com.app.rateme.api.dto.UserResponseDto;
import com.app.rateme.api.dto.UserModelAssembler;
import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;
import com.app.rateme.security.AppUserDetailsService;
import com.app.rateme.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody AuthenticationDto authenticationDTO) {

        return createAuthenticationToken(authenticationDTO);

    }

    private AuthenticationResponse createAuthenticationToken(AuthenticationDto authenticationDTO)
            throws BadCredentialsException, UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.username(),
                    authenticationDTO.password()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.username());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto registerDto) {

        User optUser = userRepository.findByusername(registerDto.getUserName());

        if (optUser != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            User user = userModelAssembler.toEntity(registerDto);
            user.setPassword(new BCryptPasswordEncoder().encode((registerDto.getPassword())));
            user.setRoles("USER");

            userRepository.save(user);

            AuthenticationDto authDTO = new AuthenticationDto(registerDto.getUserName(), registerDto.getPassword());
            AuthenticationResponse authResponse = createAuthenticationToken(authDTO);

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
    }

    @GetMapping("user")
    public ResponseEntity<UserResponseDto> fetchUser(@RequestHeader("Authorization") String token) {
        String jwt;
        String[] headerParts = token.split(" ");
        jwt = headerParts[1];
        UserResponseDto userResponseDto = userModelAssembler
                .toModelResponse(userRepository.findByusername(jwtUtil.extractUsername(jwt)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

}
