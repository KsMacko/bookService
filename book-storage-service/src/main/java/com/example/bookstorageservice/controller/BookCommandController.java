package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.entity.dto.BookTracker;
import com.example.bookstorageservice.entity.Status;
import com.example.bookstorageservice.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookCommandController {
    private final CommandService commandService;

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        commandService.deleteBook(id);
    }

    @PostMapping("/save")
    public void saveBook(@RequestBody BookDto bookDto) {
        commandService.saveBook(bookDto);
    }

    @PutMapping("/tracker/update")
    public void updateTracker(@RequestBody BookTracker tracker) {
        if (tracker.getBookStatus() == Status.AVAILABLE)
            commandService.createNewTracker(tracker.getBookId());
        else commandService.updateBookStatus(tracker);
    }
}
