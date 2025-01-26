package com.example.bookstorageservice.security;

import lombok.Data;

@Data
public class RegUserDto {
    private String username;
    private String password;
    private String confirmPassword;
}
