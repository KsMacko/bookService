package com.example.bookstorageservice.commandHandlers.command;

public class DeleteBookById extends BaseCommand{
    private Long id;

    public DeleteBookById(Long id){
        this.id=id;
    }

    public Long getId() {
        return id;
    }
}
