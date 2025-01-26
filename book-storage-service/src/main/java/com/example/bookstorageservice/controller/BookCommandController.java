package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.commandHandlers.command.CreateNewTracker;
import com.example.bookstorageservice.commandHandlers.command.DeleteBookById;
import com.example.bookstorageservice.commandHandlers.command.SaveBookToDatabase;
import com.example.bookstorageservice.commandHandlers.command.UpdateBookStatus;
import com.example.bookstorageservice.commandHandlers.dispatcher.BookCommandDispatcher;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.entity.DTO.BookTracker;
import com.example.bookstorageservice.entity.DTO.Status;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookCommandController {
    private final BookCommandDispatcher bookCommandDispatcher;

    @DeleteMapping("/delete/{id}")
    @Operation
    public String deleteBook(@PathVariable Long id){
        bookCommandDispatcher.dispatch(new DeleteBookById(id));
        return "redirect:/book/";
    }
    @PostMapping("/save")
    @Operation
    public String saveBook(@ModelAttribute("book") BookDto bookDto){
        bookCommandDispatcher.dispatch(new SaveBookToDatabase(bookDto));
        return "redirect:/book/";
    }
    @PutMapping("/tracker/update")
    @Operation
    public String updateTracker(@ModelAttribute("book_tracker") BookTracker tracker){
        if (tracker.getBookStatus()== Status.AVAILABLE)
            bookCommandDispatcher.dispatch(new CreateNewTracker(tracker.getBookId()));
        else bookCommandDispatcher.dispatch(new UpdateBookStatus(tracker));
        return "redirect:/book/";
    }
}
