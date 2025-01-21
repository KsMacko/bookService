package com.example.bookstorageservice.entity.DTO;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
public class BookDto{
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;


    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
