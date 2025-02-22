package com.example.booktrackerservice.entities;

import lombok.Getter;

@Getter
public enum Status {
    AVAILABLE("Свободна"),
    BORROWED("Взята"),
    OVERDUE("Просрочена"),
    RENEWED("Продлена");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}
