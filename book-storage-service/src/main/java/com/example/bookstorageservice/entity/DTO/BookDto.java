package com.example.bookstorageservice.entity.DTO;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto{
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}
