package com.example.bookstorageservice.entity;

public enum Status {
    AVAILABLE("Свободна"),
    BORROWED("Взята"),
    OVERDUE("Просрочена"),
    RENEWED("Продлена");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

}
