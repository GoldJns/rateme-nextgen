package com.app.rateme.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
