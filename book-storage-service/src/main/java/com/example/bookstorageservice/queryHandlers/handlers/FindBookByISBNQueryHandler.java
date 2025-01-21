package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.queryHandlers.query.FindBookByISBN;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindBookByISBNQueryHandler implements QueryHandler<FindBookByISBN>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;

    @Autowired
    public FindBookByISBNQueryHandler(BookRepo bookRepo, BookHandler bookHandler) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
    }

    @Override
    public List<BookDto> execute(FindBookByISBN query) {
        List<BookDto> bookDtos = new ArrayList<>();
        Optional<BookEntity> book = bookRepo.findByIsbn(query.getIsbn());
        book.ifPresent(bookEntity->bookDtos.add(bookHandler.handleToDto(bookEntity)));
        return bookDtos;
    }
}
