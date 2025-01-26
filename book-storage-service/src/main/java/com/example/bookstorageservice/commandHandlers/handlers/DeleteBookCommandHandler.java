package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.DeleteBookById;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookById>{
    private final BookRepo bookRepo;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public DeleteBookCommandHandler(BookRepo bookRepo, BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookRepo = bookRepo;
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public void execute(DeleteBookById query) {
        bookRepo.deleteById(query.getId());
        bookTrackerServiceFeignClient.deleteBookTracker(query.getId());
    }
}
