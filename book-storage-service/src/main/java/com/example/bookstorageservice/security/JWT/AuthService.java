package com.example.bookstorageservice.security.JWT;

import com.example.bookstorageservice.security.RegUserDto;
import com.example.bookstorageservice.entity.User;
import com.example.bookstorageservice.security.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final TokenDetails tokenDetails;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<?> createAuthToken(JwtRequest authRequest, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не найден");
        }
        String token = tokenDetails.generateToken(authRequest.getUsername());

        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) tokenDetails.getJwtExpirationInMs());
        response.addCookie(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/book/");

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    public ResponseEntity<?> createNewUser(RegUserDto registrationUserDto, HttpServletResponse response) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пароли не совпадают");
        }
        if (userService.userExists(registrationUserDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким логином уже существует");
        }
        User user = userService.createNewUser(registrationUserDto);
        String token = tokenDetails.generateToken(user.getLogin());
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) tokenDetails.getJwtExpirationInMs());
        response.addCookie(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/book/");

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}