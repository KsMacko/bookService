package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.SaveBookToDatabase;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveBookCommandHandler implements CommandHandler<SaveBookToDatabase>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public SaveBookCommandHandler(BookRepo bookRepo, BookHandler bookHandler, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookHandler = bookHandler;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public void execute(SaveBookToDatabase query) {
        BookEntity entity = bookHandler.handleToEntity(query.getBookDto());
        bookRepo.save(entity);
        bookTrackerServiceFeignClient.createBookTracker(query.getBookDto().getId());
    }
}
