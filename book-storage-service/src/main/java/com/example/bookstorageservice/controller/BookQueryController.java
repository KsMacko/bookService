package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.entity.DTO.BookDetailsDto;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.queryHandlers.dispatcher.BookQueryDispatcher;
import com.example.bookstorageservice.queryHandlers.query.FindAllBooksQuery;
import com.example.bookstorageservice.queryHandlers.query.FindBookByISBN;
import com.example.bookstorageservice.queryHandlers.query.FindBookById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookQueryController {
    private final BookQueryDispatcher bookQueryDispatcher;

    @Autowired
    public BookQueryController(BookQueryDispatcher bookQueryDispatcher) {
        this.bookQueryDispatcher = bookQueryDispatcher;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        List<BookDetailsDto> books = bookQueryDispatcher.dispatch(new FindAllBooksQuery());
        model.addAttribute("books", books);
        return "actions";
    }
    @GetMapping("/open_add_form")
    public String addBook(Model model){
        model.addAttribute("book", new BookDto());
        return "add_book";
    }
    @GetMapping("/open_update_form/{id}")
    public String updateBook(@PathVariable Long id, Model model){
        List<BookDetailsDto> bookDtos = bookQueryDispatcher.dispatch(new FindBookById(id));
        model.addAttribute("book", bookDtos.get(0));
        return "update_book";
    }
    @GetMapping("/books/search_by_id")
    public String searchById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("books", bookQueryDispatcher.dispatch(new FindBookById(id)));
        return "actions";
    }
    @GetMapping("/books/search_by_isbn")
    public String searchByISBN(@RequestParam("isbn") String isbn, Model model) {
        model.addAttribute("books",bookQueryDispatcher.dispatch(new FindBookByISBN(isbn)));
        return "actions";
    }
}
