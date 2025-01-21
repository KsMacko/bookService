package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.queryHandlers.query.FindAllBooksQuery;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllBooksQueryHandler implements QueryHandler<FindAllBooksQuery>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;

    @Autowired
    public FindAllBooksQueryHandler(BookRepo bookRepo, BookHandler bookHandler) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
    }

    @Override
    public List<BookDto> execute(FindAllBooksQuery query) {
        List<BookEntity> entities = bookRepo.findAll();
        List<BookDto> dtos = entities.stream().map(bookHandler::handleToDto).toList();
        return dtos;
    }
}
