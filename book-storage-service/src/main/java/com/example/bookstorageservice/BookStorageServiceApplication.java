package com.example.bookstorageservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
@Controller
public class BookStorageServiceApplication {
    @Autowired private BookRepo bookRepo;
    @Autowired private BookHandler bookHandler;

    public static void main(String[] args) {
        SpringApplication.run(BookStorageServiceApplication.class, args);
    }
    @GetMapping("/")
    public String getMainPage(Model model){
        List<BookDto> books = bookRepo.findAll().stream()
                        .map(book->bookHandler.handleToDto(book)).toList();
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
        try {
            BookEntity book = bookRepo.findById(id).orElseThrow();
            model.addAttribute("book", bookHandler.handleToDto(book));
        }catch (Exception e){
            System.out.println("book is not found");
        }
        return "update_book";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        try {
            bookRepo.deleteById(id);
        }catch(NoSuchElementException e){
            System.out.println(id+" not found");
        }

        return "redirect:/";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookDto bookDto){
        BookEntity book = bookHandler.handleToEntity(bookDto);
        bookRepo.save(book);
        return "redirect:/";
    }

    @GetMapping("/books/search_by_id")
    public String searchById(@RequestParam("id") Long id, Model model) {
        try {
            BookEntity book = bookRepo.findById(id).orElseThrow();
            model.addAttribute("searched_book", bookHandler.handleToDto(book));
        }catch (NoSuchElementException e){
            System.out.println("No such element");
        }
        return "actions";
    }
    @GetMapping("/books/search_by_isbn")
    public String searchByISBN(@RequestParam("isbn") String isbn, Model model) {
            BookDto book = bookHandler.handleToDto(bookRepo.findAllByIsbn(isbn));
            model.addAttribute("searched_book",book);
        return "actions";
    }

}
