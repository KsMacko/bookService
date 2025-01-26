package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.security.RegUserDto;
import com.example.bookstorageservice.security.JWT.AuthService;
import com.example.bookstorageservice.security.JWT.JwtRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AuthService authService;

    @GetMapping("/")
    @Operation
    public String showLoginPage(Model model) {
        model.addAttribute("log", true);
        model.addAttribute("errorMessage", null);
        return "auth_form";
    }

    @GetMapping("/show_reg_form")
    @Operation
    public String showRegPage(Model model) {
        model.addAttribute("log", false);
        model.addAttribute("errorMessage", null);
        return "auth_form";
    }

    @PostMapping("/auth")
    @Operation
    public String createAuthToken(@ModelAttribute JwtRequest authRequest, Model model, HttpServletResponse response) {
        System.out.println("SecurityContext Authentication: {}"+ SecurityContextHolder.getContext().getAuthentication());
        ResponseEntity<?> result = authService.createAuthToken(authRequest, response);
        System.out.println("SecurityContext Authentication: {}"+ SecurityContextHolder.getContext().getAuthentication());
        if (result.getStatusCode().is4xxClientError()) {
            model.addAttribute("errorMessage", result.getBody().toString());
            model.addAttribute("log", true);
            return "auth_form";
        }

        return "redirect:/book/";
    }
    @Operation
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute RegUserDto registrationUserDto, Model model, HttpServletResponse response) {
        ResponseEntity<?> result = authService.createNewUser(registrationUserDto, response);

        if (result.getStatusCode().is4xxClientError()) {
            model.addAttribute("errorMessage", result.getBody().toString());
            model.addAttribute("log", false);
            return "auth_form";
        }

        return "redirect:/book/";
    }
}
