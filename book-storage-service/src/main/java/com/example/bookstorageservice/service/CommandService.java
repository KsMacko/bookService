package com.example.bookstorageservice.service;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.entity.BookHandler;
import com.example.bookstorageservice.entity.dto.BookTracker;
import com.example.bookstorageservice.feignclients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    public void createNewTracker(Long bookId) {
        bookTrackerServiceFeignClient.createBookTracker(bookId);
    }

    public void deleteBook(Long bookId) {
        bookRepo.deleteById(bookId);
        bookTrackerServiceFeignClient.deleteBookTracker(bookId);
    }

    public void saveBook(BookDto bookDto) {
        Optional<BookEntity> bookEntity;
        if (bookDto.getId() != null) {
            bookEntity = bookRepo.findById(bookDto.getId());
            bookEntity.ifPresent(entity -> {
                entity.setAuthor(bookDto.getAuthor());
                entity.setDescription(bookDto.getDescription());
                entity.setName(bookDto.getName());
                entity.setIsbn(bookDto.getIsbn());
                entity.setGenre(bookDto.getGenre());
                bookRepo.save(entity);

            });
        } else {
            BookEntity entity = bookHandler.handleToEntity(bookDto);
            bookRepo.save(entity);
            if (bookDto.getId() == null) bookTrackerServiceFeignClient.createBookTracker(entity.getId());
        }
    }

    public void updateBookStatus(BookTracker bookTracker) {
        bookTrackerServiceFeignClient.updateTracker(bookTracker);
    }
}
