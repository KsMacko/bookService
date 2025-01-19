package com.example.bookstorageservice;

import lombok.Data;

@Data
public class BookDto {
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}
