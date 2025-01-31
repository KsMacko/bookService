package com.example.bookstorageservice.service;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.dto.BookDetailsDto;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.entity.BookHandler;
import com.example.bookstorageservice.entity.dto.BookTracker;
import com.example.bookstorageservice.entity.Status;
import com.example.bookstorageservice.feignclients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    public List<BookDetailsDto> findAllBooks() {
        List<BookDto> dtos = bookRepo.findAll().stream().map(bookHandler::handleToDto).toList();
        Map<Long, List<BookTracker>> trackers = bookTrackerServiceFeignClient.getAllBooks().stream().collect(Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        dtos.forEach(dto -> {
            List<BookTracker> sortedTrackers = trackers.getOrDefault(dto.getId(), Collections.emptyList()).stream().sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1).thenComparing(BookTracker::getBorrowDate, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
            bookDetailsDTOs.add(new BookDetailsDto(dto, sortedTrackers));
        });
        return bookDetailsDTOs;
    }

    public BookDetailsDto findBookById(Long bookId) {
        BookDetailsDto bookDto = new BookDetailsDto();
        Optional<BookEntity> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            BookEntity bookEntity = book.get();
            List<BookTracker> trackers = bookTrackerServiceFeignClient.getTrackerByBookId(bookEntity.getId());
            BookDto dto = bookHandler.handleToDto(bookEntity);
            List<BookTracker> sortedTrackers = trackers.stream().sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1).thenComparing(Comparator.comparing(BookTracker::getBorrowDate).reversed())).collect(Collectors.toList());
            bookDto = new BookDetailsDto(dto, sortedTrackers);
        }
        return bookDto;
    }

    public List<BookDetailsDto> findBookByIsbn(String isbn) {
        List<BookDto> dtos = bookRepo.findAllByIsbn(isbn).stream().map(bookHandler::handleToDto).toList();
        Map<Long, List<BookTracker>> trackers = bookTrackerServiceFeignClient.getAllBooks().stream().collect(Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        dtos.forEach(dto -> {
            List<BookTracker> sortedTrackers = trackers.getOrDefault(dto.getId(), Collections.emptyList()).stream()
                    .sorted(Comparator.comparing((BookTracker t) -> t.getBookStatus() == Status.AVAILABLE ? 0 : 1)
                            .thenComparing(Comparator.comparing(BookTracker::getBorrowDate).reversed()))
                    .collect(Collectors.toList());
            bookDetailsDTOs.add(new BookDetailsDto(dto, sortedTrackers));
        });
        return bookDetailsDTOs;
    }

    public List<BookDetailsDto> getFreeBooks() {
        Map<Long, List<BookTracker>> trackersLists = bookTrackerServiceFeignClient.getFreeBooks().stream()
                .collect(Collectors.groupingBy(BookTracker::getBookId));
        List<BookDetailsDto> bookDetailsDTOs = new ArrayList<>();
        trackersLists.forEach((bookId, trackerList) -> {
            Optional<BookEntity> book = bookRepo.findById(bookId);
            book.ifPresent(bookEntity -> {
                List<BookTracker> sortedTrackers = trackerList.stream().sorted(Comparator.comparing((BookTracker t) ->
                        t.getBookStatus() == Status.AVAILABLE ? 0 : 1).thenComparing(BookTracker::getBorrowDate,
                        Comparator.nullsLast(Comparator.reverseOrder()))).toList();
                bookDetailsDTOs.add(new BookDetailsDto(bookHandler.handleToDto(bookEntity), sortedTrackers));
            });
        });
        return bookDetailsDTOs;
    }
}
