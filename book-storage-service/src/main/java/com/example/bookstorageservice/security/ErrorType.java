package com.example.bookstorageservice.security;

import lombok.Getter;

@Getter
public enum ErrorType {
    USER_NOT_FOUND("User not found"),
    PASSWORD_NOT_MATCH("Passwords don't match"),
    USER_ALREADY_EXISTS("User already exists");

    private final String displayName;
    ErrorType(String displayName) {
        this.displayName = displayName;
    }

}
