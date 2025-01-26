package com.example.bookstorageservice.security.JWT;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
