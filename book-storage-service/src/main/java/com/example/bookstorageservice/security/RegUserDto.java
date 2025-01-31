package com.example.bookstorageservice.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegUserDto {
    private String username;
    private String password;
    private String confirmPassword;
}
