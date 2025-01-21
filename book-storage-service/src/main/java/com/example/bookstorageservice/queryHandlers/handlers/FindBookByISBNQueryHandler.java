package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.BookDetailsDto;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.entity.DTO.BookTracker;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
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
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public FindBookByISBNQueryHandler(BookRepo bookRepo, BookHandler bookHandler, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public List<BookDetailsDto> execute(FindBookByISBN query) {
        List<BookDetailsDto> bookDtos = new ArrayList<>();
        Optional<BookEntity> book = bookRepo.findByIsbn(query.getIsbn());
        book.ifPresent(bookEntity -> {
            BookDto bookDto = bookHandler.handleToDto(bookEntity);
            List<BookTracker> tracker = bookTrackerServiceFeignClient.getTrackerByBookId(bookDto.getId());
            bookDtos.add(new BookDetailsDto(bookDto, tracker.get(0)));
        });
        return bookDtos;
    }
}
