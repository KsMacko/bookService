package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.*;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.FindBookByISBN;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<BookDto> dtos = bookRepo.findAllByIsbn(query.getIsbn()).stream().map(bookHandler::handleToDto).toList();
        Map<Long, List<BookTracker>> trackers = bookTrackerServiceFeignClient.getAllBooks().stream().collect(
                Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        dtos.forEach(dto->{
            List<BookTracker> sortedTrackers = trackers.getOrDefault(dto.getId(), Collections.emptyList()).stream()
                    .sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1)
                            .thenComparing(Comparator.comparing(BookTracker::getBorrowDate).reversed()))
                    .collect(Collectors.toList());
            bookDetailsDTOs.add(new BookDetailsDto(dto, sortedTrackers));
        });
        return bookDetailsDTOs;
    }
}
