package com.example.bookstorageservice.commandHandlers.command;

import com.example.bookstorageservice.entity.DTO.BookDto;

public class SaveBookToDatabase extends BaseCommand{
    private BookDto bookDto;
    public SaveBookToDatabase(BookDto bookDto){
        this.bookDto=bookDto;
    }
    public BookDto getBookDto() {return bookDto;}
}
