package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.CreateNewTracker;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewTrackerHandler implements CommandHandler<CreateNewTracker>{
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public CreateNewTrackerHandler(BookTrackerServiceFeignClient bookTrackerServiceFeignClient, BookHandler bookHandler) {
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public void execute(CreateNewTracker command) {
        bookTrackerServiceFeignClient.createBookTracker(command.getBookId());
    }
}
