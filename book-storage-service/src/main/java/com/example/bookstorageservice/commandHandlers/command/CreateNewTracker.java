package com.example.bookstorageservice.commandHandlers.command;

public class CreateNewTracker extends BaseCommand{
    private Long bookId;
    public CreateNewTracker(Long bookId){
        this.bookId=bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
