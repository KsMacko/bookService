package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.UpdateBookStatus;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookStatusHandler implements CommandHandler<UpdateBookStatus>{
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Autowired
    public UpdateBookStatusHandler(BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public void execute(UpdateBookStatus command) {
        bookTrackerServiceFeignClient.updateTracker(command.getBookTracker());
    }
}
