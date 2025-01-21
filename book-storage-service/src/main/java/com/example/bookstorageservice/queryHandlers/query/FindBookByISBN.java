package com.example.bookstorageservice.queryHandlers.query;

import lombok.AllArgsConstructor;


public class FindBookByISBN extends BaseQuery {
    private String isbn;

    public FindBookByISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
