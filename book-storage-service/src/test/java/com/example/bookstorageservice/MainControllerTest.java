package com.example.bookstorageservice;

import com.example.bookstorageservice.controller.MainController;
import com.example.bookstorageservice.security.RegUserDto;
import com.example.bookstorageservice.security.jwt.AuthService;
import com.example.bookstorageservice.security.jwt.JwtRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MainControllerTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private MainController mainController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testCreateAuthToken() throws Exception {
        doReturn(ResponseEntity.ok("token")).when(authService).createAuthToken(any(JwtRequest.class), any(HttpServletResponse.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .contentType("application/json")
                        .content("{ \"username\": \"user\", \"password\": \"password\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("token"));

        verify(authService, times(1)).createAuthToken(any(JwtRequest.class), any(HttpServletResponse.class));
    }

    @Test
    public void testRegisterUser() throws Exception {
        doReturn(ResponseEntity.ok("User created")).when(authService).createNewUser(any(RegUserDto.class), any(HttpServletResponse.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .contentType("application/json")
                        .content("{ \"username\": \"user\", \"password\": \"password\", \"confirmPassword\": \"password\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User created"));

        verify(authService, times(1)).createNewUser(any(RegUserDto.class), any(HttpServletResponse.class));
    }
}
