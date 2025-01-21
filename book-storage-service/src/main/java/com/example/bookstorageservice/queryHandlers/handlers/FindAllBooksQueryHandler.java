package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookDetailsDto;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.entity.DTO.BookTracker;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.FindAllBooksQuery;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FindAllBooksQueryHandler implements QueryHandler<FindAllBooksQuery>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public FindAllBooksQueryHandler(BookRepo bookRepo, BookHandler bookHandler, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public List<BookDetailsDto> execute(FindAllBooksQuery query) {
        List<BookDto> dtos = bookRepo.findAll().stream().map(bookHandler::handleToDto).toList();
        Map<Long, BookTracker> trackers = bookTrackerServiceFeignClient.getAllBooks().stream().collect(
                Collectors.toMap(BookTracker::getBookId, Function.identity()));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        dtos.forEach(dto->{
            bookDetailsDTOs.add(new BookDetailsDto(dto, trackers.get(dto.getId())));
        });
        return bookDetailsDTOs;
    }
}
