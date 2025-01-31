package com.example.bookstorageservice.security.jwt;

import com.example.bookstorageservice.entity.User;
import com.example.bookstorageservice.security.ErrorType;
import com.example.bookstorageservice.security.RegUserDto;
import com.example.bookstorageservice.security.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final TokenDetails tokenDetails;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> createAuthToken(JwtRequest authRequest, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorType.USER_NOT_FOUND.getDisplayName());
        }
        String token = tokenDetails.generateToken(authRequest.getUsername());

        return getResponseEntity(response, token);
    }

    private ResponseEntity<String> getResponseEntity(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) tokenDetails.getJwtExpirationInMs());
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<String> createNewUser(RegUserDto registrationUserDto, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorType.PASSWORD_NOT_MATCH.getDisplayName());
        }
        if (userService.userExists(registrationUserDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorType.USER_ALREADY_EXISTS.getDisplayName());
        }
        User user = userService.createNewUser(registrationUserDto);
        String token = tokenDetails.generateToken(user.getLogin());
        return getResponseEntity(response, token);
    }
}