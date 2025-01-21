package com.example.booktrackerservice.controller;

import com.example.booktrackerservice.commandHandlers.commands.CreateBookTracker;
import com.example.booktrackerservice.commandHandlers.commands.DeleteTrackerByBookId;
import com.example.booktrackerservice.commandHandlers.dispatcher.CommandBookTrackerDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackerCommandController {
    private final CommandBookTrackerDispatcher bookTrackerDispatcher;
    @Autowired
    public TrackerCommandController(CommandBookTrackerDispatcher bookTrackerDispatcher) {
        this.bookTrackerDispatcher = bookTrackerDispatcher;
    }
    @PostMapping("/create_tracker/{bookId}")
    void createBookTracker(@PathVariable Long bookId){
        bookTrackerDispatcher.dispatch(new CreateBookTracker(bookId));
    }
    @DeleteMapping("delete_tracker/{bookId}")
    void deleteBookTracker(@PathVariable Long bookId){
        bookTrackerDispatcher.dispatch(new DeleteTrackerByBookId(bookId));
    }
}
