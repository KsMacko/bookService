package com.example.bookstorageservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}
