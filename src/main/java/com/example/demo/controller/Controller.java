package com.example.demo.controller;


import com.example.demo.model.Greeting;
import com.example.demo.model.dto.JwtResponseDTO;
import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.security.JwtService;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/public")
@RestController
public class Controller {

@Value("${app.welcome-message}")
    String message;
    Service service;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final CustomUserDetailService customUserDetailsService;


    public Controller(Service service, AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailService customUserDetailsService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO login){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.username(),
                        login.password()
                )
        );
        UserDetails user = customUserDetailsService.loadUserByUsername(login.username());

        String token = jwtService.genererateToken(user);

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
    @GetMapping("/hello")
    public Greeting sayHello(){
        return service.greeting();
    }

    @GetMapping("/bye")
    public String sayGoodbye(){
        return message;
    }

}
