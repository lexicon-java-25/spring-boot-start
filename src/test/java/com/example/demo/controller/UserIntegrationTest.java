package com.example.demo.controller;


import com.example.demo.model.AppUser;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    void LoginShouldReturnToken() throws Exception {

        AppUser user = new AppUser();
        user.setUsername("user123");
        user.setPassword(passwordEncoder.encode("pass123"));
        userRepository.save(user);



        mockMvc.perform(post("/public/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "user123",
                        "password": "pass123"
                        }
                        """))
                .andExpect(status().isOk());

    }
}
