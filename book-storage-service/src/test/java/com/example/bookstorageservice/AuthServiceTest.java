package com.example.bookstorageservice;

import com.example.bookstorageservice.security.UserService;
import com.example.bookstorageservice.security.jwt.AuthService;
import com.example.bookstorageservice.security.jwt.JwtRequest;
import com.example.bookstorageservice.security.jwt.TokenDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private TokenDetails tokenDetails;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testCreateAuthToken() {
        JwtRequest jwtRequest = new JwtRequest("user", "password");
        when(tokenDetails.generateToken("user")).thenReturn("token");

        ResponseEntity<String> responseEntity = authService.createAuthToken(jwtRequest, response);

        verify(authenticationManager, times(1)).authenticate(any());
        verify(response, times(1)).addCookie(any());
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }
}
