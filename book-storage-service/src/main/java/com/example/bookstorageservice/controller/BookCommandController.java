package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.commandHandlers.command.DeleteBookById;
import com.example.bookstorageservice.commandHandlers.command.SaveBookToDatabase;
import com.example.bookstorageservice.commandHandlers.dispatcher.BookCommandDispatcher;
import com.example.bookstorageservice.entity.DTO.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookCommandController {
    private final BookCommandDispatcher bookCommandDispatcher;
    @Autowired
    public BookCommandController(BookCommandDispatcher bookCommandDispatcher) {
        this.bookCommandDispatcher = bookCommandDispatcher;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookCommandDispatcher.dispatch(new DeleteBookById(id));
        return "redirect:/";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookDto bookDto){
        bookCommandDispatcher.dispatch(new SaveBookToDatabase(bookDto));
        return "redirect:/";
    }
}
