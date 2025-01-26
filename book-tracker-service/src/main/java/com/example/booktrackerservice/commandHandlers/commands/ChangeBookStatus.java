package com.example.booktrackerservice.commandHandlers.commands;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;


public class ChangeBookStatus extends BaseCommand{
    private BookTrackerDto bookTrackerDto;

    public ChangeBookStatus(BookTrackerDto bookTrackerDto) {
        this.bookTrackerDto=bookTrackerDto;
    }
    public BookTrackerDto getBookTrackerDto() {
        return bookTrackerDto;
    }
}
