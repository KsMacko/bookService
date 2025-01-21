package com.example.booktrackerservice.commandHandlers.commands;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class CreateBookTracker extends BaseCommand{
    private Long bookId;

    public CreateBookTracker(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
