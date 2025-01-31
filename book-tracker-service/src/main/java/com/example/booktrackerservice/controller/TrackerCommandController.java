package com.example.booktrackerservice.controller;

import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.service.CommandService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TrackerCommandController {
    private final CommandService commandService;

    public TrackerCommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/create_tracker/{bookId}")
    void createBookTracker(@PathVariable Long bookId) {
        commandService.createBookTracker(bookId);
    }

    @DeleteMapping("/delete_tracker/{bookId}")
    void deleteBookTracker(@PathVariable Long bookId) {
        commandService.deleteTrackerByBookId(bookId);
    }

    @PutMapping("/update_tracker")
    void updateTracker(@RequestBody BookTrackerDto bookTrackerDto) {
        commandService.changeBookStatus(bookTrackerDto);
    }
}
