package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.*;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.FindBookById;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FindBookByIdQueryHandler implements QueryHandler<FindBookById>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public FindBookByIdQueryHandler(BookRepo bookRepo, BookHandler bookHandler, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public List<BookDetailsDto> execute(FindBookById query) {
        List<BookDetailsDto> bookDtos = new ArrayList<>();
        Optional<BookEntity> book = bookRepo.findById(query.getId());
        book.ifPresent(bookEntity -> {
            List<BookTracker>  trackers = bookTrackerServiceFeignClient.getTrackerByBookId(bookEntity.getId());
            BookDto dto = bookHandler.handleToDto(bookEntity);
            List<BookTracker> sortedTrackers = trackers.stream()
                    .sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1)
                            .thenComparing(Comparator.comparing(BookTracker::getBorrowDate).reversed()))
                    .collect(Collectors.toList());
            bookDtos.add(new BookDetailsDto(dto, sortedTrackers));
        });
        return bookDtos;
    }
}
