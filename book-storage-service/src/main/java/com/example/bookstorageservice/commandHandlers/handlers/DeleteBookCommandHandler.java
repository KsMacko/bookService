package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.DeleteBookById;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookById>{
    private final BookRepo bookRepo;

    @Autowired
    public DeleteBookCommandHandler(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void execute(DeleteBookById query) {
        bookRepo.deleteById(query.getId());
    }
}
