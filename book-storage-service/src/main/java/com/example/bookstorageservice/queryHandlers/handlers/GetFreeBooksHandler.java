package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.*;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.GetFreeBooks;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetFreeBooksHandler implements QueryHandler<GetFreeBooks>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public GetFreeBooksHandler(BookRepo bookRepo, BookHandler bookHandler, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public List<BookDetailsDto> execute(GetFreeBooks query) {
        Map<Long, List<BookTracker>> trackersLists = bookTrackerServiceFeignClient.getFreeBooks().stream().collect(
                Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        trackersLists.forEach((bookId,trackerList)->{
            Optional<BookEntity> book = bookRepo.findById(bookId);
            book.ifPresent(bookEntity -> {
                List<BookTracker> sortedTrackers = trackerList.stream().sorted(Comparator.comparing((BookTracker t) ->
                                        t.getBookStatus() == Status.AVAILABLE ? 0 : 1)
                        .thenComparing(BookTracker::getBorrowDate, Comparator.nullsLast(Comparator.reverseOrder())))
                        .toList();
                bookDetailsDTOs.add(new BookDetailsDto(bookHandler.handleToDto(bookEntity), sortedTrackers));
            });
        });
        return bookDetailsDTOs;
    }
}
