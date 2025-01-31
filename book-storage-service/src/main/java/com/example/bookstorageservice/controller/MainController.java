package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.security.RegUserDto;
import com.example.bookstorageservice.security.jwt.AuthService;
import com.example.bookstorageservice.security.jwt.JwtRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<String> createAuthToken(@RequestBody JwtRequest authRequest, HttpServletResponse response) {
        return authService.createAuthToken(authRequest, response);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody RegUserDto registrationUserDto, HttpServletResponse response) {
        return authService.createNewUser(registrationUserDto, response);
    }
}
