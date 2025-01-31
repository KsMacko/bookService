package com.example.bookstorageservice.feignclients;

import com.example.bookstorageservice.entity.dto.BookTracker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "book-tracker-service", url = "http://localhost:8081/")
public interface BookTrackerServiceFeignClient {
    @PostMapping("/create_tracker/{bookId}")
    void createBookTracker(@PathVariable Long bookId);

    @DeleteMapping("delete_tracker/{bookId}")
    void deleteBookTracker(@PathVariable Long bookId);

    @PutMapping("/update_tracker")
    void updateTracker(@RequestBody BookTracker bookTrackerDto);

    @GetMapping("/get_free_book_trackers")
    List<BookTracker> getFreeBooks();

    @GetMapping("/get_all_book_trackers")
    List<BookTracker> getAllBooks();

    @GetMapping("/get_tracker_byBook/{book_id}")
    List<BookTracker> getTrackerByBookId(@PathVariable Long book_id);
}
