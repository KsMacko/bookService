package com.example.bookstorageservice.commandHandlers.command;

import com.example.bookstorageservice.entity.DTO.BookTracker;

public class UpdateBookStatus extends BaseCommand{
    private BookTracker bookTracker;
    public UpdateBookStatus(BookTracker bookTracker){
        this.bookTracker=bookTracker;
    }
    public BookTracker getBookTracker() {
        return bookTracker;
    }
}
