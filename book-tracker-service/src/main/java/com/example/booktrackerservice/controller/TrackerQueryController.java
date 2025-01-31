package com.example.booktrackerservice.controller;

import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrackerQueryController {
    private final QueryService queryService;

    @GetMapping("/get_free_book_trackers")
    List<BookTrackerDto> getFreeBooks() {
        return queryService.showFreeBooks();
    }

    @GetMapping("/get_all_book_trackers")
    List<BookTrackerDto> getAllBooks() {
        return queryService.showAllBookTrackers();
    }

    @GetMapping("/get_tracker_byBook/{book_id}")
    List<BookTrackerDto> getTrackerByBookId(@PathVariable Long book_id) {
        return queryService.getTrackerByBookId(book_id);
    }
}
