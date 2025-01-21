package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class BookCommandController {
    @Autowired private BookRepo bookRepo;
    @Autowired private BookHandler bookHandler;
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
}
