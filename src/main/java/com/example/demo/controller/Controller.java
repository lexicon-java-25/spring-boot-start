package com.example.demo.controller;


import com.example.demo.model.Greeting;
import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.service.JwtService;
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
    private final CustomUserDetailService UserdetailService;

    private final JwtService jwtService;

    public Controller(Service service, AuthenticationManager authenticationManager, CustomUserDetailService userdetailService, JwtService jwtService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        UserdetailService = userdetailService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginReqest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqest.getName(),
                        loginReqest.getPassword()
                )
        );

        UserDetails user = UserdetailService.loadUserByUsername(loginReqest.getName());

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(token);
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
