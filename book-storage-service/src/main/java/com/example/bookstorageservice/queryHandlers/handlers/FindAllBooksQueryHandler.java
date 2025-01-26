package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.*;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.FindAllBooksQuery;
import com.example.bookstorageservice.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllBooksQueryHandler implements QueryHandler<FindAllBooksQuery>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;


    @Override
    public List<BookDetailsDto> execute(FindAllBooksQuery query) {
        List<BookDto> dtos = bookRepo.findAll().stream().map(bookHandler::handleToDto).toList();
        Map<Long, List<BookTracker>> trackers = bookTrackerServiceFeignClient.getAllBooks().stream()
                .collect(Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        dtos.forEach(dto -> {
            List<BookTracker> sortedTrackers = trackers.getOrDefault(dto.getId(), Collections.emptyList()).stream()
                    .sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1)
                            .thenComparing(BookTracker::getBorrowDate, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
            bookDetailsDTOs.add(new BookDetailsDto(dto, sortedTrackers));
        });
        return bookDetailsDTOs;
    }

}
